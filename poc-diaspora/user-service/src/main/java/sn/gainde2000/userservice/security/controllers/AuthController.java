package sn.gainde2000.userservice.security.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sn.gainde2000.userservice.business.Users.IUser;
import sn.gainde2000.userservice.security.dto.*;
import sn.gainde2000.userservice.security.jwt.JwtTokenService;

@RestController
public class AuthController {
	  private final JwtTokenService jwtTokenService;
	    private final IUser iUtilisateurs;

	    public AuthController(JwtTokenService jwtTokenService, IUser iUtilisateurs) {
	        this.jwtTokenService = jwtTokenService;
	        this.iUtilisateurs = iUtilisateurs;
	    }

	    //For testing auth
	    /*@PostMapping("login")
	    public ResponseEntity<LoginResponse> loginRequest(@Valid @RequestBody LoginRequest loginRequest) {

	        final LoginResponse loginResponse = jwtTokenService.getLoginResponse(loginRequest);

	        return ResponseEntity.ok(loginResponse);
	    }*/

	 //Check login by email

	@PostMapping("auth/login")
	public ResponseEntity<LoginResponse> loginRequest(@Valid @RequestBody LoginRequest1 loginRequest) {

		final LoginResponse loginResponse = jwtTokenService.getLoginResponse1(loginRequest);

		return ResponseEntity.ok(loginResponse);
	}

	    //for registration

	    @PostMapping("auth/register")
	    public ResponseEntity<RegistrationResponse> registrationRequest(@Valid @RequestBody RegistrationRequest registrationRequest) {

	        final RegistrationResponse registrationResponse = iUtilisateurs.registration(registrationRequest);

	        return ResponseEntity.status(HttpStatus.CREATED).body(registrationResponse);
	    }
}
