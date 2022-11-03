package com.example.proyectosw.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Species implements Serializable {

    @JsonProperty("films")
    private List<String> films;

    @JsonProperty("skin_colors")
    private String skinColors;

    @JsonProperty("homeworld")
    private String homeworld;

    @JsonProperty("edited")
    private String edited;

    @JsonProperty("created")
    private String created;

    @JsonProperty("eye_colors")
    private String eyeColors;

    @JsonProperty("language")
    private String language;

    @JsonProperty("classification")
    private String classification;

    @JsonProperty("people")
    private List<String> people;

    @JsonProperty("url")
    private String url;

    @JsonProperty("hair_colors")
    private String hairColors;

    @JsonProperty("average_height")
    private String averageHeight;

    @JsonProperty("name")
    private String name;

    @JsonProperty("designation")
    private String designation;

    @JsonProperty("average_lifespan")
    private String averageLifespan;

    public List<String> getFilms() {
        return films;
    }

    public String getSkinColors() {
        return skinColors;
    }

    public String getHomeworld() {
        return homeworld;
    }

    public void setHomeworld(String nameHomeworld) {
        this.homeworld = nameHomeworld;
    }

    public String getEdited() {
        return edited;
    }

    public String getCreated() {
        return created;
    }

    public String getEyeColors() {
        return eyeColors;
    }

    public String getLanguage() {
        return language;
    }

    public String getClassification() {
        return classification;
    }

    public List<String> getPeople() {
        return people;
    }

    public String getUrl() {
        return url;
    }

    public String getHairColors() {
        return hairColors;
    }

    public String getAverageHeight() {
        return averageHeight;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public String getAverageLifespan() {
        return averageLifespan;
    }
}