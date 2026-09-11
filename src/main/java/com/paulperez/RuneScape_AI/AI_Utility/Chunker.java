package com.paulperez.RuneScape_AI.AI_Utility;

import com.paulperez.RuneScape_AI.Services.WikiService;

import java.util.ArrayList;
import java.util.List;

public class Chunker {
    
    public List<String> makeChunks(String textContent){

        // create empty list called chunkList to hold chunks
        List<String> chunkList = new ArrayList<String>();

        // create subStrings array to get text content split using regex
        // .split("==.*?==") says split the text @ any characters b/w == and ? says be lazy, dont gobble
        String[] subStrings = textContent.split("==.*?==");

        // print subString
        for(int i = 0; i < subStrings.length; i++){
            System.out.println(subStrings[i]);
        }

        // return chunkList
        return chunkList;
    }
}
