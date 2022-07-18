package com.sid.gl.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServeurResponse {
    private boolean statut ;
    private String description ;
    private Object data ;
    private List<String> notifInfos;
}
