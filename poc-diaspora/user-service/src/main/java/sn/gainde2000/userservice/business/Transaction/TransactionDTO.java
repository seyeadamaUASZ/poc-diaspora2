package sn.gainde2000.userservice.business.Transaction;

import sn.gainde2000.userservice.business.Users.User;
import sn.gainde2000.userservice.utils.TransactionStatus;

import java.util.Date;

public class TransactionDTO {
    private Long id;
    private Date dateTransaction;
    private String action;
    private String application;
    private User user;
    private String status;
    private String status1;

    public TransactionDTO(Date dateTransaction, String action, String application, User user, String status, String status1) {
        this.dateTransaction = dateTransaction;
        this.action = action;
        this.application = application;
        this.user = user;
        this.status = status;
        this.status1 = status1;
    }

    public String getStatus1() {
        return status1;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(Date dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TransactionDTO() {
    }
}
