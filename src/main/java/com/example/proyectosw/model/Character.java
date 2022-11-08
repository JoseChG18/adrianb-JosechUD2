package com.example.proyectosw.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Button;

public class Character implements Serializable {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("homeworld")
    private String homeworld;
    @JsonProperty("skin_color")
    private String skinColor;
    @JsonProperty("hair_color")
    private String hairColor;
    private Button eliminar;
    private Button agregar;
    private Button modificar;

    public Character(int ID, String NAME, String GENDER, String SKIN_COLOR, String HOMEWORLD, String HAIR_COLOR){
        this.id = ID;
        this.name = NAME;
        this.gender = GENDER;
        this.skinColor = SKIN_COLOR;
        this.homeworld = HOMEWORLD;
        this.hairColor = HAIR_COLOR;
        eliminar = new Button("Eliminar");
        agregar = new Button("Agregar");
        modificar = new Button("Modificar");
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return this.name;
    }
    public String getHomeworld() {
        return homeworld;
    }
    public String getGender() {
        return gender;
    }
    public String getSkinColor() {
        return skinColor;
    }
    public String getHairColor() {
        return hairColor;
    }
    public Button getAgregar() {
        return agregar;
    }
    public Button getEliminar() {
        return eliminar;
    }
    public Button getModificar() {
        return modificar;
    }

    /*
    @JsonProperty("birth_year")
    private String birthYear;
    @JsonProperty("height")
    private String height;
    @JsonProperty("mass")
    private String mass;
    @JsonProperty("vehicles")
    private List<String> vehicles;
    @JsonProperty("eye_color")
    private String eyeColor;
    @JsonProperty("species")
    private List<Object> species;
    @JsonProperty("starships")
    private List<String> starships;
    @JsonProperty("films")
    private List<String> films;
    @JsonProperty("edited")
    private String edited;
    @JsonProperty("url")
    private String url;
    @JsonProperty("created")
    private String created;

    public List<String> getFilms() {
        return films;
    }
    public String getEdited() {
        return edited;
    }
    public String getCreated() {
        return created;
    }
    public String getMass() {
        return mass;
    }
    public List<String> getVehicles() {
        return vehicles;
    }
    public String getUrl() {
        return url;
    }
    public String getBirthYear() {
        return birthYear;
    }
    public String getEyeColor() {
        return eyeColor;
    }
    public List<Object> getSpecies() {
        return species;
    }
    public List<String> getStarships() {
        return starships;
    }
    public String getHeight() {
        return height;
    }
    */
}