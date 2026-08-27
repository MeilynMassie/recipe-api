package com.mjm.api.recipe.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mjm.api.recipe.model.Ingredient;
import com.mjm.api.recipe.repository.IngredientRepository;
import com.mjm.api.recipe.service.IngredientService;
import com.mjm.api.recipe.exception.ResourceNotFoundException;


@Service
public class IngredientServiceImpl implements IngredientService {
    private IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> getIngredients(Long recipeId) {
        return ingredientRepository.findByRecipeId(recipeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ingredient not found due to Recipe", recipeId));
    }
}