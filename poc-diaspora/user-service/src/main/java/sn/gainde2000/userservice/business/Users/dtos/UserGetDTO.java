package sn.gainde2000.userservice.business.Users.dtos;

import sn.gainde2000.userservice.utils.UserStatus;

public class UserGetDTO {
	  private Long id;
	  private String nom;
	  private String prenom;
	  private String email;
	  private String telephone;
	  private String username;
	  private UserStatus userStatus;

	  //private UserGetDTO userGetDTO;


	public UserGetDTO(Long id, String nom, String prenom, String email, String telephone, String username, UserStatus userStatus) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.username = username;
		this.userStatus = userStatus;
	}

	public UserGetDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	  
	  
	  
}