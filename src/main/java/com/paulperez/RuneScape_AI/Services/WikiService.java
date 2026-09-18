package com.paulperez.RuneScape_AI.Services;
import com.paulperez.RuneScape_AI.Model.JSON_Model.*;
import com.paulperez.RuneScape_AI.Utility.Deserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class WikiService {

    // variable to store the API URL
    private final String API_BASE_URL = "https://oldschool.runescape.wiki/api.php";

    // create client to access URL
    private final RestClient restClient = RestClient.create(API_BASE_URL);

    private final Deserializer deserializer;

    @Autowired
    public WikiService(Deserializer deserializer) {
        this.deserializer = deserializer;
    }

    // METHODS //

    // Gets wiki page based on title
    public JSONPackageObject getWikiPage(String title){

        // create empty package object
        JSONPackageObject jsonPackageObject = null;

        // Try
        try{

            // Try to use RestClient to get the data from the API URL
            // uri is th query string
            // action=query says we're querying the data
            // format=json says we want JSON back from call
            // prop=revisions says I want the revision-history stuff not links or categories
            // rvprop=content says give me page text, not the metadata
            // rvslots=main says give me the standard compartment
            // titles= is where we put the title from the method call
            // .Replace lets us replace all spaces w underscores since spaces will break the URL
            jsonPackageObject = restClient.get().uri("?action=query&format=json&prop=revisions&rvprop=content&rvslots=main&titles=" + title.replace(" ", "_")).retrieve().body(JSONPackageObject.class);

            // if wikiJSON represents a usable page
            if(isUnusablePage(jsonPackageObject)){

                // notify of unusable page
                System.out.println("Page is unusable");
                jsonPackageObject = null;
            }
        }
        // catch exception
        catch(Exception e){

            // print exception
            System.out.println("****REST CLIENT RESPONSE EXCEPTION****");
            e.printStackTrace();
        }

        return jsonPackageObject;
    }

    // checks if wiki page is usable
    public boolean isUnusablePage(JSONPackageObject responseJSON){

        boolean result = false;

        if(deserializer.deserialize(responseJSON).equals("")){
            result = true;
        }

        // return result
        return result;
    }
}
