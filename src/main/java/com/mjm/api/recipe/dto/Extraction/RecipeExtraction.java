package com.mjm.api.recipe.dto.Extraction;

import java.util.List;

public class RecipeExtraction {
    private String name;
    private Integer prep_time;
    private Integer cook_time;
    private Integer servings;
    private List<IngredientExtraction> ingredients;
    private List<InstructionExtraction> instructions;
    // private SourceExtraction source;
}
