package com.paulperez.RuneScape_AI.Utility;

import com.paulperez.RuneScape_AI.DAO.WikiPageDAO;
import com.paulperez.RuneScape_AI.Model.JSON_Model.JSONPackageObject;
import com.paulperez.RuneScape_AI.Model.WikiPage;
import com.paulperez.RuneScape_AI.Services.WikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


// The purpose of this object is to 'make' wiki pages
// in other words use the DAO to saave wiki pages to DB
@Component
public class WikiPageMaker {

    private WikiPageDAO wikiPageDAO;

    @Autowired
    public WikiPageMaker(WikiPageDAO wikiPageDAO) {
        this.wikiPageDAO = wikiPageDAO;
    }

    public WikiPage makeWikiPage(String title, String content){

        // build the URL using the title
        String URL = "https://oldschool.runescape.wiki/w/" + title.replace(" ", "_");

        // crate new wiki page
        WikiPage newWikiPage = new WikiPage();

        // set attributes of new wiki page
        newWikiPage.setTitle(title);
        newWikiPage.setContent(content);
        newWikiPage.setURL(URL);

        // save new wiki page to DB using DAO
        newWikiPage = wikiPageDAO.save(newWikiPage);

        // return new wiki page that was saved to DB
        return newWikiPage;
    }
}
