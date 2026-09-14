package com.mjm.api.recipe.model.ChangeRequest;

import lombok.Getter;

@Getter 
public class UpdateIngredientRequest {
    private String quantity;

    private String unit;

    private String name;

    private String section;
}
