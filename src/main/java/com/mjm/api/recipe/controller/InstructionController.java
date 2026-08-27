package com.mjm.api.recipe.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mjm.api.recipe.model.Instruction;
import com.mjm.api.recipe.service.InstructionService;

@RestController
@RequestMapping("${app.api.base-path}/recipe/{recipeId}/instruction")
public class InstructionController {
    private final InstructionService instructionService;

    public InstructionController(InstructionService instructionService) {
        this.instructionService = instructionService;
    }

    @GetMapping
    public List<Instruction> getInstructions(@PathVariable Long recipeId) {
        return instructionService.getInstructions(recipeId);
    }
}