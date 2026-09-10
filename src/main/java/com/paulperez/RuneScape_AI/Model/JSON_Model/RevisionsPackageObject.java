package com.paulperez.RuneScape_AI.Model.JSON_Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RevisionsPackageObject {

    // @JsonProperty tells spring what the JSON field name will be
    @JsonProperty("slots")
    private SlotsObject slotsObject;

    // full constructor
    public RevisionsPackageObject(SlotsObject slotsObject) {
        this.slotsObject = slotsObject;
    }

    // empty constructor
    public RevisionsPackageObject() {
    }

    // GETTERS AND SETTERS
    public SlotsObject getSlotsObject() {
        return slotsObject;
    }

    public void setSlotsObject(SlotsObject slotsObject) {
        this.slotsObject = slotsObject;
    }

}
