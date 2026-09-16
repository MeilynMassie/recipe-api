package com.mjm.api.recipe.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mjm.api.recipe.exception.InvalidRequestException;
import com.mjm.api.recipe.model.Instruction;
import com.mjm.api.recipe.model.Recipe;
import com.mjm.api.recipe.dto.ChangeRequest.UpdateInstructionRequest;
import com.mjm.api.recipe.repository.InstructionRepository;
import com.mjm.api.recipe.repository.RecipeRepository;
import com.mjm.api.recipe.service.InstructionService;
import com.mjm.api.recipe.exception.ResourceNotFoundException;


@Service
public class InstructionServiceImpl implements InstructionService {
    private InstructionRepository instructionRepository;
    private RecipeRepository recipeRepository;

    public InstructionServiceImpl(InstructionRepository instructionRepository, RecipeRepository recipeRepository) {
        this.instructionRepository = instructionRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Instruction> getInstructions(Long recipeId) {
        recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe", recipeId));

        return instructionRepository.findByRecipeId(recipeId);
    }

    @Override
    public Instruction getInstruction(Long instructionId, Long recipeId) {
        return instructionRepository.findByIdAndRecipeId(instructionId, recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Instruction not found due to Recipe", recipeId));
    }

    @Override
    public void createInstruction(Instruction instruction, Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new ResourceNotFoundException("Recipe", recipeId));
        instruction.setRecipe(recipe);
        instructionRepository.save(instruction);
    }

    @Override
    public void deleteInstruction(Long instructionId) {
        Instruction instruction = instructionRepository.findById(instructionId)
                .orElseThrow(() -> new ResourceNotFoundException("Instruction", instructionId));
        instructionRepository.delete(instruction);
    }

    @Override
    public void updateInstructionDetails(Long instructionId, Long recipeId, UpdateInstructionRequest instructionChangeRequest) {
        Instruction instruction = instructionRepository.findByIdAndRecipeId(instructionId, recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Instruction not found due to Recipe", recipeId));

        if (!UpdateRequestValidator.hasAnyInstructionUpdate(instructionChangeRequest)) {
            throw new InvalidRequestException("PATCH request body must contain at least one valid field to update");
        }

        if (instructionChangeRequest.getStep_number() != null) {
            instruction.setStep_number(instructionChangeRequest.getStep_number());
        }
        if (instructionChangeRequest.getDescription() != null) {
            instruction.setDescription(instructionChangeRequest.getDescription());
        }

        instructionRepository.save(instruction);
    }
}