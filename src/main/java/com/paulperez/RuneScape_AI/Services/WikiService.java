package com.paulperez.RuneScape_AI.Services;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class WikiService {

    // variable to store the API URL
    private final String API_BASE_URL = "https://oldschool.runescape.wiki/api.php";

    // create client to access URL
    private final RestClient restClient = RestClient.create(API_BASE_URL);

    // METHODS //

    // Gets wiki page based on title
    public String getWikiPage(String title){

        String wikiJSON = title + " ";

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
            wikiJSON = restClient.get().uri("?action=query&format=json&prop=revisions&rvprop=content&rvslots=main&titles=" + title.replace(" ", "_")).retrieve().body(String.class);

            if(isRedirect(wikiJSON)){
                wikiJSON = "That Page was a redirect";
            }
        }
        // catch exception
        catch(Exception e){

            // print exception
            System.out.println("****REST CLIENT RESPONSE EXCEPTION****");
            System.out.println(e.toString());
        }

        return wikiJSON;
    }

    // checks if wiki page is a redirect
    public boolean isRedirect(String responseJSON){

        // create bool variable, false by default
        boolean result = false;

        // if response contains redirect text
        if(responseJSON.contains("#REDIRECT")){

            // response is a redirect
            result = true;
        }

        // return result
        return result;
    }
}
