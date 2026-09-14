package com.mjm.api.recipe.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mjm.api.recipe.model.Ingredient;
import com.mjm.api.recipe.model.Instruction;
import com.mjm.api.recipe.model.Recipe;
import com.mjm.api.recipe.model.UpdateIngredientRequest;
import com.mjm.api.recipe.model.UpdateInstructionRequest;
import com.mjm.api.recipe.repository.InstructionRepository;
import com.mjm.api.recipe.repository.RecipeRepository;
import com.mjm.api.recipe.service.InstructionService;
import com.mjm.api.recipe.exception.ResourceNotFoundException;


@Service
public class InstructionServiceImpl implements InstructionService {
    private InstructionRepository instructionRepository;
    private RecipeRepository recipeRepository;

    public InstructionServiceImpl(InstructionRepository instructionRepository) {
        this.instructionRepository = instructionRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Instruction> getInstructions(Long recipeId) {
        List<Instruction> instructions = instructionRepository.findByRecipeId(recipeId);

        if (instructions.isEmpty()) {
            throw new ResourceNotFoundException("Instruction not found due to Recipe", recipeId);
        }
        return instructions;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteInstruction'");
    }

    @Override
    public void updateInstructionDetails(Long instructionId, Long recipeId, UpdateInstructionRequest instruction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateInstructionDetails'");
    }
}