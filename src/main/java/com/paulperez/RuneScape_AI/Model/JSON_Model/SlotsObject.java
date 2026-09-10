package com.paulperez.RuneScape_AI.Model.JSON_Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SlotsObject {

    // @JsonProperty tells spring what the JSON field name will be
    @JsonProperty("main")
    private MainObject mainObject;

    // full constructor
    public SlotsObject(MainObject mainObject) {
        this.mainObject = mainObject;
    }

    // empty constructor
    public SlotsObject() {
    }

    // GETTERS AND SETTERS
    public MainObject getMainObject() {
        return mainObject;
    }

    public void setMainObject(MainObject mainObject) {
        this.mainObject = mainObject;
    }
}
