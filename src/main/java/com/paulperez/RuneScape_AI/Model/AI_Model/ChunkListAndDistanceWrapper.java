package com.paulperez.RuneScape_AI.Model.AI_Model;

import com.paulperez.RuneScape_AI.Model.Chunk;

import java.util.List;

public class ChunkListAndDistanceWrapper {

    private List<Chunk> chunkList;
    private float closestDistance;

    public ChunkListAndDistanceWrapper(List<Chunk> chunkList, float closestDistance) {
        this.chunkList = chunkList;
        this.closestDistance = closestDistance;
    }


    public List<Chunk> getChunkList() {
        return chunkList;
    }

    public void setChunkList(List<Chunk> chunkList) {
        this.chunkList = chunkList;
    }

    public float getClosestDistance() {
        return closestDistance;
    }

    public void setClosestDistance(float closestDistance) {
        this.closestDistance = closestDistance;
    }
}
