package com.example.proyectosw.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.scene.control.Button;

public class Film implements Serializable {

    @JsonProperty("id")
    private int id;
    @JsonProperty("director")
    private String director;
    @JsonProperty("title")
    private String title;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("producer")
    private String producer;
    @JsonIgnore
    private final Button eliminar;

    public Film(int ID, String TITLE, String DIRECTOR, String RELEASEDATE, String PRODUCER){
        this.id = ID;
        this.title = TITLE;
        this.director = DIRECTOR;
        this.releaseDate = RELEASEDATE;
        this.producer = PRODUCER;
        eliminar = new Button("Eliminar");
    }

    public int getId() {
        return id;
    }

    public String getDirector() {
        return director;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getProducer() {
        return producer;
    }
    public Button getEliminar() {
        return eliminar;
    }
    /*
    @JsonProperty("url")
    private String url;
    @JsonProperty("created")
    private String created;
    @JsonProperty("edited")
    private String edited;
    @JsonProperty("vehicles")
    private List<String> vehicles;
    @JsonProperty("opening_crawl")
    private String openingCrawl;
    @JsonProperty("characters")
    private List<String> characters;
    @JsonProperty("episode_id")
    private int episodeId;
    @JsonProperty("planets")
    private List<String> planets;
    @JsonProperty("starships")
    private List<String> starships;
    @JsonProperty("species")
    private List<String> species;

    public List<String> getStarships() {
        return starships;
    }

    public List<String> getSpecies() {
        return species;
    }
    public String getUrl() {
        return url;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public List<String> getPlanets() {
        return planets;
    }
    public String getCreated() {
        return created;
    }

    public List<String> getVehicles() {
        return vehicles;
    }

    public String getOpeningCrawl() {
        return openingCrawl;
    }
    public String getEdited() {
        return edited;
    }
    */
}