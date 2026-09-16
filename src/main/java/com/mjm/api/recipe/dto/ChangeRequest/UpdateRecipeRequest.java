package com.mjm.api.recipe.dto.ChangeRequest;

import java.util.List;

import com.mjm.api.recipe.model.Ingredient;
import com.mjm.api.recipe.model.Instruction;

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
