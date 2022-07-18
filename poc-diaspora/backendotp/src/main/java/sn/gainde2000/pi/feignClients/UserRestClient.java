package sn.gainde2000.pi.feignClients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sn.gainde2000.pi.otp.entity.User;
import sn.gainde2000.pi.otp.entity.UserGetDTO;

@FeignClient(name="auth")
public interface UserRestClient {

    @GetMapping("/checkUser/{telephone}")
     User getUserByTelephone(@PathVariable(name="telephone") String telephone);

     @GetMapping("/activateUser/{telephone}")
     UserGetDTO activateUser(@PathVariable(name="telephone")String telephone);

     @GetMapping("/activateUserByEmail/{email}")
     UserGetDTO activateUserByEmail(@PathVariable(name="email") String email);

}
