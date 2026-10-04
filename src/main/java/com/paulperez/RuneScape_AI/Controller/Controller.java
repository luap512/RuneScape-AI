package com.paulperez.RuneScape_AI.Controller;

import com.paulperez.RuneScape_AI.AI_Utility.ChunkExpo;
import com.paulperez.RuneScape_AI.DAO.ChatQueriesDAO;
import com.paulperez.RuneScape_AI.DAO.ChunkDAO;
import com.paulperez.RuneScape_AI.DAO.WikiPageDAO;
import com.paulperez.RuneScape_AI.Model.AI_Model.Receiving.AskQuestionResponse;
import com.paulperez.RuneScape_AI.Model.AI_Model.Receiving.EmbedContentResponse;
import com.paulperez.RuneScape_AI.Model.AI_Model.Receiving.Embedding;
import com.paulperez.RuneScape_AI.Model.ChatQuery;
import com.paulperez.RuneScape_AI.Model.Chunk;
import com.paulperez.RuneScape_AI.Model.JSON_Model.JSONPackageObject;
import com.paulperez.RuneScape_AI.Model.WikiPage;
import com.paulperez.RuneScape_AI.Services.EmbeddingService;
import com.paulperez.RuneScape_AI.Services.WikiService;
import org.springframework.http.HttpStatus;
import org.springframework.util.comparator.ComparableComparator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/wiki") @CrossOrigin("http://localhost:5173")
public class Controller {

    private ChatQueriesDAO chatQueriesDAO;
    private ChunkDAO chunkDAO;
    private WikiPageDAO wikiPageDAO;
    private WikiService wikiService;
    private EmbeddingService embeddingService;
    private ChunkExpo chunkExpo;

    public Controller(ChatQueriesDAO chatQueriesDAO, ChunkDAO chunkDAO, WikiPageDAO wikiPageDAO, WikiService wikiService, ChunkExpo chunkExpo, EmbeddingService embeddingService) {
        this.chatQueriesDAO = chatQueriesDAO;
        this.chunkDAO = chunkDAO;
        this.wikiPageDAO = wikiPageDAO;
        this.wikiService = wikiService;
        this.embeddingService = embeddingService;
        this.chunkExpo = chunkExpo;
    }

    @RequestMapping(path = "/{title}", method = RequestMethod.GET)
    public JSONPackageObject getWikiPageByTitle(@PathVariable String title){

        JSONPackageObject wikiPage = wikiService.getWikiPage(title);

        if(wikiPage == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wiki Page not found");
        }

        return wikiPage;
    }

    @RequestMapping(path = "/{title}/chunks", method = RequestMethod.POST)
    public List<Chunk> addChunks(@PathVariable String title){
        List<Chunk> chunkList = chunkExpo.makeChunkList(title);
        return chunkList;
    }

    @RequestMapping(path = "/embeddingService", method = RequestMethod.POST)
    public EmbedContentResponse getEmbededContent(@RequestParam(required = true) String requestText){
        EmbedContentResponse embedContentResponse =  embeddingService.embedText(requestText);
        return embedContentResponse;
    }

    @RequestMapping(path = "/query", method = RequestMethod.POST)
    public float[] getQueryVector(@RequestBody(required = true) ChatQuery chatQuery){

        System.out.println("Question: " +  chatQuery.getQuestion());

        // get question from chatQuery
        String question = chatQuery.getQuestion();

        // call embedding service + save response in variable
        EmbedContentResponse embedContentResponse = embeddingService.embedText(question);

        // check if embedding service returns null
        if(embedContentResponse == null){

            // bad gateway response
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "couldn't get an embedding for that question");
        }

        // get embedding from embedContentResponse
        Embedding embedding = embedContentResponse.getEmbedding();

        // create a temp array of floats the same size as the list from the embeddingService call
        float [] temp = new float[embedding.getValues().size()];

        // loop thru the List from the embeddingService call
        for(int j = 0; j < embedding.getValues().size(); j++){
            temp[j] = embedding.getValues().get(j);
        }

        return temp;
    }

    @RequestMapping(path = "/similarChunks", method = RequestMethod.POST)
    public List<Chunk> getSimilarChunks(@RequestBody(required = true) ChatQuery chatQuery){

        return chunkExpo.compareQuestionWikiPageVectors(chatQuery.getQuestion());
    }

    @RequestMapping(path = "/askAI", method = RequestMethod.POST)
    public String getAskQuestionResponse(@RequestBody(required = true) ChatQuery chatQuery){

        return chunkExpo.getQuestionResponse(chatQuery.getQuestion());
    }


}
