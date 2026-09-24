package com.paulperez.RuneScape_AI.AI_Utility;

import com.pgvector.PGvector;
import com.paulperez.RuneScape_AI.DAO.ChunkDAO;
import com.paulperez.RuneScape_AI.Model.Chunk;
import com.paulperez.RuneScape_AI.Model.JSON_Model.*;
import com.paulperez.RuneScape_AI.Model.WikiPage;
import com.paulperez.RuneScape_AI.Services.WikiService;
import com.paulperez.RuneScape_AI.Utility.Deserializer;
import com.paulperez.RuneScape_AI.Utility.WikiPageMaker;
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
    private WikiPageMaker wikiPageMaker;
    private ChunkDAO chunkDAO;


    @Autowired
    public ChunkExpo(WikiService wikiService, Chunker chunker, Deserializer deserializer, WikiPageMaker wikiPageMaker, ChunkDAO chunkDAO) {
        this.wikiService = wikiService;
        this.chunker = chunker;
        this.deserializer = deserializer;
        this.wikiPageMaker = wikiPageMaker;
        this.chunkDAO = chunkDAO;
    }

    public String fetchAndDeserialize (String pageTitle){

        // get jsonpackageobject thru wikiservice
        JSONPackageObject jsonPackageObject = wikiService.getWikiPage(pageTitle);

        // create variable to hold the text from the chunk after deserializtiom
        String chunkString = deserializer.deserialize(jsonPackageObject);

        return chunkString;
    }

    public List<String> makeStringList(String pageTitle){

        // list of string chunks
        return chunker.makeChunksList(fetchAndDeserialize(pageTitle));
    }

    public List<Chunk> makeChunkList(String pageTitle){

        // create empty list of chunks
        List<Chunk> chunkList = new ArrayList<>();

        // create empty list for chunk strings
        List<String> chunkStirngsList = new ArrayList<>();

        // fetch and deserialize the content from the wiki page based on the title
        // save the content from the page into wikiPage Content variable
        String wikiPageContent = fetchAndDeserialize(pageTitle);

        // 'make' and fetch the wiki page in and from the DB
        WikiPage wikiPage = wikiPageMaker.makeWikiPage(pageTitle, wikiPageContent);

        // make the list of chunk strings based on the content of the wiki page using chunker
        chunkStirngsList = chunker.makeChunksList(wikiPageContent);


        // loop thru the list of chunk strings
        for(int i = 0; i < chunkStirngsList.size(); i++){

            float[] temp = new float [768];

            // make a new empty chunk
            Chunk newChunk = new Chunk();

            // set the attributes of the new chunk
            newChunk.setContent(chunkStirngsList.get(i));
            newChunk.setEmbeddingVector(temp);
            newChunk.setWikiPage(wikiPage);

            // save the new chunk to the DB
            newChunk = chunkDAO.save(newChunk);

            // add the new chunk to the chunk list
            chunkList.add(newChunk);

        }

        // return the full chunk list
        return chunkList;
    }
}
