package sn.gainde2000.userservice.utils;

public class TransactionPojo {
    private Long nombretransaction;
    private String service;

    public TransactionPojo(Long nombretransaction, String service) {
        this.nombretransaction = nombretransaction;
        this.service = service;
    }

    public TransactionPojo() {
    }

    public Long getNombretransaction() {
        return nombretransaction;
    }

    public void setNombretransaction(Long nombretransaction) {
        this.nombretransaction = nombretransaction;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
