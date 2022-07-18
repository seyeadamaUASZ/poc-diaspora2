package sn.gainde2000.userservice.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import sn.gainde2000.userservice.business.Users.IUser;
import sn.gainde2000.userservice.business.Users.User;
import sn.gainde2000.userservice.business.Users.dtos.UserGetDTO;
import sn.gainde2000.userservice.business.Users.dtos.UserMapper;
import sn.gainde2000.userservice.security.dto.AuthenticatedUserDTO;
import sn.gainde2000.userservice.security.dto.LoginRequest;
import sn.gainde2000.userservice.security.dto.LoginRequest1;
import sn.gainde2000.userservice.security.dto.LoginResponse;
import sn.gainde2000.userservice.security.mappers.AuthUserMapper;

@Service
public class JwtTokenService {
	static final Logger log = LoggerFactory.getLogger(JwtTokenService.class);

    private final IUser userService;

    private final JwtTokenManager jwtTokenManager;

    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public JwtTokenService(IUser userService, JwtTokenManager jwtTokenManager, AuthenticationManager authenticationManager, UserMapper userMapper) {
        this.userService = userService;
        this.jwtTokenManager = jwtTokenManager;
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
    }

    public LoginResponse getLoginResponse(LoginRequest loginRequest) {

        final String username = loginRequest.getUsername();
        final String password = loginRequest.getPassword();

        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        final AuthenticatedUserDTO authenticatedUserDto = userService.findAuthenticatedUtilisateursByUsername(username);

        final User user = AuthUserMapper.INSTANCE.convertToUser(authenticatedUserDto);
        final String token = jwtTokenManager.generateToken(user);

        log.info(" {} has successfully logged in!", user.getUsername());

        return new LoginResponse(token);
    }


    public LoginResponse getLoginResponse1(LoginRequest1 loginRequest) {

        final String email = loginRequest.getEmail();
        final String password = loginRequest.getPassword();

        //find user by email

        User userByEmail = userService.findUserByEmail(email);

        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userByEmail.getUsername(), password);

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        final AuthenticatedUserDTO authenticatedUserDto = userService.findAuthenticatedUtilisateursByUsername(userByEmail.getUsername());

        final User user = AuthUserMapper.INSTANCE.convertToUser(authenticatedUserDto);
        final String token = jwtTokenManager.generateToken(user);

        //user get dto

        UserGetDTO userGetDTO = userMapper.toUtilisateurGetDto(user);

        log.info(" {} has successfully logged in!", user.getUsername());

        return new LoginResponse(token,userByEmail.getUsername(),userByEmail.getNom(),userByEmail.getPrenom(),userByEmail.getEmail(),userByEmail.getTelephone(),userByEmail.getUserStatus());
    }

}
