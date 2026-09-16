package com.mjm.api.recipe.service.impl;

import org.springframework.web.client.RestClient;

import com.mjm.api.recipe.model.Recipe;
import com.mjm.api.recipe.service.ExtractionService;

public class ExtractionServiceImpl implements  ExtractionService{

    private final RestClient restClient;

    public ExtractionServiceImpl(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("http://localhost:8000")
                .build();
    }
    
    @Override
    public Recipe extractRecipeFromUrl(String url) {
        return restClient.post()
                .uri("/extract/url")
                .body(new String(url))
                .retrieve()
                .body(Recipe.class);
    }
}
