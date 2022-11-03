package com.example.proyectosw.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseStarship {

    @JsonProperty("next")
    private String next;

    @JsonProperty("previous")
    private Object previous;

    @JsonProperty("count")
    private int count;

    @JsonProperty("results")
    private List<Starship> results;

    public String getNext() {
        return next;
    }

    public Object getPrevious() {
        return previous;
    }

    public int getCount() {
        return count;
    }

    public List<Starship> getResults() {
        return results;
    }
}