package com.example.proyectosw.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseCharacter {

    @JsonProperty("next")
    private String next;

    @JsonProperty("previous")
    private Object previous;

    @JsonProperty("count")
    private int count;

    @JsonProperty("results")
    private List<Character> results;

    public String getNext() {
        return next;
    }

    public Object getPrevious() {
        return previous;
    }

    public int getCount() {
        return count;
    }

    public List<Character> getResults() {
        return results;
    }
}