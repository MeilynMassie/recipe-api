package com.mjm.api.recipe.service;

import java.util.List;

import com.mjm.api.recipe.model.Ingredient;

public interface IngredientService {
    List<Ingredient> getIngredients(Long recipeId);
}