package com.paulperez.RuneScape_AI.Model.AI_Model.Sending;


public class EmbedRequest {


    private Content content;

    public EmbedRequest(Content content) {
        this.content = content;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

}
