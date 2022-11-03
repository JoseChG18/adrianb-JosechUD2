package com.example.proyectosw.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Film implements Serializable {

    @JsonProperty("edited")
    private String edited;

    @JsonProperty("director")
    private String director;

    @JsonProperty("created")
    private String created;

    @JsonProperty("vehicles")
    private List<String> vehicles;

    @JsonProperty("opening_crawl")
    private String openingCrawl;

    @JsonProperty("title")
    private String title;

    @JsonProperty("url")
    private String url;

    @JsonProperty("characters")
    private List<String> characters;

    @JsonProperty("episode_id")
    private int episodeId;

    @JsonProperty("planets")
    private List<String> planets;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("starships")
    private List<String> starships;

    @JsonProperty("species")
    private List<String> species;

    @JsonProperty("producer")
    private String producer;

    public String getEdited() {
        return edited;
    }

    public String getDirector() {
        return director;
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

    public String getTitle() {
        return title;
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public List<String> getStarships() {
        return starships;
    }

    public List<String> getSpecies() {
        return species;
    }

    public String getProducer() {
        return producer;
    }
}