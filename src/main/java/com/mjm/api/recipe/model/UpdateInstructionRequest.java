package com.mjm.api.recipe.model;

import lombok.Getter;

@Getter 
public class UpdateInstructionRequest {
    private Integer step_number;

    private String description;
}
