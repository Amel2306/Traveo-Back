package com.ig5.iwa.themes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "themes")
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTheme;

    @Column(nullable = false, unique = true)
    private String label;

    private String description;

    @JsonProperty("image_default") // Permet de mapper "image_default" du JSON à "imageDefault" en Java
    private String imageDefault;

    // Getters et Setters
    public Long getIdTheme() {
        return idTheme;
    }

    public void setIdTheme(Long idTheme) {
        this.idTheme = idTheme;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageDefault() {
        return imageDefault;
    }

    public void setImageDefault(String imageDefault) {
        this.imageDefault = imageDefault;
    }
}


