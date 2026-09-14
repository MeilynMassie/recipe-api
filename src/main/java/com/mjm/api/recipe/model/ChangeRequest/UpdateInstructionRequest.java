package com.mjm.api.recipe.model.ChangeRequest;

import lombok.Getter;

@Getter 
public class UpdateInstructionRequest {
    private Integer step_number;

    private String description;
}
