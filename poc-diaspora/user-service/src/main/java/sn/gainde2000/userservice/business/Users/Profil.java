package sn.gainde2000.userservice.business.Users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="profil")
public class Profil {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	  private String nomProfil;
	  
	  
	public Profil(Long id, String nomProfil) {
		super();
		this.id = id;
		this.nomProfil = nomProfil;
	}
	
	public Profil() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomProfil() {
		return nomProfil;
	}
	public void setNomProfil(String nomProfil) {
		this.nomProfil = nomProfil;
	}
	  
	  
}
