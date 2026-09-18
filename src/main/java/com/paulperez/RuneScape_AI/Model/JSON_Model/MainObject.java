package com.paulperez.RuneScape_AI.Model.JSON_Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MainObject {

    // @JsonProperty tells spring what the JSON field name will be
    @JsonProperty("contentmodel")
    private String contentModel;

    @JsonProperty("contentformat")
    private String contentFormat;

    @JsonProperty("*")
    private String textContent;

    // full constructor
    public MainObject(String contentModel, String contentFormat, String textContent) {
        this.contentModel = contentModel;
        this.contentFormat = contentFormat;
        this.textContent = textContent;
    }

    // empty constructor
    public MainObject() {
    }

    // GETTERS AND SETTERS
    public String getContentModel() {
        return contentModel;
    }

    public void setContentModel(String contentModel) {
        this.contentModel = contentModel;
    }

    public String getContentFormat() {
        return contentFormat;
    }

    public void setContentFormat(String contentFormat) {
        this.contentFormat = contentFormat;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
}
