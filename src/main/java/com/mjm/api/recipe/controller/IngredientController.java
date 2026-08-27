package com.mjm.api.recipe.controller;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mjm.api.recipe.model.Ingredient;
import com.mjm.api.recipe.service.IngredientService;

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
}