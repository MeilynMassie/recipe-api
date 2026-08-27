package com.mjm.api.recipe.service.impl;

import org.springframework.stereotype.Service;

import com.mjm.api.recipe.exception.ResourceNotFoundException;
import com.mjm.api.recipe.model.Recipe;
import com.mjm.api.recipe.repository.RecipeRepository;
import com.mjm.api.recipe.service.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService {
    private RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Recipe getRecipe(Long chefId) {
        return recipeRepository.findByChefId(chefId).orElseThrow(() -> new ResourceNotFoundException("Recipe not found due to Chef", chefId));
    }
}