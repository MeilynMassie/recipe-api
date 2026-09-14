package com.mjm.api.recipe.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mjm.api.recipe.exception.ResourceNotFoundException;
import com.mjm.api.recipe.model.Chef;
import com.mjm.api.recipe.model.Recipe;
import com.mjm.api.recipe.model.ChangeRequest.UpdateRecipeRequest;
import com.mjm.api.recipe.repository.ChefRepository;
import com.mjm.api.recipe.repository.RecipeRepository;
import com.mjm.api.recipe.service.RecipeService;


@Service
public class RecipeServiceImpl implements RecipeService {
    private RecipeRepository recipeRepository;
    private ChefRepository chefRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository, ChefRepository chefRepository) {
        this.recipeRepository = recipeRepository;
        this.chefRepository = chefRepository;
    }

    @Override
    public Recipe getRecipe(Long recipeId) {
        return recipeRepository.findById(recipeId).orElseThrow(() -> new ResourceNotFoundException("Recipe", recipeId));
    }

    @Override
    public List<Recipe> getRecipes(Long chefId) {
        List<Recipe> recipes = recipeRepository.findByChefId(chefId);

        if (recipes.isEmpty()) {
            throw new ResourceNotFoundException("Recipe not found due to Chef", chefId);
        }

        return recipes;
    }

    @Override
    public void createRecipe(Recipe recipe, Long chefId) {
        Chef chef = chefRepository.findById(chefId).orElseThrow(() -> new ResourceNotFoundException("Chef", chefId));
        recipe.setChef(chef);

        if (recipe.getIngredients() != null) {
            recipe.getIngredients().forEach(ingredient -> ingredient.setRecipe(recipe));
        }

        if (recipe.getInstructions() != null) {
            recipe.getInstructions().forEach(instruction -> instruction.setRecipe(recipe));
        }

        recipeRepository.save(recipe);
    }

    @Override
    public void deleteRecipe(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new ResourceNotFoundException("Recipe", recipeId));
        recipeRepository.delete(recipe);
    }

    @Override
    public void updateRecipeDetails(Long recipeId, UpdateRecipeRequest recipeChangeRequest) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new ResourceNotFoundException("Recipe", recipeId));
        System.out.println("Ingredients: " + recipe.getIngredients());
        System.out.println("Instructions: " + recipe.getInstructions());
        if (recipeChangeRequest.getName() != null) {
            recipe.setName(recipeChangeRequest.getName());
        }
        if (recipeChangeRequest.getPrep_time() != null) {
            recipe.setPrep_time(recipeChangeRequest.getPrep_time());
        }
        if (recipeChangeRequest.getCook_time() != null) {
            recipe.setCook_time(recipeChangeRequest.getCook_time());
        }
        if (recipeChangeRequest.getServings() != null) {
            recipe.setServings(recipeChangeRequest.getServings());
        }
        recipeRepository.save(recipe);
    }
}