package com.mjm.api.recipe.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mjm.api.recipe.model.Ingredient;
import com.mjm.api.recipe.model.Recipe;
import com.mjm.api.recipe.model.UpdateIngredientRequest;
import com.mjm.api.recipe.repository.IngredientRepository;
import com.mjm.api.recipe.repository.RecipeRepository;
import com.mjm.api.recipe.service.IngredientService;
import com.mjm.api.recipe.exception.ResourceNotFoundException;


@Service
public class IngredientServiceImpl implements IngredientService {
    private IngredientRepository ingredientRepository;
    private RecipeRepository recipeRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, RecipeRepository recipeRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Ingredient> getIngredients(Long recipeId) {
        List<Ingredient> ingredients = ingredientRepository.findByRecipeId(recipeId);

        if (ingredients.isEmpty()) {
            throw new ResourceNotFoundException("Ingredient not found due to Recipe", recipeId);
        }
        return ingredients;
    }

    @Override
    public Ingredient getIngredient(Long ingredientId, Long recipeId) {
        return ingredientRepository.findByIdAndRecipeId(ingredientId, recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found due to Recipe", recipeId));
    }

    @Override
    public void createIngredient(Ingredient ingredient, Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new ResourceNotFoundException("Recipe", recipeId));
        ingredient.setRecipe(recipe);
        ingredientRepository.save(ingredient);
    }

    @Override
    public void deleteIngredient(Long ingredientId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteIngredient'");
    }

    @Override
    public void updateIngredientDetails(Long ingredientId, Long recipeId, UpdateIngredientRequest ingredient) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateIngredientDetails'");
    }
}