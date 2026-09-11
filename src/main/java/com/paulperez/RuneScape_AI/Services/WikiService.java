package com.paulperez.RuneScape_AI.Services;
import com.paulperez.RuneScape_AI.Model.JSON_Model.*;
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
    public JSONPackageObject getWikiPage(String title){

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

        // create bool variable, false by default
        boolean result = false;

        // DESERIALIZE JSONPackageObject

        // if responseJSON is null
        if(responseJSON == null){

            // return true (page is unusable)
            result = true;
            return result;
        }

        // get the queryPackageObject from responseJSON
        QueryPackageObject queryPackageObject = responseJSON.getQueryPackageObject();

        // if queryPackageObject is null
        if(queryPackageObject == null){

            // return true (page is unusable)
            result = true;
            return result;
        }

        // get the pageResultObject from queryPackasgeObject using the pageResultObject map
        PageResultObject pageResultObject =  queryPackageObject.getPageResultObjectMap().values().stream().findFirst().orElse(null);

        // if there is no page the page is not usable
        // if there are mo revisions the page is not usable
        if(pageResultObject == null || pageResultObject.getRevisionsList() == null || pageResultObject.getRevisionsList().size() == 0) {

            // return page is unusable
            result = true;
            return result;
        }

        // get the revisionsPackageObject from the pageResultObject using revisionsList
        RevisionsPackageObject revisionsPackageObject = pageResultObject.getRevisionsList().getFirst();

        // get the slotsObject from the revisionsPackageObject
        SlotsObject slotsObject = revisionsPackageObject.getSlotsObject();

        // get the mainObject from the slots object
        MainObject mainObject = slotsObject.getMainObject();

        // get the actual text content from the main object
        String textContent = mainObject.getTextContent();

        // if the actual text contains redirect
        if(textContent.contains(("#REDIRECT"))){

            // response is a redirect
            result = true;
        }

        // return result
        return result;
    }
}
