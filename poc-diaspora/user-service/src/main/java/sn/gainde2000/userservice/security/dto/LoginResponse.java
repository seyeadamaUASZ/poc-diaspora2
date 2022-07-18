package sn.gainde2000.userservice.security.dto;

import sn.gainde2000.userservice.business.Users.User;
import sn.gainde2000.userservice.business.Users.dtos.UserGetDTO;
import sn.gainde2000.userservice.utils.UserStatus;

public class LoginResponse {
	
	private String token;
	private String username;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private UserStatus userStatus;

    public LoginResponse(String token, String username, String nom, String prenom, String email, String telephone, UserStatus userStatus) {
        this.token = token;
        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.userStatus = userStatus;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LoginResponse(String token) {
        this.token = token;
    }

    public LoginResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
