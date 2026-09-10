package com.paulperez.RuneScape_AI.Controller;

import com.paulperez.RuneScape_AI.DAO.ChatQueriesDAO;
import com.paulperez.RuneScape_AI.DAO.ChunkDAO;
import com.paulperez.RuneScape_AI.DAO.WikiPageDAO;
import com.paulperez.RuneScape_AI.Model.JSON_Model.JSONPackageObject;
import com.paulperez.RuneScape_AI.Model.WikiPage;
import com.paulperez.RuneScape_AI.Services.WikiService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/wiki")
public class Controller {

    private ChatQueriesDAO chatQueriesDAO;
    private ChunkDAO chunkDAO;
    private WikiPageDAO wikiPageDAO;
    private WikiService wikiService;

    public Controller(ChatQueriesDAO chatQueriesDAO, ChunkDAO chunkDAO, WikiPageDAO wikiPageDAO, WikiService wikiService) {
        this.chatQueriesDAO = chatQueriesDAO;
        this.chunkDAO = chunkDAO;
        this.wikiPageDAO = wikiPageDAO;
        this.wikiService = wikiService;
    }

    @RequestMapping(path = "/{title}", method = RequestMethod.GET)
    public JSONPackageObject getWikiPageByTitle(@PathVariable String title){

        JSONPackageObject wikiPage = wikiService.getWikiPage(title);

        if(wikiPage == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wiki Page not found");
        }

        return wikiPage;
    }
}
