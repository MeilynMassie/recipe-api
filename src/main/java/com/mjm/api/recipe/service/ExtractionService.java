package com.mjm.api.recipe.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mjm.api.recipe.dto.Extraction.RecipeExtraction;

public interface ExtractionService {

    RecipeExtraction extractRecipeFromUrl(String url);
    RecipeExtraction extractRecipeFromImages(List<MultipartFile> images);
    // TODO: May need to break this process into two functions instead of one but we shall see
    // ResponseEntity<String> sendImagesToApi(MultipartFile file);
    // RecipeExtraction recieveRecipeFromApi(String response);
}

