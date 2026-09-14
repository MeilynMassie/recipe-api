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

import com.mjm.api.recipe.model.Recipe;
import com.mjm.api.recipe.model.ChangeRequest.UpdateRecipeRequest;
import com.mjm.api.recipe.service.RecipeService;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private RecipeController controller;

    @Test
    void getRecipesDelegatesToService() {
        Recipe recipe = new Recipe();
        ReflectionTestUtils.setField(recipe, "id", 1L);

        when(recipeService.getRecipes(5L)).thenReturn(List.of(recipe));

        List<Recipe> result = controller.getRecipes(5L);

        assertEquals(List.of(recipe), result);
        verify(recipeService).getRecipes(5L);
    }

    @Test
    void getRecipeDelegatesToService() {
        Recipe recipe = new Recipe();
        ReflectionTestUtils.setField(recipe, "id", 1L);

        when(recipeService.getRecipe(1L)).thenReturn(recipe);

        Recipe result = controller.getRecipe(5L, 1L);

        assertSame(recipe, result);
        verify(recipeService).getRecipe(1L);
    }

    @Test
    void createRecipeDelegatesToService() {
        Recipe recipe = new Recipe();

        controller.createRecipe(recipe, 5L);

        verify(recipeService).createRecipe(recipe, 5L);
    }

    @Test
    void deleteRecipeDelegatesToService() {
        controller.deleteRecipe(1L);

        verify(recipeService).deleteRecipe(1L);
    }

    @Test
    void updateRecipeDetailsDelegatesToServiceAndReturnsNoContent() {
        UpdateRecipeRequest request = new UpdateRecipeRequest();

        ResponseEntity<Void> result = controller.updateRecipeDetails(1L, request);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(recipeService).updateRecipeDetails(1L, request);
    }
}
