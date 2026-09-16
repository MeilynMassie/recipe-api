package com.mjm.api.recipe.dto.ChangeRequest;

import lombok.Getter;

@Getter 
public class UpdateInstructionRequest {
    private Integer step_number;

    private String description;
}
