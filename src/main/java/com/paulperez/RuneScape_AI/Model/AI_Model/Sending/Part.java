package com.paulperez.RuneScape_AI.Model.AI_Model.Sending;

public class Part {


    // empty string to hold text content
    private String text = "";


    public Part(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
