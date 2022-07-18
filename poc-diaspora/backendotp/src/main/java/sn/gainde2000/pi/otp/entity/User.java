package sn.gainde2000.pi.otp.entity;

import javax.persistence.Column;

public class User {
    private Long id;
    private String prenom;
    private String nom;
    private String email;
    private String username;
    private String telephone;
    private String password;

    private UserStatus userStatus;
}
