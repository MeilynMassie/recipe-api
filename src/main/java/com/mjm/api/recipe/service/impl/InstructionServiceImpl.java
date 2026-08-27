package com.mjm.api.recipe.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mjm.api.recipe.model.Instruction;
import com.mjm.api.recipe.repository.InstructionRepository;
import com.mjm.api.recipe.service.InstructionService;
import com.mjm.api.recipe.exception.ResourceNotFoundException;


@Service
public class InstructionServiceImpl implements InstructionService {
    private InstructionRepository instructionRepository;

    public InstructionServiceImpl(InstructionRepository instructionRepository) {
        this.instructionRepository = instructionRepository;
    }

    @Override
    public List<Instruction> getInstructions(Long recipeId) {
        return instructionRepository.findByRecipeId(recipeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Instruction not found due to Recipe", recipeId));
    }
}