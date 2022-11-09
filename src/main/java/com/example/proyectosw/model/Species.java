package com.example.proyectosw.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.scene.control.Button;

public class Species implements Serializable {

    @JsonProperty("id")
    private int id;
    @JsonProperty("homeworld")
    private String homeworld;
    @JsonProperty("language")
    private String language;
    @JsonProperty("classification")
    private String classification;
    @JsonProperty("name")
    private String name;
    @JsonProperty("average_lifespan")
    private String averageLifespan;
    @JsonIgnore
    private final Button eliminar;

    public Species(int ID,String NAME, String CLASSIFICATION, String HOMEWORLD, String LANGUAGE, String AVERAGELIFESPAN){
        this.id = ID;
        this.name = NAME;
        this.classification = CLASSIFICATION;
        this.homeworld = HOMEWORLD;
        this.language = LANGUAGE;
        this.averageLifespan = AVERAGELIFESPAN;
        eliminar = new Button("Eliminar");
    }
    public int getId() {
        return id;
    }
    public String getHomeworld() {
        return homeworld;
    }
    public String getLanguage() {
        return language;
    }
    public String getClassification() {
        return classification;
    }
    public String getName() {
        return name;
    }
    public String getAverageLifespan() {
        return averageLifespan;
    }
    public Button getEliminar() {
        return eliminar;
    }
    /*
    @JsonProperty("average_height")
    private String averageHeight;
    @JsonProperty("designation")
    private String designation;
    @JsonProperty("films")
    private List<String> films;
    @JsonProperty("skin_colors")
    private String skinColors;
    @JsonProperty("edited")
    private String edited;
    @JsonProperty("created")
    private String created;
    @JsonProperty("eye_colors")
    private String eyeColors;
    @JsonProperty("people")
    private List<String> people;
    @JsonProperty("url")
    private String url;
    @JsonProperty("hair_colors")
    private String hairColors;
    public List<String> getFilms() {
        return films;
    }
    public String getSkinColors() {
        return skinColors;
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
    public String getDesignation() {
        return designation;
    }
     */
}