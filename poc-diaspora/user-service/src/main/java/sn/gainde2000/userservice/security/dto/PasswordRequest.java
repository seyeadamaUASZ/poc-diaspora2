package sn.gainde2000.userservice.security.dto;

public class PasswordRequest {
    private String email;
    private String newpassword;

    public PasswordRequest(String email, String newpassword) {
        this.email = email;
        this.newpassword = newpassword;
    }

    public PasswordRequest() {
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
