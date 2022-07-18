package com.sid.gl.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produits {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("status")
    private Long status;
    @JsonProperty("nature")
    private String nature;
    @JsonProperty("marque")
    private String marque;
    @JsonProperty("contenance")
    private String contenance;
    @JsonProperty("typeemballage")
    private String typeemballage;
    @JsonProperty("descriptionEtiquette")
    private String descriptionEtiquette;
    @JsonProperty("etiquetteouemballage")
    private String etiquetteouemballage;
    @JsonProperty("autFra")
    private String autFra;
    @JsonProperty("categorie")
    private Categorie categorie;
}
