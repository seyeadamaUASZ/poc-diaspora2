package sn.gainde2000.userservice.business.Users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.gainde2000.userservice.business.Users.dtos.UserGetDTO;
import sn.gainde2000.userservice.business.Users.dtos.UserMapper;
import sn.gainde2000.userservice.exceptionHandler.ResourceNotFoundException;
import sn.gainde2000.userservice.security.dto.EmailRequest;
import sn.gainde2000.userservice.security.dto.PasswordRequest;
import sn.gainde2000.userservice.utils.AppConstants;
import sn.gainde2000.userservice.utils.DataResponse;
import sn.gainde2000.userservice.utils.ServeurResponse;


@RestController
public class UserController {
    static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private IUser iUser;
    private UserMapper mapper;

    public UserController(IUser iUser, UserMapper mapper) {
        this.iUser = iUser;
        this.mapper = mapper;
    }

    @GetMapping("/auth/users")
    public ResponseEntity<DataResponse> checkUsersPageables(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        logger.info("loading all users in response.....");
        return ResponseEntity.ok(iUser.getAllUsers(pageNo, pageSize, sortBy, sortDir));
    }


    @GetMapping("/checkUser/{telephone}")
    public ResponseEntity<UserGetDTO> userByTelephone(@PathVariable(name="telephone") String telephone){
        User user = iUser.findUserByTelephone(telephone);
        if (!user.equals("")){
            UserGetDTO userGetDTO = mapper.toUtilisateurGetDto(user);
            return ResponseEntity.ok(userGetDTO);
        }else{
            return null;
        }
    }

    @GetMapping("/activateUser/{telephone}")
    public ResponseEntity<UserGetDTO> activateUser(@PathVariable(name="telephone")String telephone){
           UserGetDTO userGetDTO = iUser.activateUserByTel(telephone);
           return ResponseEntity.ok(userGetDTO);
    }

    //envoyer code otp by email

    @PostMapping("/envoyerotpByMail")
    public ServeurResponse sendMail(@RequestBody EmailRequest emailRequest){
        return iUser.sendMail(emailRequest);
    }

    @PostMapping("/modifierPassword")
    public ServeurResponse modifierPassword(@RequestBody PasswordRequest passwordRequest){
        return iUser.confirmPassword(passwordRequest);
    }


    @GetMapping("/sendMailOtp/{email}")
    public ServeurResponse sendMaiLByOTP(@PathVariable(name="email")String email){
        return iUser.sendOTPByMail(email);
    }


    @GetMapping("/getInfo/{email}")
    public ServeurResponse getInfoUser(@PathVariable(name="email")String email){
        ServeurResponse response = new ServeurResponse();
        User user = iUser.findUserByEmail(email);
        if(user!=null){
            response.setDescription("user checked");
            response.setData(user);
            response.setStatut(true);
        }else{
            response.setDescription("user not checked");
            response.setData(user);
            response.setStatut(true);
        }
        return response;
    }

    //activate
    @GetMapping("/activateUtilisateur/{id}")
    public ServeurResponse activateUser(@PathVariable(name="id") Long id) throws ResourceNotFoundException {
        ServeurResponse response = new ServeurResponse();
        User user = iUser.activateUser(id);
        if(user !=null){
          response.setStatut(true);
          response.setData(user);
          response.setDescription("utilisateur activé !!");
        }else{
            response.setStatut(false);
            response.setData(null);
            response.setDescription("utilisateur non activé !!");
        }
        return response;
    }


    //desactivate
    @GetMapping("/desactivateUtilisateur/{id}")
    public ServeurResponse desactivateUser(@PathVariable(name="id") Long id) throws ResourceNotFoundException {
        ServeurResponse response = new ServeurResponse();
        User user = iUser.desactiveUser(id);
        if(user !=null){
            response.setStatut(true);
            response.setData(user);
            response.setDescription("utilisateur désactivé !!");
        }else{
            response.setStatut(false);
            response.setData(null);
            response.setDescription("utilisateur non activé !!");
        }
        return response;
    }

    @GetMapping("/countUsers")
    public ServeurResponse countUser(){
        ServeurResponse response = new ServeurResponse();
        Integer nombreUtilisateurs = iUser.countUsers();
        response.setData(nombreUtilisateurs);
        response.setDescription("nombre utilisateurs inscrits");
        response.setStatut(true);
        return response;
    }

    @GetMapping("/countUsersactivated")
    public ServeurResponse countUsersActivated(){
        ServeurResponse response = new ServeurResponse();
        Integer nombreUtilisateursActives = iUser.countUsersactivated();
        response.setStatut(true);
        response.setDescription("nombre utilisateurs activés");
        response.setData(nombreUtilisateursActives);
        return response;
    }

    @GetMapping("/countUsersdesactivated")
    public ServeurResponse countUsersdesactivated(){
        ServeurResponse response = new ServeurResponse();
        Integer nombreUtilisateursdesactives = iUser.countUsersdesactived();
        response.setStatut(true);
        response.setData(nombreUtilisateursdesactives);
        response.setDescription("nombre utilisateurs desactivés");
        return response;
    }




}
