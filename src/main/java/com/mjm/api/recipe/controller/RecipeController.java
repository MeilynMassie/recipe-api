package com.mjm.api.recipe.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mjm.api.recipe.model.Recipe;
import com.mjm.api.recipe.model.ChangeRequest.UpdateRecipeRequest;
import com.mjm.api.recipe.service.RecipeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("${app.api.base-path}/recipe/{chefId}")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public List<Recipe> getRecipes(@PathVariable Long chefId) {
        return recipeService.getRecipes(chefId);
    }

    @GetMapping("/{recipeId}")
    public Recipe getRecipe(@PathVariable Long chefId, @PathVariable Long recipeId) {
        return recipeService.getRecipe(recipeId);
    }

    @PostMapping
    public ResponseEntity<Void> createRecipe(@Valid @RequestBody Recipe recipe, @PathVariable Long chefId) {
        recipeService.createRecipe(recipe, chefId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{recipeId}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long recipeId) {
        recipeService.deleteRecipe(recipeId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{recipeId}")
    public ResponseEntity<Void> updateRecipeDetails(@PathVariable Long recipeId, @RequestBody UpdateRecipeRequest recipe) {
        recipeService.updateRecipeDetails(recipeId, recipe);
        return ResponseEntity.noContent().build();
    }
}