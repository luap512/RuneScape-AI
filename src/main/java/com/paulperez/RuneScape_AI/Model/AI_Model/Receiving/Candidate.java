package com.paulperez.RuneScape_AI.Model.AI_Model.Receiving;

import com.paulperez.RuneScape_AI.Model.AI_Model.Sending.Content;

public class Candidate {

    private Content content;

    public Candidate(Content content) {
        this.content = content;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
