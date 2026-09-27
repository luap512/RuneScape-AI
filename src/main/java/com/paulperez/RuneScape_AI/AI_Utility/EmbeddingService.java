package com.paulperez.RuneScape_AI.AI_Utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmbeddingService {

    // @Value tells Spring where API key goes
    @Value("${gemini.api.key}")
    private String apiKey;
}
