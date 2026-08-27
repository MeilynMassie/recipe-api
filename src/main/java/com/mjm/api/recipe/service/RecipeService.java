package com.mjm.api.recipe.service;

import com.mjm.api.recipe.model.Recipe;

public interface RecipeService {
    Recipe getRecipe(Long chefId);
}