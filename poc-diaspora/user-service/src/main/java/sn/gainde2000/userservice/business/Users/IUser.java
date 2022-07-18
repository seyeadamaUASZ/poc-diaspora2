package sn.gainde2000.userservice.business.Users;

import org.springframework.data.jpa.repository.Query;
import sn.gainde2000.userservice.business.Users.dtos.UserGetDTO;
import sn.gainde2000.userservice.business.Users.dtos.UserPostDTO;
import sn.gainde2000.userservice.exceptionHandler.ResourceNotFoundException;
import sn.gainde2000.userservice.security.dto.*;
import sn.gainde2000.userservice.utils.DataResponse;
import sn.gainde2000.userservice.utils.ServeurResponse;

/**
 * @author aseye
 *
 */
/**
 * @author aseye
 *
 */
public interface IUser {
	
    // public List<UtilisateursGetDto> listUtilisateurs();
    DataResponse getAllUsers(int pageNo, int pageSize, String sortBy, String sortDir);

    UserGetDTO getUtilisateur(Long id) throws ResourceNotFoundException;
    
   User saveUtilisateur(UserPostDTO utilisateurs);


    RegistrationResponse registration(RegistrationRequest registrationRequest);

    AuthenticatedUserDTO findAuthenticatedUtilisateursByUsername(String username);

    User findUserByEmail(String email);

    User findUserByTelephone(String telephone);

    UserGetDTO activateUserByTel(String telephone);

    void activateUserByEmail(String email);

    public void updateUser(User user);

    ServeurResponse sendMail(EmailRequest emailRequest);

    ServeurResponse confirmPassword(PasswordRequest passwordRequest);

    ServeurResponse sendOTPByMail(String email);

    User findUserByUsername(String username);

    User desactiveUser(Long id)throws ResourceNotFoundException;

    User activateUser(Long id)throws ResourceNotFoundException;

    // aggrégation utilisateur
    Integer countUsers();

    Integer countUsersactivated();

    Integer countUsersdesactived();







}
