package com.paulperez.RuneScape_AI.Utility;

import com.paulperez.RuneScape_AI.DAO.WikiPageDAO;
import com.paulperez.RuneScape_AI.Model.JSON_Model.JSONPackageObject;
import com.paulperez.RuneScape_AI.Model.WikiPage;
import com.paulperez.RuneScape_AI.Services.WikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WikiPageMaker {

    private WikiPageDAO wikiPageDAO;

    @Autowired
    public WikiPageMaker(WikiPageDAO wikiPageDAO) {
        this.wikiPageDAO = wikiPageDAO;
    }

    public WikiPage makeWikiPage(String title, String content){

        String URL = "https://oldschool.runescape.wiki/w/" + title.replace(" ", "_");

        WikiPage newWikiPage = new WikiPage();

        newWikiPage.setTitle(title);
        newWikiPage.setContent(content);
        newWikiPage.setURL(URL);

        newWikiPage = wikiPageDAO.save(newWikiPage);

        return newWikiPage;
    }
}
