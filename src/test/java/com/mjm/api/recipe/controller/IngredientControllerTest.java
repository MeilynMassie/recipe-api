package com.mjm.api.recipe.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.mjm.api.recipe.model.Ingredient;
import com.mjm.api.recipe.model.ChangeRequest.UpdateIngredientRequest;
import com.mjm.api.recipe.service.IngredientService;

@ExtendWith(MockitoExtension.class)
class IngredientControllerTest {

    @Mock
    private IngredientService ingredientService;

    @InjectMocks
    private IngredientController controller;

    @Test
    void getIngredientsDelegatesToService() {
        Ingredient ingredient = new Ingredient();
        ReflectionTestUtils.setField(ingredient, "id", 1L);

        when(ingredientService.getIngredients(5L)).thenReturn(List.of(ingredient));

        List<Ingredient> result = controller.getIngredients(5L);

        assertEquals(List.of(ingredient), result);
        verify(ingredientService).getIngredients(5L);
    }

    @Test
    void getIngredientDelegatesToService() {
        Ingredient ingredient = new Ingredient();
        ReflectionTestUtils.setField(ingredient, "id", 1L);

        when(ingredientService.getIngredient(1L, 5L)).thenReturn(ingredient);

        Ingredient result = controller.getIngredient(1L, 5L);

        assertSame(ingredient, result);
        verify(ingredientService).getIngredient(1L, 5L);
    }

    @Test
    void createIngredientDelegatesToService() {
        Ingredient ingredient = new Ingredient();

        controller.createIngredient(ingredient, 5L);

        verify(ingredientService).createIngredient(ingredient, 5L);
    }

    @Test
    void deleteIngredientDelegatesToService() {
        controller.deleteIngredient(1L);

        verify(ingredientService).deleteIngredient(1L);
    }

    @Test
    void updateIngredientDetailsDelegatesToServiceAndReturnsNoContent() {
        UpdateIngredientRequest request = new UpdateIngredientRequest();

        ResponseEntity<Void> result = controller.updateIngredientDetails(1L, 5L, request);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(ingredientService).updateIngredientDetails(1L, 5L, request);
    }
}
