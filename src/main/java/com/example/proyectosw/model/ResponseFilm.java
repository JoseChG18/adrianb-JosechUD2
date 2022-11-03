package com.example.proyectosw.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseFilm {

    @JsonProperty("next")
    private Object next;

    @JsonProperty("previous")
    private Object previous;

    @JsonProperty("count")
    private int count;

    @JsonProperty("results")
    private List<Film> results;

    public Object getNext() {
        return next;
    }

    public Object getPrevious() {
        return previous;
    }

    public int getCount() {
        return count;
    }

    public List<Film> getResults() {
        return results;
    }
}