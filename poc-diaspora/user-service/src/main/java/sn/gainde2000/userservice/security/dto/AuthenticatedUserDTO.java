package sn.gainde2000.userservice.security.dto;

import sn.gainde2000.userservice.business.Users.Profil;

public class AuthenticatedUserDTO {
	    private String name;
	    private String username;
	    private String password;
	    private Profil profil;
	    
	    
	    
		public Profil getProfil() {
			return profil;
		}
		public void setProfil(Profil profil) {
			this.profil = profil;
		}
		public String getName() {
			return name;
		}
		public AuthenticatedUserDTO() {
			super();
			// TODO Auto-generated constructor stub
		}
		public AuthenticatedUserDTO(String name, String username, String password) {
			super();
			this.name = name;
			this.username = username;
			this.password = password;
		}
		public void setName(String name) {
			this.name = name;
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
