package com.paulperez.RuneScape_AI.Model.AI_Model.Receiving;

public class EmbedContentResponse {

    private Embedding embedding;

    public EmbedContentResponse(Embedding embedding) {
        this.embedding = embedding;
    }

    public Embedding getEmbedding() {
        return embedding;
    }

    public void setEmbedding(Embedding embedding) {
        this.embedding = embedding;
    }
}
