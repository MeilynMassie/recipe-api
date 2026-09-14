package com.mjm.api.recipe.model;

import java.util.List;

import lombok.Getter;

@Getter 
public class UpdateRecipeRequest {
    private String name;

    private Integer prep_time;

    private Integer cook_time;

    private Integer servings;

    private List<Ingredient> ingredients;

    private List<Instruction> instructions;
}
