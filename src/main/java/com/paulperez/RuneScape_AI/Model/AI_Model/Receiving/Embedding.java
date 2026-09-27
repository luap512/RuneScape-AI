package com.paulperez.RuneScape_AI.Model.AI_Model.Receiving;

import java.util.ArrayList;
import java.util.List;

public class Embedding {

    private List<Float> values = new ArrayList<>();

    public Embedding(List<Float> values) {
        this.values = values;
    }

    public List<Float> getValues() {
        return values;
    }

    public void setValues(List<Float> values) {
        this.values = values;
    }
}
