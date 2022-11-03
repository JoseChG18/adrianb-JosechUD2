package com.example.proyectosw.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseSpecies {

    @JsonProperty("next")
    private String next;

    @JsonProperty("previous")
    private Object previous;

    @JsonProperty("count")
    private int count;

    @JsonProperty("results")
    private List<Species> results;

    public String getNext() {
        return next;
    }

    public Object getPrevious() {
        return previous;
    }

    public int getCount() {
        return count;
    }

    public List<Species> getResults() {
        return results;
    }
}