package sn.gainde2000.userservice.security.dto;

import sn.gainde2000.userservice.business.Users.dtos.UserGetDTO;

public class RegistrationResponse {
	private String message;
	private UserGetDTO user;

    public RegistrationResponse(String message, UserGetDTO userGetDTO) {
        this.message = message;
        this.user = userGetDTO;
    }



    public RegistrationResponse(String message) {
        this.message = message;
    }

    public RegistrationResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserGetDTO getUserGetDTO() {
        return user;
    }

    public void setUserGetDTO(UserGetDTO userGetDTO) {
        this.user = userGetDTO;
    }
}
