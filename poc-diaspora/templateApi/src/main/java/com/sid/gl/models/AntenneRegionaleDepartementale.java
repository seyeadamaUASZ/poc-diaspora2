package com.sid.gl.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AntenneRegionaleDepartementale {
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
    @JsonProperty("codeAntenne")
    private String codeAntenne;
    @JsonProperty("nomAntenne")
    private String nomAntenne;
    @JsonProperty("region")
    private Region region;
}
