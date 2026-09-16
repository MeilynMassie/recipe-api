package com.mjm.api.recipe.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.mjm.api.recipe.exception.InvalidRequestException;
import com.mjm.api.recipe.exception.ResourceNotFoundException;
import com.mjm.api.recipe.model.Instruction;
import com.mjm.api.recipe.model.Recipe;
import com.mjm.api.recipe.dto.ChangeRequest.UpdateInstructionRequest;
import com.mjm.api.recipe.repository.InstructionRepository;
import com.mjm.api.recipe.repository.RecipeRepository;

@ExtendWith(MockitoExtension.class)
class InstructionServiceImplTest {

    @Mock
    private InstructionRepository instructionRepository;

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private InstructionServiceImpl instructionService;

    @Test
    void getInstructionsReturnsInstructionsWhenFound() {
        Recipe recipe = new Recipe();
        ReflectionTestUtils.setField(recipe, "id", 5L);

        Instruction instruction = new Instruction();
        ReflectionTestUtils.setField(instruction, "id", 1L);

        when(recipeRepository.findById(5L)).thenReturn(Optional.of(recipe));
        when(instructionRepository.findByRecipeId(5L)).thenReturn(List.of(instruction));

        List<Instruction> result = instructionService.getInstructions(5L);

        assertEquals(List.of(instruction), result);
    }

    @Test
    void getInstructionsReturnsEmptyListWhenRecipeExistsButHasNoInstructions() {
        Recipe recipe = new Recipe();
        ReflectionTestUtils.setField(recipe, "id", 5L);

        when(recipeRepository.findById(5L)).thenReturn(Optional.of(recipe));
        when(instructionRepository.findByRecipeId(5L)).thenReturn(List.of());

        List<Instruction> result = instructionService.getInstructions(5L);

        assertEquals(List.of(), result);
    }

    @Test
    void getInstructionsThrowsWhenRecipeDoesNotExist() {
        when(recipeRepository.findById(5L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> instructionService.getInstructions(5L));
    }

    @Test
    void getInstructionReturnsInstructionWhenFound() {
        Instruction instruction = new Instruction();
        ReflectionTestUtils.setField(instruction, "id", 1L);

        when(instructionRepository.findByIdAndRecipeId(1L, 5L)).thenReturn(Optional.of(instruction));

        Instruction result = instructionService.getInstruction(1L, 5L);

        assertSame(instruction, result);
    }

    @Test
    void createInstructionLinksRecipeAndSavesIt() {
        Recipe recipe = new Recipe();
        ReflectionTestUtils.setField(recipe, "id", 5L);

        Instruction instruction = new Instruction();

        when(recipeRepository.findById(5L)).thenReturn(Optional.of(recipe));

        instructionService.createInstruction(instruction, 5L);

        assertSame(recipe, instruction.getRecipe());
        verify(instructionRepository).save(instruction);
    }

    @Test
    void deleteInstructionDeletesExistingInstruction() {
        Instruction instruction = new Instruction();
        ReflectionTestUtils.setField(instruction, "id", 1L);

        when(instructionRepository.findById(1L)).thenReturn(Optional.of(instruction));

        instructionService.deleteInstruction(1L);

        verify(instructionRepository).delete(instruction);
    }

    @Test
    void updateInstructionDetailsUpdatesOnlyProvidedFields() {
        Instruction instruction = new Instruction();
        ReflectionTestUtils.setField(instruction, "id", 1L);
        ReflectionTestUtils.setField(instruction, "step_number", 1);
        ReflectionTestUtils.setField(instruction, "description", "Old description");

        UpdateInstructionRequest request = new UpdateInstructionRequest();
        ReflectionTestUtils.setField(request, "step_number", 2);
        ReflectionTestUtils.setField(request, "description", "New description");

        when(instructionRepository.findByIdAndRecipeId(1L, 5L)).thenReturn(Optional.of(instruction));

        instructionService.updateInstructionDetails(1L, 5L, request);

        assertEquals(2, instruction.getStep_number());
        assertEquals("New description", instruction.getDescription());
        verify(instructionRepository).save(instruction);
    }

    @Test
    void updateInstructionDetailsThrowsWhenRequestHasNoUpdatableFields() {
        Instruction instruction = new Instruction();
        ReflectionTestUtils.setField(instruction, "id", 1L);

        UpdateInstructionRequest request = new UpdateInstructionRequest();

        when(instructionRepository.findByIdAndRecipeId(1L, 5L)).thenReturn(Optional.of(instruction));

        assertThrows(InvalidRequestException.class, () -> instructionService.updateInstructionDetails(1L, 5L, request));
    }
}
