package com.paulperez.RuneScape_AI.Model.AI_Model.Sending;

import java.util.ArrayList;
import java.util.List;

public class Content {

    // empty parts array to hold parts objects
    private List<Part> parts = new ArrayList<>();

    // CONSTRUCTOR
    public Content(List<Part> parts) {
        this.parts = parts;
    }

    // GETTERS N SETTERS
    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
