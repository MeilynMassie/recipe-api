package com.mjm.api.recipe.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mjm.api.recipe.model.Instruction;
import com.mjm.api.recipe.model.ChangeRequest.UpdateInstructionRequest;
import com.mjm.api.recipe.service.InstructionService;

import jakarta.validation.Valid;

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

    @GetMapping("/{instructionId}")
    public Instruction getInstruction(@PathVariable Long instructionId, @PathVariable Long recipeId) {
        return instructionService.getInstruction(instructionId, recipeId);
    }

    @PostMapping
    public ResponseEntity<Void> createInstruction(@Valid @RequestBody Instruction instruction, @PathVariable Long recipeId) {
        instructionService.createInstruction(instruction, recipeId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{instructionId}")
    public ResponseEntity<Void> deleteInstruction(@PathVariable Long instructionId) {
        instructionService.deleteInstruction(instructionId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{instructionId}")
    public ResponseEntity<Void> updateInstructionDetails(@PathVariable Long instructionId, @PathVariable Long recipeId, @RequestBody UpdateInstructionRequest instruction) {
        instructionService.updateInstructionDetails(instructionId, recipeId, instruction);
        return ResponseEntity.noContent().build();
    }
}