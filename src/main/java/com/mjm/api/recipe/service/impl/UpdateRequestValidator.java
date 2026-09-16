package com.mjm.api.recipe.service.impl;

import com.mjm.api.recipe.dto.ChangeRequest.UpdateChefRequest;
import com.mjm.api.recipe.dto.ChangeRequest.UpdateIngredientRequest;
import com.mjm.api.recipe.dto.ChangeRequest.UpdateInstructionRequest;
import com.mjm.api.recipe.dto.ChangeRequest.UpdateRecipeRequest;

final class UpdateRequestValidator {
    private UpdateRequestValidator() {
    }

    static boolean hasAnyChefUpdate(UpdateChefRequest request) {
        return request.getUsername() != null
                || request.getPassword() != null
                || request.getEmail() != null;
    }

    static boolean hasAnyRecipeUpdate(UpdateRecipeRequest request) {
        return request.getName() != null
                || request.getPrep_time() != null
                || request.getCook_time() != null
                || request.getServings() != null;
    }

    static boolean hasAnyIngredientUpdate(UpdateIngredientRequest request) {
        return request.getQuantity() != null
                || request.getUnit() != null
                || request.getName() != null
                || request.getSection() != null;
    }

    static boolean hasAnyInstructionUpdate(UpdateInstructionRequest request) {
        return request.getStep_number() != null
                || request.getDescription() != null;
    }
}
