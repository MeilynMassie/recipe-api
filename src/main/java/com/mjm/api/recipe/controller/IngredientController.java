package com.mjm.api.recipe.controller;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mjm.api.recipe.model.Ingredient;
import com.mjm.api.recipe.model.ChangeRequest.UpdateIngredientRequest;
import com.mjm.api.recipe.service.IngredientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("${app.api.base-path}/recipe/{recipeId}/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public List<Ingredient> getIngredients(@PathVariable Long recipeId) {
        return ingredientService.getIngredients(recipeId);
    }
    
    @GetMapping("/{ingredientId}")
    public Ingredient getIngredient(@PathVariable Long ingredientId, @PathVariable Long recipeId) {
        return ingredientService.getIngredient(ingredientId, recipeId);
    }

    @PostMapping
    public void createIngredient(@Valid @RequestBody Ingredient ingredient, @PathVariable Long recipeId) {
        ingredientService.createIngredient(ingredient, recipeId);
    }

    @DeleteMapping("/{ingredientId}")
    public void deleteIngredient(@PathVariable Long ingredientId) {
        ingredientService.deleteIngredient(ingredientId);
    }

    @PatchMapping("/{ingredientId}")
    public String updateIngredientDetails(@PathVariable Long ingredientId, @PathVariable Long recipeId, @RequestBody UpdateIngredientRequest ingredient) {
        ingredientService.updateIngredientDetails(ingredientId, recipeId, ingredient);
        return "Ingredient updated successfully";
    }
}