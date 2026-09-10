package com.paulperez.RuneScape_AI.Model.JSON_Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class QueryPackageObject {

    // @JsonProperty tells spring what the JSON field name will be
    @JsonProperty("normalized")
    private List<Object> normalized;

    @JsonProperty("pages")
    private Map<String, PageResultObject> pageResultObjectMap;

    // full constructor
    public QueryPackageObject(List<Object> normalized, Map<String, PageResultObject> pageResultObjectMap) {
        this.normalized = normalized;
        this.pageResultObjectMap = pageResultObjectMap;
    }

    // empty constructor
    public QueryPackageObject() {
    }

    // GETTERS AND SETTERS
    public List<Object> getNormalized() {
        return normalized;
    }

    public void setNormalized(List<Object> normalized) {
        this.normalized = normalized;
    }

    public Map<String, PageResultObject> getPageResultObjectMap() {
        return pageResultObjectMap;
    }

    public void setPageResultObjectMap(Map<String, PageResultObject> pageResultObjectMap) {
        this.pageResultObjectMap = pageResultObjectMap;
    }
}
