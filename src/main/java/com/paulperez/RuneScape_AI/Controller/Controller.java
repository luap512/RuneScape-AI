package com.paulperez.RuneScape_AI.Controller;

import com.paulperez.RuneScape_AI.AI_Utility.ChunkExpo;
import com.paulperez.RuneScape_AI.DAO.ChatQueriesDAO;
import com.paulperez.RuneScape_AI.DAO.ChunkDAO;
import com.paulperez.RuneScape_AI.DAO.WikiPageDAO;
import com.paulperez.RuneScape_AI.Model.Chunk;
import com.paulperez.RuneScape_AI.Model.JSON_Model.JSONPackageObject;
import com.paulperez.RuneScape_AI.Model.WikiPage;
import com.paulperez.RuneScape_AI.Services.WikiService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/wiki")
public class Controller {

    private ChatQueriesDAO chatQueriesDAO;
    private ChunkDAO chunkDAO;
    private WikiPageDAO wikiPageDAO;
    private WikiService wikiService;
    private ChunkExpo chunkExpo;

    public Controller(ChatQueriesDAO chatQueriesDAO, ChunkDAO chunkDAO, WikiPageDAO wikiPageDAO, WikiService wikiService, ChunkExpo chunkExpo) {
        this.chatQueriesDAO = chatQueriesDAO;
        this.chunkDAO = chunkDAO;
        this.wikiPageDAO = wikiPageDAO;
        this.wikiService = wikiService;
        this.chunkExpo = chunkExpo;
    }

    @RequestMapping(path = "/{title}", method = RequestMethod.GET)
    public JSONPackageObject getWikiPageByTitle(@PathVariable String title){

        JSONPackageObject wikiPage = wikiService.getWikiPage(title);

        if(wikiPage == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wiki Page not found");
        }

        return wikiPage;
    }

    @RequestMapping(path = "/{title}/chunks", method = RequestMethod.POST)
    public List<Chunk> addChunks(@PathVariable String title){
        List<Chunk> chunkList = chunkExpo.makeChunkList(title);
        return chunkList;
    }
}
