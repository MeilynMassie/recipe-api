package com.mjm.api.recipe.service;

import java.util.List;

import com.mjm.api.recipe.model.Recipe;
import com.mjm.api.recipe.model.UpdateRecipeRequest;

public interface RecipeService {
    List<Recipe> getRecipes(Long chefId);
    Recipe getRecipe(Long recipeId);
    void createRecipe(Recipe recipe, Long chefId);
    void deleteRecipe(Long recipeId);
    void updateRecipeDetails(Long recipeId, UpdateRecipeRequest recipe);
}