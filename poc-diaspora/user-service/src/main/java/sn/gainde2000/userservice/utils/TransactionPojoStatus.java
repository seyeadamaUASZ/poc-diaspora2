package sn.gainde2000.userservice.utils;

public class TransactionPojoStatus {
    private Long nombretransactionStatusEncours;
    private Long nombretransactionStatusTermine;
    private String service;
    private String status;
    private String status1;


    public TransactionPojoStatus(Long nombretransactionStatusEncours, Long nombretransactionStatusTermine, String service, String status, String status1) {
        this.nombretransactionStatusEncours = nombretransactionStatusEncours;
        this.nombretransactionStatusTermine = nombretransactionStatusTermine;
        this.service = service;
        this.status = status;
        this.status1 = status1;
    }

    public TransactionPojoStatus(Long nombretransactionStatusEncours, Long nombretransactionStatusTermine, String service) {
        this.nombretransactionStatusEncours = nombretransactionStatusEncours;
        this.nombretransactionStatusTermine = nombretransactionStatusTermine;
        this.service = service;
    }

    public Long getNombretransactionStatusTermine() {
        return nombretransactionStatusTermine;
    }

    public void setNombretransactionStatusTermine(Long nombretransactionStatusTermine) {
        this.nombretransactionStatusTermine = nombretransactionStatusTermine;
    }

    public Long getNombretransactionStatusEncours() {
        return nombretransactionStatusEncours;
    }

    public void setNombretransactionStatusEncours(Long nombretransactionStatusEncours) {
        this.nombretransactionStatusEncours = nombretransactionStatusEncours;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus1() {
        return status1;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }
}
