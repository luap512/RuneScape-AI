package com.paulperez.RuneScape_AI.Model.JSON_Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PageResultObject {

    // @JsonProperty tells spring what the JSON field name will be
    @JsonProperty("pageid")
    private Integer pageId;

    @JsonProperty("ns")
    private Integer ns;

    @JsonProperty("title")
    private String titleString;

    @JsonProperty("revisions")
    private List<RevisionsPackageObject> revisionsList;


    @JsonProperty("missing")
    private String missing;

    // full constructor
    public PageResultObject(Integer pageId, Integer ns, String titleString, List<RevisionsPackageObject> revisionsList, String missing) {
        this.pageId = pageId;
        this.ns = ns;
        this.titleString = titleString;
        this.revisionsList = revisionsList;
        this.missing = missing;
    }

    // empty constructor
    public PageResultObject() {
    }

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public Integer getNs() {
        return ns;
    }

    public void setNs(Integer ns) {
        this.ns = ns;
    }

    public String getTitleString() {
        return titleString;
    }

    public void setTitleString(String titleString) {
        this.titleString = titleString;
    }

    public List<RevisionsPackageObject> getRevisionsList() {
        return revisionsList;
    }

    public void setRevisionsList(List<RevisionsPackageObject> revisionsList) {
        this.revisionsList = revisionsList;
    }

    public String getMissing() {
        return missing;
    }

    public void setMissing(String missing) {
        this.missing = missing;
    }
}
