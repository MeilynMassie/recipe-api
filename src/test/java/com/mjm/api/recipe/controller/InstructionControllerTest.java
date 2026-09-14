package com.mjm.api.recipe.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.mjm.api.recipe.model.Instruction;
import com.mjm.api.recipe.model.ChangeRequest.UpdateInstructionRequest;
import com.mjm.api.recipe.service.InstructionService;

@ExtendWith(MockitoExtension.class)
class InstructionControllerTest {

    @Mock
    private InstructionService instructionService;

    @InjectMocks
    private InstructionController controller;

    @Test
    void getInstructionsDelegatesToService() {
        Instruction instruction = new Instruction();
        ReflectionTestUtils.setField(instruction, "id", 1L);

        when(instructionService.getInstructions(5L)).thenReturn(List.of(instruction));

        List<Instruction> result = controller.getInstructions(5L);

        assertEquals(List.of(instruction), result);
        verify(instructionService).getInstructions(5L);
    }

    @Test
    void getInstructionDelegatesToService() {
        Instruction instruction = new Instruction();
        ReflectionTestUtils.setField(instruction, "id", 1L);

        when(instructionService.getInstruction(1L, 5L)).thenReturn(instruction);

        Instruction result = controller.getInstruction(1L, 5L);

        assertSame(instruction, result);
        verify(instructionService).getInstruction(1L, 5L);
    }

    @Test
    void createInstructionDelegatesToService() {
        Instruction instruction = new Instruction();

        controller.createInstruction(instruction, 5L);

        verify(instructionService).createInstruction(instruction, 5L);
    }

    @Test
    void deleteInstructionDelegatesToService() {
        controller.deleteInstruction(1L);

        verify(instructionService).deleteInstruction(1L);
    }

    @Test
    void updateInstructionDetailsDelegatesToServiceAndReturnsSuccessMessage() {
        UpdateInstructionRequest request = new UpdateInstructionRequest();

        String result = controller.updateInstructionDetails(1L, 5L, request);

        assertEquals("Instruction updated successfully", result);
        verify(instructionService).updateInstructionDetails(1L, 5L, request);
    }
}
