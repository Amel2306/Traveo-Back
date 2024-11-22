package com.ig5.iwa.activite.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "activites")
public class Activite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActivite;

    @Column(name = "id_user", nullable = false)
    private Long userId;

    @Column(name = "id_theme", nullable = false)
    private Long themeId;

    private String nomActivite;
    private String description;
    private Double latitude;
    private Double longitude;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    private Double prix;
    private Integer nbPlaces;
    private String tags;
    private String image;

    // Getters et Setters


    public Long getIdActivite() {
        return idActivite;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getThemeId() {
        return themeId;
    }

    public String getNomActivite() {
        return nomActivite;
    }

    public String getDescription() {
        return description;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public Double getPrix() {
        return prix;
    }

    public Integer getNbPlaces() {
        return nbPlaces;
    }

    public String getTags() {
        return tags;
    }

    public String getImage() {
        return image;
    }

    public void setIdActivite(Long idActivite) {
        this.idActivite = idActivite;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setThemeId(Long themeId) {
        this.themeId = themeId;
    }

    public void setNomActivite(String nomActivite) {
        this.nomActivite = nomActivite;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public void setNbPlaces(Integer nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
