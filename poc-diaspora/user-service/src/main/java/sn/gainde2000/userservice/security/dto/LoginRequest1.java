package sn.gainde2000.userservice.security.dto;

import javax.validation.constraints.NotEmpty;

public class LoginRequest1 {
    @NotEmpty(message = "{login_email_not_empty}")
    private String email;

    @NotEmpty(message = "{login_password_not_empty}")
    private String password;

    public LoginRequest1(@NotEmpty(message = "{login_email_not_empty}") String email, @NotEmpty(message = "{login_password_not_empty}") String password) {
        this.email = email;
        this.password = password;
    }

    public LoginRequest1() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
