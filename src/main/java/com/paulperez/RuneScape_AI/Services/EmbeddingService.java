package com.paulperez.RuneScape_AI.Services;

import com.paulperez.RuneScape_AI.Model.AI_Model.Receiving.EmbedContentResponse;
import com.paulperez.RuneScape_AI.Model.AI_Model.Sending.Content;
import com.paulperez.RuneScape_AI.Model.AI_Model.Sending.EmbedRequest;
import com.paulperez.RuneScape_AI.Model.AI_Model.Sending.Part;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;


@Component
public class EmbeddingService {

    // @Value tells Spring where API key goes
    @Value("${gemini.api.key}")
    private String apiKey;

    // api URL string
    private String API_BASE_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-embedding-001:embedContent";

    // create client to access URL
    private final RestClient restClient = RestClient.create(API_BASE_URL);

    public EmbedContentResponse embedText(String text){

        // create empty embedContentResponse object
        EmbedContentResponse embedContentResponse = null;

        // create null sending objects

        // create new part using text
        Part part = new Part(text);

        // create list of parts for content
        List<Part> partList = new ArrayList<>();

        // add new part to partlist
        partList.add(part);

        // create new content object using partList
        Content content = new Content(partList);

        // create new embedRequest Object using content
        EmbedRequest embedRequest = new EmbedRequest(content);


        // Try
        try{

            embedContentResponse = restClient.post().header("x-goog-api-key", apiKey).contentType(MediaType.APPLICATION_JSON).body(embedRequest).retrieve().body(EmbedContentResponse.class);

        }
        // catch exception
        catch(Exception e){

            // print exception
            System.out.println("****REST CLIENT RESPONSE EXCEPTION****");
            e.printStackTrace();
        }

        return embedContentResponse;
    }
}
