package sn.gainde2000.userservice.business.Users;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import sn.gainde2000.userservice.business.Otp.*;
import sn.gainde2000.userservice.business.Users.dtos.UserGetDTO;
import sn.gainde2000.userservice.business.Users.dtos.UserMapper;
import sn.gainde2000.userservice.business.Users.dtos.UserPostDTO;
import sn.gainde2000.userservice.exceptionHandler.ResourceNotFoundException;
import sn.gainde2000.userservice.security.dto.*;
import sn.gainde2000.userservice.security.mappers.AuthUserMapper;
import sn.gainde2000.userservice.utils.*;

import javax.mail.MessagingException;

@Service
public class UserImpl implements IUser{
	
	    static final Logger log = LoggerFactory.getLogger(UserImpl.class);

	    private final UserRepository utilisateursRepository;
	    private final UserMapper mappers;

	    private static final String REGISTRATION_SUCCESSFUL = "registration_successful";

	    private final BCryptPasswordEncoder bCryptPasswordEncoder;

	    private final UserValidationService userValidationService;

	    private final GeneralMessageAccessor generalMessageAccessor;
	    private final ProfilRepository profilRepository;



		private final IParametreOtp iParametreOtp;


		private final ParametreOtpReposiroty repo;


		private final OTPConfigurationRepository repoConf;


		private final Email email;
	    
	    

	public UserImpl(UserRepository utilisateursRepository, UserMapper mappers,
					BCryptPasswordEncoder bCryptPasswordEncoder, UserValidationService userValidationService,
					GeneralMessageAccessor generalMessageAccessor, ProfilRepository profilRepository, IParametreOtp iParametreOtp, ParametreOtpReposiroty repo, OTPConfigurationRepository repoConf, Email email) {
			super();
			this.utilisateursRepository = utilisateursRepository;
			this.mappers = mappers;
			this.bCryptPasswordEncoder = bCryptPasswordEncoder;
			this.userValidationService = userValidationService;
			this.generalMessageAccessor = generalMessageAccessor;
			this.profilRepository = profilRepository;
		this.iParametreOtp = iParametreOtp;
		this.repo = repo;
		this.repoConf = repoConf;
		this.email = email;
	}

	@Override
	public DataResponse getAllUsers(int pageNo, int pageSize, String sortBy, String sortDir) {
		  Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
	                : Sort.by(sortBy).descending();
	        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

	        Page<User> utilisateurs = utilisateursRepository.findAll(pageable);
	        List<User> listOfUsers = utilisateurs.getContent();
	        List<UserGetDTO> listUsersGetDtos = mappers.toUserGetDtos(listOfUsers);
	        DataResponse dataResponse = new DataResponse();
	        dataResponse.setContent(listUsersGetDtos);
	        dataResponse.setPageNo(utilisateurs.getNumber());
	        dataResponse.setPageSize(utilisateurs.getSize());
	        dataResponse.setTotalElements(utilisateurs.getTotalElements());
	        dataResponse.setTotalPages(utilisateurs.getTotalPages());
	        dataResponse.setLast(utilisateurs.isLast());

	        return dataResponse;
	}

	@Override
	public UserGetDTO getUtilisateur(Long id) throws ResourceNotFoundException {
		User user = this.utilisateursRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Utilisateur not found for this id :: " + id));
        //log.error("User not exist with id ", id);
        return mappers.toUtilisateurGetDto(user);
	}

	@Override
	public User saveUtilisateur(UserPostDTO utilisateurs) {
		// TODO Auto-generated method stub
		  return this.utilisateursRepository.save(mappers.userPostDTOToUser(utilisateurs));
	}

	@Override
	public RegistrationResponse registration(RegistrationRequest registrationRequest) {
		userValidationService.validateUser(registrationRequest);
		RegistrationResponse registrationResponse=new RegistrationResponse();
		//verification du mail
		User userdoublon = this.utilisateursRepository.findUserByEmail(registrationRequest.getEmail());
		if(userdoublon!=null){
			String message ="Email existe déja !!";
			registrationResponse.setMessage(message);
			registrationResponse.setUserGetDTO(null);
		}else{

			final User user = AuthUserMapper.INSTANCE.convertToUser(registrationRequest);
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.setProfil(profilRepository.findProfilByNomProfil("USER"));
			user.setUserStatus(UserStatus.NO_ACTIVED);

			User user1 = utilisateursRepository.save(user);

			//

			//convert to dto
			UserGetDTO userGetDTO = mappers.toUtilisateurGetDto(user1);

			final String username = registrationRequest.getUsername();
			final String registrationSuccessMessage = generalMessageAccessor.getMessage(null, REGISTRATION_SUCCESSFUL,
					username);

			log.info("{} registered successfully!", username);
			registrationResponse.setMessage(registrationSuccessMessage);
			registrationResponse.setUserGetDTO(userGetDTO);

			//sending otp
			//otpService.envoyerCodeOtpByTypeAndNumero("email",registrationRequest.getEmail());

			//sending code otp process
			envoyerCodeOTP("email",registrationRequest.getEmail());

		}



        return registrationResponse;
	}

	@Override
	public AuthenticatedUserDTO findAuthenticatedUtilisateursByUsername(String username) {
		  final User user = utilisateursRepository.findUserByUsername(username);

	        return AuthUserMapper.INSTANCE.convertToAuthenticatedUserDto(user);
	}

