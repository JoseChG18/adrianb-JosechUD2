package com.example.proyectosw.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.scene.control.Button;

public class Planet implements Serializable {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("terrain")
    private String terrain;
    @JsonProperty("climate")
    private String climate;
    @JsonProperty("population")
    private String population;
    @JsonProperty("gravity")
    private String gravity;
    private final Button eliminar;
    private final Button agregar;
    private final Button modificar;


    public Planet(int ID, String NAME, String TERRAIN, String GRAVITY, String CLIMATE, String POPULATION){
        this.id = ID;
        this.name = NAME;
        this.terrain = TERRAIN;
        this.gravity = GRAVITY;
        this.climate = CLIMATE;
        this.population = POPULATION;
        eliminar = new Button("Eliminar");
        agregar = new Button("Agregar");
        modificar = new Button("Modificar");
    }
    public int getId() {
        return id;
    }

    public String getClimate() {
        return climate;
    }

    public String getPopulation() {
        return population;
    }

    public String getGravity() {
        return gravity;
    }

    public String getName() {
        return name;
    }

    public String getTerrain() {
        return terrain;
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
    @JsonProperty("rotation_period")
    private String rotationPeriod;
    @JsonProperty("orbital_period")
    private String orbitalPeriod;
    @JsonProperty("surface_water")
    private String surfaceWater;
    @JsonProperty("diameter")
    private String diameter;
    @JsonProperty("residents")
    private List<String> residents;
    @JsonProperty("films")
    private List<String> films;
    @JsonProperty("edited")
    private String edited;
    @JsonProperty("created")
    private String created;
    @JsonProperty("url")
    private String url;
    public List<String> getFilms() {
        return films;
    }

    public String getEdited() {
        return edited;
    }

    public String getCreated() {
        return created;
    }
    public String getRotationPeriod() {
        return rotationPeriod;
    }

    public String getUrl() {
        return url;
    }
    public String getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public String getSurfaceWater() {
        return surfaceWater;
    }

    public String getDiameter() {
        return diameter;
    }
    public List<String> getResidents() {
        return residents;
    }
    */
}