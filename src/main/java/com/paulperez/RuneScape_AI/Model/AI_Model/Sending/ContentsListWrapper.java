package com.paulperez.RuneScape_AI.Model.AI_Model.Sending;

import java.util.List;

public class ContentsListWrapper {

    private List<Content> contents;

    public ContentsListWrapper(List<Content> contents) {
        this.contents = contents;
    }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }
}
