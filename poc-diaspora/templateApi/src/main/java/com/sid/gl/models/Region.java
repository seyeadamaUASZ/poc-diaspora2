package com.sid.gl.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Region {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("status")
    private Long status;
    @JsonProperty("poOwner")
    private Long poOwner;
    @JsonProperty("owner")
    private Long owner;
    @JsonProperty("idLink")
    private Long idLink;
    @JsonProperty("codeRegion")
    private String codeRegion;
    @JsonProperty("nomRegion")
    private String nomRegion;
}
