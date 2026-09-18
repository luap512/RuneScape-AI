package com.paulperez.RuneScape_AI.AI_Utility;

import com.paulperez.RuneScape_AI.Services.WikiService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Chunker {

    public Chunker() {
    }

    public List<String> makeChunksList(String textContent){

        // create empty list called chunkList to hold chunks
        List<String> chunkList = new ArrayList<String>();

        // create subStrings array to get text content split using regex
        // .split("==+.*?==+") says split the text @ any characters b/w ==
        // ? says be lazy, don't gobble
        // + says next one has to match previous
        String[] subStrings = textContent.split("==+.*?==+");

        // print subString
        for(int i = 0; i < subStrings.length; i++){
            // System.out.println(subStrings[i]);
            chunkList.add(subStrings[i]);
        }

        // return chunkList
        return chunkList;
    }
}