	@Override
	public User findUserByEmail(String email) {
		return utilisateursRepository.findUserByEmail(email);
	}

	@Override
	public User findUserByTelephone(String telephone) {
		return utilisateursRepository.findUserByTelephone(telephone);
	}

	@Override
	public UserGetDTO activateUserByTel(String telephone) {
		User user = this.utilisateursRepository.findUserByTelephone(telephone);
		user.setUserStatus(UserStatus.ACTIVED);
		return mappers.toUtilisateurGetDto(this.utilisateursRepository.saveAndFlush(user));

	}

	@Override
	public void activateUserByEmail(String email) {
		User user = this.utilisateursRepository.findUserByTelephone(email);
		user.setUserStatus(UserStatus.ACTIVED);
		this.utilisateursRepository.saveAndFlush(user);

	}

	@Override
	public void updateUser(User user) {
		this.utilisateursRepository.saveAndFlush(user);
	}

	@Override
	public ServeurResponse sendMail(EmailRequest request) {
		ServeurResponse response = new ServeurResponse();
		//recap user
		User user = this.utilisateursRepository.findUserByEmail(request.getEmail());
		if(user ==null){
			response.setStatut(false);
			response.setData(null);
			response.setDescription("l'email de l'utilisateur n'existe pas ");
			//log.error("User not exist with email", request.getEmail());
		}else{
			response = envoyerCodeOTP("email",request.getEmail());
		}
		return response;

	}

	@Override
	public ServeurResponse confirmPassword(PasswordRequest passwordRequest) {
		ServeurResponse response = new ServeurResponse();
		//recap user by email
		User user = this.utilisateursRepository.findUserByEmail(passwordRequest.getEmail());
		//verifier toujours l'utilisateur
		if(user ==null){
			response.setStatut(false);
			response.setData(null);
			response.setDescription("L'utilisateur n'existe pas !!!");
		}else{
			//modifier le mot de passe
			user.setPassword(bCryptPasswordEncoder.encode(passwordRequest.getNewpassword()));
			this.utilisateursRepository.saveAndFlush(user);
			response.setStatut(true);
			response.setData(null);
			response.setDescription("Mot de passe modifié avec succés !!!");
		}
		return response;
	}

	@Override
	public ServeurResponse sendOTPByMail(String email) {
		ServeurResponse response = new ServeurResponse();
		//recap user
		User user = this.utilisateursRepository.findUserByEmail(email);
		if(user ==null){
			response.setStatut(false);
			response.setData(null);
			response.setDescription("l'email de l'utilisateur n'existe pas ");
			//log.error("User not exist with email", email);
		}else{
			response = envoyerCodeOTP("email",email);
		}
		return response;
	}

	@Override
	public User findUserByUsername(String username) {
		return this.utilisateursRepository.findUserByUsername(username);
	}

	@Override
	public User desactiveUser(Long id)throws ResourceNotFoundException {
		User user = this.utilisateursRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Utilisateur not found for this id :: " + id));
		//log.error("User not exist with id ", id);
		//desactiver status
		user.setUserStatus(UserStatus.NO_ACTIVED);
		return this.utilisateursRepository.saveAndFlush(user);
	}

	@Override
	public User activateUser(Long id)throws ResourceNotFoundException {
		User user = this.utilisateursRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Utilisateur not found for this id :: " + id));
		//log.error("User not exist with id ", id);
		//desactiver status
		user.setUserStatus(UserStatus.ACTIVED);
		return this.utilisateursRepository.saveAndFlush(user);
	}

	@Override
	public Integer countUsers() {
		return this.utilisateursRepository.countUsers();
	}

	@Override
	public Integer countUsersactivated() {
		return this.utilisateursRepository.countUsersactivated();
	}

	@Override
	public Integer countUsersdesactived() {
		return this.utilisateursRepository.countUsersdesactived();
	}


	private ServeurResponse envoyerCodeOTP(String type,String adresse){

		ServeurResponse response= new ServeurResponse();
		List<ParametreOtp> parametreotps = iParametreOtp.listParametreOtpByType(type);
		if(!parametreotps.isEmpty()) {
			ParametreOtp parametreOtp =parametreotps.get(0);
			OTPConfiguration otpConf=new OTPConfiguration();
			otpConf.setParametreOtp(parametreOtp);

			char[] otp= iParametreOtp.generateCodeOtp("Email");
			otpConf.setValcode(String.valueOf(otp));

			System.out.println("Code otp sending on "+ String.valueOf(otp));

			otpConf.setValDuree(new Double(parametreOtp.getDuree()).longValue());
			otpConf.setDejavalide(false);
			//envoyer le sms
			repoConf.save(otpConf);

			String from = "no-reply@gainde2000.sn";
			try {
				email.sendMail(from,adresse,"code de validation est \n"+otpConf.getValcode(),"validation code OTP");
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			response.setStatut(true);
			response.setDescription("code envoyé par "+parametreOtp.getTypeOtp()+"!!!");
			response.setData(otpConf);

		}else {
			response.setStatut(false);
			response.setDescription("ce type de parametrage n'existe pas");
			response.setData(null);
		}
		return response;

	}



}
