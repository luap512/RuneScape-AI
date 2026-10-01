package com.paulperez.RuneScape_AI.Services;

import com.paulperez.RuneScape_AI.Model.AI_Model.Receiving.AskQuestionResponse;
import com.paulperez.RuneScape_AI.Model.AI_Model.Receiving.EmbedContentResponse;
import com.paulperez.RuneScape_AI.Model.AI_Model.Sending.Content;
import com.paulperez.RuneScape_AI.Model.AI_Model.Sending.ContentsListWrapper;
import com.paulperez.RuneScape_AI.Model.AI_Model.Sending.Part;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionService {

    // @Value tells Spring where API key goes
    @Value("${gemini.api.key}")
    private String apiKey;

    // api URL string
    private String API_BASE_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash-lite:generateContent";

    // create client to access URL
    private final RestClient restClient = RestClient.create(API_BASE_URL);

    public ContentsListWrapper generateContentsListWrapper(String questionString){

        Part part = new Part(questionString);

        List<Part> partList = new ArrayList<>();

        partList.add(part);

        Content content = new Content(partList);

        List<Content> contentList = new ArrayList<>();

        contentList.add(content);

        ContentsListWrapper contentsListWrapper = new ContentsListWrapper(contentList);

        return contentsListWrapper;

    }

    public AskQuestionResponse getAskQuestionResponse(String questionString) {

        // create a contentsListWrapper based on the question
        ContentsListWrapper contentsListWrapper = generateContentsListWrapper(questionString);

        // create a null askQuestionResponse
        AskQuestionResponse askQuestionResponse = null;

        // try to get the askQuestionReponse using restClient
        try{

            // POST the contentsListWrapper
            askQuestionResponse = restClient.post().header("x-goog-api-key", apiKey).contentType(MediaType.APPLICATION_JSON).body(contentsListWrapper).retrieve().body(AskQuestionResponse.class);

        }

        // catch exception
        catch (Exception e){


            // print exception
            System.out.println("****REST CLIENT RESPONSE EXCEPTION****");
            e.printStackTrace();

        }

        // return askQuestionResponse
        return askQuestionResponse;
    }
}
