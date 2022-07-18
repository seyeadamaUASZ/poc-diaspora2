package sn.gainde2000.userservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import sn.gainde2000.userservice.security.jwt.JwtAuthenticationEntryPoint;
import sn.gainde2000.userservice.security.jwt.JwtAuthenticationFilter;
import sn.gainde2000.userservice.security.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private final UserDetailsServiceImpl userDetailsServiceImpl;

	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	private final JwtAuthenticationEntryPoint unauthorizedHandler;

	public SecurityConfiguration(UserDetailsServiceImpl userDetailsServiceImpl, JwtAuthenticationFilter jwtAuthenticationFilter, JwtAuthenticationEntryPoint unauthorizedHandler) {
		this.userDetailsServiceImpl = userDetailsServiceImpl;
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.unauthorizedHandler = unauthorizedHandler;
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers("/auth/**",
				            "/checkUser/**",
						    "/activateUser/**",
						   "/activateUserByEmail/**",
						 "/verificationCodeForUser/**",
						"/envoyerotpByMail/**",
						"/modifierPassword/**",
						"/verificationCode/**",
						"/getInfo/**",
						"/sendMailOtp/**",
						"/tracability/**",
						"/transactions/**",
						"/activateUtilisateur/**",
						"/desactivateUtilisateur/**",
						"/countUsers/**",
						"/countUsersactivated/**",
						"/countUsersdesactivated/**",
						"/transactionByService/**",
						"/findapp/**",
						"/v2/api-docs","/configuration/ui",
						"/swagger-resources/**",
						"/configuration/security",
						"/swagger-ui.html", "/webjars/**",

						"/actuator/**").permitAll()
				.anyRequest().authenticated().and()
				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
	}
}
