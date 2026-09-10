package com.paulperez.RuneScape_AI.Model.JSON_Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JSONPackageObject {

    @JsonProperty("batchcomplete")
    private String batchComplete;
    @JsonProperty("query")
    private QueryPackageObject queryPackageObject;

    // EMPTY CONSTRUCTOR
    public JSONPackageObject() {
    }

    // FULL CONSTRUCTOR
    public JSONPackageObject(String batchComplete, QueryPackageObject queryPackageObject) {
        this.batchComplete = batchComplete;
        this.queryPackageObject = queryPackageObject;
    }

    // GETTERS AND SETTERS
    public String getBatchComplete() {
        return batchComplete;
    }

    public void setBatchComplete(String batchComplete) {
        this.batchComplete = batchComplete;
    }

    public QueryPackageObject getQueryPackageObject() {
        return queryPackageObject;
    }

    public void setQueryPackageObject(QueryPackageObject queryPackageObject) {
        this.queryPackageObject = queryPackageObject;
    }
}
