package org.jethro.parametrage.api.dto;

import java.time.LocalDateTime;

public class Person extends BaseEntityDTO{
    
    private String prenom;
    private String nom;
    private LocalDateTime dateNaissance;
    private String pathPhoto;
    private String email;
    private Sexe sexe;
    private SituationMatrimoniale situationMatrimoniale;
    private Profession profession;
    private NeighborhoodDto neighborhoodDto;

    public Person() {
    }

    public Person(String prenom, String nom, LocalDateTime dateNaissance, String pathPhoto, String email, Sexe sexe, SituationMatrimoniale situationMatrimoniale, Profession profession, NeighborhoodDto neighborhoodDto) {
        this.prenom = prenom;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.pathPhoto = pathPhoto;
        this.email = email;
        this.sexe = sexe;
        this.situationMatrimoniale = situationMatrimoniale;
        this.profession = profession;
        this.neighborhoodDto = neighborhoodDto;
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

    public LocalDateTime getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDateTime dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getPathPhoto() {
        return pathPhoto;
    }

    public void setPathPhoto(String pathPhoto) {
        this.pathPhoto = pathPhoto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public SituationMatrimoniale getSituationMatrimoniale() {
        return situationMatrimoniale;
    }

    public void setSituationMatrimoniale(SituationMatrimoniale situationMatrimoniale) {
        this.situationMatrimoniale = situationMatrimoniale;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public NeighborhoodDto getQuartier() {
        return neighborhoodDto;
    }

    public void setQuartier(NeighborhoodDto neighborhoodDto) {
        this.neighborhoodDto = neighborhoodDto;
    }

    @Override
    public String toString() {
        return "Person{" +
                "uuid='" + uuid + '\'' +
                ", code='" + code + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", pathPhoto='" + pathPhoto + '\'' +
                ", email='" + email + '\'' +
                ", sexe=" + sexe +
                ", situationMatrimoniale=" + situationMatrimoniale +
                ", profession=" + profession +
                ", neighborhoodDto=" + neighborhoodDto +
                '}';
    }
}

