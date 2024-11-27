package com.ig5.iwa.avis.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "avis")
public class Avis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avis")
    private Long idAvis;

    @Column(name = "idActivite", nullable = false)
    private Long idActivite;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Integer note;

    @Column(length = 500)
    private String commentaire;

    @Column(name = "date_avis", nullable = false)
    private LocalDate dateAvis;

    // Getters et Setters
    public Long getIdAvis() {
        return idAvis;
    }

    public void setIdAvis(Long idAvis) {
        this.idAvis = idAvis;
    }

    public Long getIdActivite() {
        return idActivite;
    }

    public void setIdActivite(Long idActivite) {
        this.idActivite = idActivite;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public LocalDate getDateAvis() {
        return dateAvis;
    }

    public void setDateAvis(LocalDate dateAvis) {
        this.dateAvis = dateAvis;
    }
}
