package com.mjm.api.recipe.service;

import java.util.List;

import com.mjm.api.recipe.model.Ingredient;
import com.mjm.api.recipe.model.UpdateIngredientRequest;

public interface IngredientService {
    List<Ingredient> getIngredients(Long recipeId);
    Ingredient getIngredient(Long ingredientId, Long recipeId);
    void createIngredient(Ingredient ingredient, Long recipeId);
    void deleteIngredient(Long ingredientId);
    void updateIngredientDetails(Long ingredientId, Long recipeId, UpdateIngredientRequest ingredient);
}