package com.example.proyectosw.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Starship implements Serializable {

    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("model")
    private String model;
    @JsonProperty("manufacturer")
    private String manufacturer;
    @JsonProperty("cost_in_credits")
    private String costInCredits;
    @JsonProperty("length")
    private String length;
    @JsonProperty("cargo_capacity")
    private String cargoCapacity;
    @JsonProperty("starship_class")
    private String starshipClass;
    @JsonProperty("hyperdrive_rating")
    private String hyperdriveRating;

    public Starship(int ID, String NAME, String MODEL, String MANUFACTURER, String COSTINCREDITS, String LENGTH, String CARGOCAPACITY, String STARSHIPCLASS, String HYPERDRIVERATING){
        this.id = ID;
        this.name = NAME;
        this.model = MODEL;
        this.manufacturer = MANUFACTURER;
        this.costInCredits = COSTINCREDITS;
        this.length = LENGTH;
        this.cargoCapacity = CARGOCAPACITY;
        this.starshipClass = STARSHIPCLASS;
        this.hyperdriveRating = HYPERDRIVERATING;
    }

    public int getId() {
        return id;
    }

    public String getCargoCapacity() {
        return cargoCapacity;
    }

    public String getLength() {
        return length;
    }

    public String getStarshipClass() {
        return starshipClass;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getHyperdriveRating() {
        return hyperdriveRating;
    }

    public String getCostInCredits() {
        return costInCredits;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    /*
    @JsonProperty("max_atmosphering_speed")
    private String maxAtmospheringSpeed;
    @JsonProperty("films")
    private List<String> films;
    @JsonProperty("passengers")
    private String passengers;
    @JsonProperty("pilots")
    private List<Object> pilots;
    @JsonProperty("edited")
    private String edited;
    @JsonProperty("consumables")
    private String consumables;
    @JsonProperty("MGLT")
    private String mGLT;
    @JsonProperty("created")
    private String created;
    @JsonProperty("url")
    private String url;
    @JsonProperty("crew")
    private String crew;
    public String getMaxAtmospheringSpeed() {
        return maxAtmospheringSpeed;
    }

    public List<String> getFilms() {
        return films;
    }

    public String getPassengers() {
        return passengers;
    }

    public List<Object> getPilots() {
        return pilots;
    }

    public String getEdited() {
        return edited;
    }

    public String getConsumables() {
        return consumables;
    }

    public String getMGLT() {
        return mGLT;
    }

    public String getCreated() {
        return created;
    }

    public String getCrew() {
        return crew;
    }

    public String getUrl() {
        return url;
    }
     */
}