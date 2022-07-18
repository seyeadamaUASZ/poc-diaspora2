package sn.gainde2000.userservice.security.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class RegistrationRequest {
	    @NotEmpty(message = "{registration_nom_not_empty}")
	    private String nom;

	    @NotEmpty(message = "{registration_prenom_not_empty}")
	    private String prenom;
	    
	    public String getNom() {
	        return nom;
	    }

	    public void setNom(String nom) {
	        this.nom = nom;
	    }

	    public String getPrenom() {
	        return prenom;
	    }

	    public void setPrenom(String prenom) {
	        this.prenom = prenom;
	    }

	    @Email(message = "{registration_email_is_not_valid}")
	    @NotEmpty(message = "{registration_email_not_empty}")
	    private String email;

	    @NotEmpty(message = "{registration_username_not_empty}")
	    private String username;

	    @NotEmpty(message = "{registration_password_not_empty}")
	    private String password;

	    @NotEmpty(message = "{telephone_not_empty}")
	    private String telephone;


	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public RegistrationRequest(String nom, String prenom, String email, String username, String password, String telephone) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.username = username;
		this.password = password;
		this.telephone = telephone;
	}

	public RegistrationRequest() {
	    }



	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    

}
