package sn.gainde2000.permisservice.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Permis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String prenom;
    private String nom;

    private Date dateNaissance;
    private Date dateDelivrance;
    private Date dateExpiration;
    private String numeroPermis;

    private boolean delivre =false;
    private String categoriePermis;
    private String groupeSanguin;
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] img;
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] nouveauPermis;



    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public byte[] getNouveauPermis() {
        return nouveauPermis;
    }

    public void setNouveauPermis(byte[] nouveauPermis) {
        this.nouveauPermis = nouveauPermis;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Date getDateDelivrance() {
        return dateDelivrance;
    }

    public void setDateDelivrance(Date dateDelivrance) {
        this.dateDelivrance = dateDelivrance;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public String getNumeroPermis() {
        return numeroPermis;
    }

    public void setNumeroPermis(String numeroPermis) {
        this.numeroPermis = numeroPermis;
    }

    public String getGroupeSanguin() {
        return groupeSanguin;
    }

    public void setGroupeSanguin(String groupeSanguin) {
        this.groupeSanguin = groupeSanguin;
    }

    public String getCategoriePermis() {
        return categoriePermis;
    }

    public void setCategoriePermis(String categoriePermis) {
        this.categoriePermis = categoriePermis;
    }

    public boolean isDelivre() {
        return delivre;
    }

    public void setDelivre(boolean delivre) {
        this.delivre = delivre;
    }
}
