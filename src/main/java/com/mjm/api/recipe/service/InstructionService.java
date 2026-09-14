package com.mjm.api.recipe.service;

import java.util.List;

import com.mjm.api.recipe.model.Instruction;
import com.mjm.api.recipe.model.ChangeRequest.UpdateInstructionRequest;

public interface InstructionService {
    List<Instruction> getInstructions(Long recipeId);
    Instruction getInstruction(Long instructionId, Long recipeId);
    void createInstruction(Instruction instruction, Long recipeId);
    void deleteInstruction(Long instructionId);
    void updateInstructionDetails(Long instructionId, Long recipeId, UpdateInstructionRequest instruction);
}