package com.paulperez.RuneScape_AI.Model.AI_Model;

import com.paulperez.RuneScape_AI.Model.Chunk;

public class ChunkDistance {

    private float distance;
    private Chunk chunk;


    public ChunkDistance(float distance, Chunk chunk) {
        this.distance = distance;
        this.chunk = chunk;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public Chunk getChunk() {
        return chunk;
    }

    public void setChunk(Chunk chunk) {
        this.chunk = chunk;
    }


}
