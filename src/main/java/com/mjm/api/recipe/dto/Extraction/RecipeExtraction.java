package com.mjm.api.recipe.dto.Extraction;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class RecipeExtraction {
    private String name;
    private Integer prep_time;
    private Integer cook_time;
    private Integer servings;
    private List<IngredientExtraction> ingredients;
    private List<InstructionExtraction> instructions;
    // private SourceExtraction source;
    // TODO: Maybe use response to return ok or error so that fast api always returns 
    // something instead of having to worry about it crashing from invalid input
    // private String response;
}
