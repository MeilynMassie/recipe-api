package com.mjm.api.recipe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mjm.api.recipe.model.Recipe;
import com.mjm.api.recipe.service.RecipeService;

@RestController
@RequestMapping("${app.api.base-path}/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public Recipe getRecipe(@RequestParam Long chefId) {
        return recipeService.getRecipe(chefId);
    }
}