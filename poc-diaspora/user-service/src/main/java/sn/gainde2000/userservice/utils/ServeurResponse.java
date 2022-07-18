package sn.gainde2000.userservice.utils;

import java.util.List;

public class ServeurResponse {
    private boolean statut ;
    private String description ;
    private Object data ;
    private List<String> notifInfos;


    public ServeurResponse() {
    }

    public ServeurResponse(boolean statut, String description, Object data, List<String> notifInfos) {
        super();
        this.statut = statut;
        this.description = description;
        this.data = data;
        this.notifInfos = notifInfos;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<String> getNotifInfos() {
        return notifInfos;
    }

    public void setNotifInfos(List<String> notifInfos) {
        this.notifInfos = notifInfos;
    }
}
