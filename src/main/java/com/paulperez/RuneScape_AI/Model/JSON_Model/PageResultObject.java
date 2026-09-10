package com.paulperez.RuneScape_AI.Model.JSON_Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PageResultObject {

    // @JsonProperty tells spring what the JSON field name will be
    @JsonProperty("pageid")
    private int pageId;
    @JsonProperty("ns")
    private int ns;
    @JsonProperty("title")
    private String titleString;
    @JsonProperty("revisions")
    private List<RevisionsPackageObject> revisionsList;

    // full constructor
    public PageResultObject(int pageId, int ns, String titleString, List<RevisionsPackageObject> revisionsList) {
        this.pageId = pageId;
        this.ns = ns;
        this.titleString = titleString;
        this.revisionsList = revisionsList;
    }

    // empty constructor
    public PageResultObject() {
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public int getNs() {
        return ns;
    }

    public void setNs(int ns) {
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
}
