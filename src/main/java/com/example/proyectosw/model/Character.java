package com.example.proyectosw.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Character implements Serializable {
    @JsonProperty("name")
    private String name;
    @JsonProperty("birth_year")
    private String birthYear;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("homeworld")
    private String homeworld;
    @JsonProperty("skin_color")
    private String skinColor;
    @JsonProperty("height")
    private String height;
    @JsonProperty("mass")
    private String mass;
    @JsonProperty("vehicles")
    private List<String> vehicles;
    @JsonProperty("hair_color")
    private String hairColor;
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

    public String getHomeworld() {
        return homeworld;
    }

    public void setHomeworld(String nameHomeworld) {
        this.homeworld = nameHomeworld;
    }

    public String getGender() {
        return gender;
    }

    public String getSkinColor() {
        return skinColor;
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

    public String getHairColor() {
        return hairColor;
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

    public String getName() {
        return this.name;
    }

    public String getHeight() {
        return height;
    }
}