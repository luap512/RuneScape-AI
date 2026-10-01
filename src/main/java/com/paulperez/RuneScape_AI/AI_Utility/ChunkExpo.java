package com.paulperez.RuneScape_AI.AI_Utility;
import com.paulperez.RuneScape_AI.Model.AI_Model.Receiving.AskQuestionResponse;
import com.paulperez.RuneScape_AI.Services.QuestionService;
import com.pgvector.PGvector;
import com.paulperez.RuneScape_AI.DAO.ChunkDAO;
import com.paulperez.RuneScape_AI.Model.Chunk;
import com.paulperez.RuneScape_AI.Model.JSON_Model.*;
import com.paulperez.RuneScape_AI.Model.WikiPage;
import com.paulperez.RuneScape_AI.Services.WikiService;
import com.paulperez.RuneScape_AI.Services.EmbeddingService;
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
    private final EmbeddingService embeddingService;
    private final QuestionService questionService;
    private Chunker chunker;
    private Deserializer deserializer;
    private WikiPageMaker wikiPageMaker;
    private ChunkDAO chunkDAO;


    @Autowired
    public ChunkExpo(WikiService wikiService, Chunker chunker, Deserializer deserializer, WikiPageMaker wikiPageMaker, ChunkDAO chunkDAO, EmbeddingService embeddingService, QuestionService questionService) {
        this.wikiService = wikiService;
        this.chunker = chunker;
        this.deserializer = deserializer;
        this.wikiPageMaker = wikiPageMaker;
        this.chunkDAO = chunkDAO;
        this.embeddingService = embeddingService;
        this.questionService = questionService;
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

            // create a List of floats called embed values based on the embeddingService API call
            List<Float> embedValues = embeddingService.embedText(chunkStirngsList.get(i)).getEmbedding().getValues();

            // create a temp array of floats the same size as the list from the embeddingService call
            float [] temp = new float[embedValues.size()];

            // loop thru the List from the embeddingService call
            for(int j = 0; j < embedValues.size(); j++){
                temp[j] = embedValues.get(j);
            }

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

    public List<Chunk> compareQuestionWikiPageVectors(String question){

        // get embedded question

        List<Float> floatList = embeddingService.embedText(question).getEmbedding().getValues();

        float[] embeddedQuestion = new float[floatList.size()];

        for(int i = 0; i < floatList.size(); i++){
            embeddedQuestion[i] = floatList.get(i);
        }

        // compare with DAO

        List<Chunk> similarChunksList = chunkDAO.getSimilarChunks(embeddedQuestion);

        // return list of similar chunks
        return similarChunksList;

    }

    public String generatePromptString(String questionString , List<Chunk> chunkList){

        String generatedPromptString =
                "Act as a professional old school RuneScape player and developer " +
                "who has spent 100's of hours playing the game and has completed all of its major challenges. " +
                "Using the context provided, answer the question: " + questionString +
                        " if you can't find the answer in the context, tell me, and ask specific questions to get to " +
                        "a better answer. ";

        String contextString = "Context: ";

        for(int i = 0; i < chunkList.size(); i++){

            contextString += " " + chunkList.get(i).getContent();

        }

        return generatedPromptString + contextString;
    }

    public AskQuestionResponse getQuestionResponse(String questionString){

        // get the list of similar chunks
        List<Chunk> similarChunks = compareQuestionWikiPageVectors(questionString);

        // build the prompt
        String promptString = generatePromptString(questionString, similarChunks);


        // return the response after sending the full prompt thru the question service
        return questionService.getAskQuestionResponse(promptString);
    }
}
