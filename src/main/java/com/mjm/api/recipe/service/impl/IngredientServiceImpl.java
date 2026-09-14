package com.mjm.api.recipe.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mjm.api.recipe.exception.InvalidRequestException;
import com.mjm.api.recipe.model.Ingredient;
import com.mjm.api.recipe.model.Recipe;
import com.mjm.api.recipe.model.ChangeRequest.UpdateIngredientRequest;
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
        recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe", recipeId));

        return ingredientRepository.findByRecipeId(recipeId);
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
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient", ingredientId));
        ingredientRepository.delete(ingredient);
    }

    @Override
    public void updateIngredientDetails(Long ingredientId, Long recipeId, UpdateIngredientRequest ingredientChangeRequest) {
        Ingredient ingredient = ingredientRepository.findByIdAndRecipeId(ingredientId, recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found due to Recipe", recipeId));

        if (!UpdateRequestValidator.hasAnyIngredientUpdate(ingredientChangeRequest)) {
            throw new InvalidRequestException("PATCH request body must contain at least one valid field to update");
        }

        if (ingredientChangeRequest.getQuantity() != null) {
            ingredient.setQuantity(ingredientChangeRequest.getQuantity());
        }
        if (ingredientChangeRequest.getUnit() != null) {
            ingredient.setUnit(ingredientChangeRequest.getUnit());
        }
        if (ingredientChangeRequest.getName() != null) {
            ingredient.setName(ingredientChangeRequest.getName());
        }
        if (ingredientChangeRequest.getSection() != null) {
            ingredient.setSection(ingredientChangeRequest.getSection());
        }

        ingredientRepository.save(ingredient);
    }
}