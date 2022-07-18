package sn.gainde2000.userservice.security.services;

import java.util.Collections;
import java.util.Objects;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import sn.gainde2000.userservice.business.Users.IUser;
import sn.gainde2000.userservice.business.Users.Profil;
import sn.gainde2000.userservice.security.dto.AuthenticatedUserDTO;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	 private static final String USERNAME_OR_PASSWORD_INVALID = "Invalid username or password.";

	    private final IUser userService;

	    public UserDetailsServiceImpl(IUser userService) {
	        this.userService = userService;
	    }


	    @Override
	    public UserDetails loadUserByUsername(String username) {

	        final AuthenticatedUserDTO authenticatedUser = userService.findAuthenticatedUtilisateursByUsername(username);

	        if (Objects.isNull(authenticatedUser)) {
	            throw new UsernameNotFoundException(USERNAME_OR_PASSWORD_INVALID);
	        }

	        final String authenticatedUsername = authenticatedUser.getUsername();
	        final String authenticatedPassword = authenticatedUser.getPassword();
	        final Profil userRole = authenticatedUser.getProfil();
	        final SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userRole.getNomProfil());

	        return new User(authenticatedUsername, authenticatedPassword, Collections.singletonList(grantedAuthority));
	    }
}
