package sn.gainde2000.userservice.business.Users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import sn.gainde2000.userservice.utils.UserStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="utilisateurs")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String prenom;
  private String nom;
  @Column(unique = true)
  private String email;
  @Column(unique=true)
  private String username;
  @Column(unique=true)
  private String telephone;
  @JsonIgnore
  private String password;

  private UserStatus userStatus;

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	@ManyToOne
  @JoinColumn(name = "profil_id")
  private Profil profil;
  

public Profil getProfil() {
	return profil;
}
public void setProfil(Profil profil) {
	this.profil = profil;
}
public User() {
	super();
	// TODO Auto-generated constructor stub
}
public User(String prenom, String nom, String email, String username, String telephone, String password) {
	super();
	this.prenom = prenom;
	this.nom = nom;
	this.email = email;
	this.username = username;
	this.telephone = telephone;
	this.password = password;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getPrenom() {
	return prenom;
}
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
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
public String getTelephone() {
	return telephone;
}
public void setTelephone(String telephone) {
	this.telephone = telephone;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
  
  
  
  
}
