package com.paulperez.RuneScape_AI.Utility;

import com.paulperez.RuneScape_AI.Model.JSON_Model.*;
import org.springframework.stereotype.Component;

@Component
public class Deserializer {

    public String deserialize(JSONPackageObject jsonPackageObject){

        String retStr = "";

        // if responseJSON is null
        if(jsonPackageObject == null){

            // return empty string (page is unusable)
            return retStr;
        }

        // get the queryPackageObject from responseJSON
        QueryPackageObject queryPackageObject = jsonPackageObject.getQueryPackageObject();

        // if queryPackageObject is null
        if(queryPackageObject == null){

            // return empty string
            return retStr;
        }

        // get the pageResultObject from queryPackasgeObject using the pageResultObject map
        PageResultObject pageResultObject =  queryPackageObject.getPageResultObjectMap().values().stream().findFirst().orElse(null);

        // if there is no page the page is not usable
        // if there are no revisions the page is not usable
        if(pageResultObject == null || pageResultObject.getRevisionsList() == null || pageResultObject.getRevisionsList().size() == 0) {

            // return empty string
            return retStr;
        }

        // get the revisionsPackageObject from the pageResultObject using revisionsList
        RevisionsPackageObject revisionsPackageObject = pageResultObject.getRevisionsList().getFirst();

        // get the slotsObject from the revisionsPackageObject
        SlotsObject slotsObject = revisionsPackageObject.getSlotsObject();

        // get the mainObject from the slots object
        MainObject mainObject = slotsObject.getMainObject();

        // get the actual text content from the main object + make it lower case
        String lowerCaseTextContent = mainObject.getTextContent().toLowerCase();

        // store unchanged text content
        String textContent = mainObject.getTextContent();

        // if the actual text contains redirect or is a disambig page
        if(lowerCaseTextContent.contains(("#redirect")) || lowerCaseTextContent.contains("{{disambig}}")){

            // return empty string
            return retStr;

        }

        // make return string text content
        retStr = textContent;

        return retStr;
    }
}
