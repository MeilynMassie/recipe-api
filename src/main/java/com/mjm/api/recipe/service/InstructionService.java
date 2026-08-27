package com.mjm.api.recipe.service;

import java.util.List;

import com.mjm.api.recipe.model.Instruction;

public interface InstructionService {
    List<Instruction> getInstructions(Long recipeId);
}