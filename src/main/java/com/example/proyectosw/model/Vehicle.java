package com.example.proyectosw.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Vehicle implements Serializable {

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
    @JsonProperty("passengers")
    private String passengers;
    @JsonProperty("cargo_capacity")
    private String cargoCapacity;
    @JsonProperty("vehicle_class")
    private String vehicleClass;

    public Vehicle(int ID, String NAME, String MODEL, String MANUFACTURER, String COSTINCREDITS, String LENGTH, String PASSENGERS, String CARGOCAPACITY, String VEHICLECLASS){
        this.id = ID;
        this.name = NAME;
        this.model = MODEL;
        this.manufacturer = MANUFACTURER;
        this.costInCredits = COSTINCREDITS;
        this.length = LENGTH;
        this.passengers = PASSENGERS;
        this.cargoCapacity = CARGOCAPACITY;
        this.vehicleClass = VEHICLECLASS;
    }
    public int getId() {
        return id;
    }

    public String getCargoCapacity() {
        return cargoCapacity;
    }

    public String getPassengers() {
        return passengers;
    }

    public String getLength() {
        return length;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getVehicleClass() {
        return vehicleClass;
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
    @JsonProperty("pilots")
    private List<Object> pilots;
    @JsonProperty("edited")
    private String edited;
    @JsonProperty("consumables")
    private String consumables;
    @JsonProperty("created")
    private String created;
    @JsonProperty("url")
    private String url;
    @JsonProperty("crew")
    private String crew;
    public String getMaxAtmospheringSpeed() {
        return maxAtmospheringSpeed;
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

    public String getCreated() {
        return created;
    }
    public String getCrew() {
        return crew;
    }

    public String getUrl() {
        return url;
    }

    public List<String> getFilms() {
        return films;
    }

     */
}