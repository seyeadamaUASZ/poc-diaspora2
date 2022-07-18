package sn.gainde2000.rvmedicalservice.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
public class RVMedical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String prenom;
    private String nom;

    private Date dateNaissance;
    private String lieuNaissange;
    private int age;
    private String telephone;
    private String sexe;
    private String email;
    private String adresse;
    private String specialite;
    private String profession;
    private boolean delivre=false;

    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] ticketRv;

    @Temporal(TemporalType.TIMESTAMP)
    private Date rv;

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

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissange() {
        return lieuNaissange;
    }

    public void setLieuNaissange(String lieuNaissange) {
        this.lieuNaissange = lieuNaissange;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Date getRv() {
        return rv;
    }

    public void setRv(Date rv) {
        this.rv = rv;
    }

    public byte[] getTicketRv() {
        return ticketRv;
    }

    public void setTicketRv(byte[] ticketRv) {
        this.ticketRv = ticketRv;
    }

    public boolean isDelivre() {
        return delivre;
    }

    public void setDelivre(boolean delivre) {
        this.delivre = delivre;
    }
}
