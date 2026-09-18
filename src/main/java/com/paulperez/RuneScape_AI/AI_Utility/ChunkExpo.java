package com.paulperez.RuneScape_AI.AI_Utility;

import com.paulperez.RuneScape_AI.Model.Chunk;
import com.paulperez.RuneScape_AI.Model.JSON_Model.*;
import com.paulperez.RuneScape_AI.Services.WikiService;
import com.paulperez.RuneScape_AI.Utility.Deserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// object uses wiki service to get data
// object uses chunker to split data from service call into chunks
@Component
public class ChunkExpo {

    private final WikiService wikiService;
    private Chunker chunker;
    private Deserializer deserializer;


    @Autowired
    public ChunkExpo(WikiService wikiService, Chunker chunker, Deserializer deserializer) {
        this.wikiService = wikiService;
        this.chunker = chunker;
        this.deserializer = deserializer;
    }

    public List<String> makeStringList(String pageTitle){

        // make new array list for strings
        List<String> stringList = new ArrayList<>();

        // get jsonpackageobject thru wikiservice
        JSONPackageObject jsonPackageObject = wikiService.getWikiPage(pageTitle);

        // create variable to hold the text from the chunk after deserializtiom
        String chunkString = deserializer.deserialize(jsonPackageObject);

        // list of string chunks
        return chunker.makeChunksList(chunkString);
    }
}
