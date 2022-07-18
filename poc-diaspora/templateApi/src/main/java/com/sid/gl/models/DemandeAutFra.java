package com.sid.gl.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemandeAutFra {
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
    @JsonProperty(" raisonsociale")
    private String raisonsociale;
    @JsonProperty("adresseEntreprise")
    private String adresseEntreprise;
    @JsonProperty("emailEntreprise")
    private String emailEntreprise;
    @JsonProperty("ninea")
    private String ninea;
    @JsonProperty("registrecommerce")
    private String registrecommerce;
    @JsonProperty("statutJuridique")
    private String statutJuridique;
    @JsonProperty("telephoneEntreprise")
    private String telephoneEntreprise;
    @JsonProperty("prenomResponsable")
    private String prenomResponsable;
    @JsonProperty("nomResponsable")
    private String nomResponsable;
    @JsonProperty("civiliteResponsable")
    private String civiliteResponsable;
    @JsonProperty("adresseResponsable")
    private String adresseResponsable;
    @JsonProperty("telephoneResponsable")
    private String telephoneResponsable;
    @JsonProperty("emailResponsable")
    private String emailResponsable;
    @JsonProperty("certificatanalysesproduit")
    private byte[] certificatanalysesproduit;
    @JsonProperty("registrecommerceetcreditmobilier")
    private byte[] registrecommerceetcreditmobilier;
    @JsonProperty("juridique")
    private byte[] juridique;
    @JsonProperty("processusfabrication")
    private byte[] processusfabrication;
    @JsonProperty("copieninea")
    private byte[] copieninea;
    @JsonProperty("cnipasseport")
    private byte[] cnipasseport;
    @JsonProperty("paiementManuel")
    private boolean paiementManuel=false;
    @JsonProperty("recu_paiment")
    private byte[] recuPaiment;
    private String recuFileType;

    @JsonProperty("dateSoumission")
    private Date dateSoumission;
    @JsonProperty("numdemande")
    private String numdemande;
    @JsonProperty("produits")
    private List<Produits> produits;
    private String objetDemande;
    @JsonProperty("antenneRegionaleDepartementale")
    private AntenneRegionaleDepartementale antenneRegionaleDepartementale;
    @JsonProperty("region")
    private Region region;
    private Long numeroFacture;
}
