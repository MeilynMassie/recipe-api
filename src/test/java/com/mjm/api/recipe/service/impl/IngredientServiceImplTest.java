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
import com.mjm.api.recipe.model.Ingredient;
import com.mjm.api.recipe.model.Recipe;
import com.mjm.api.recipe.model.ChangeRequest.UpdateIngredientRequest;
import com.mjm.api.recipe.repository.IngredientRepository;
import com.mjm.api.recipe.repository.RecipeRepository;

@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {

    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private IngredientServiceImpl ingredientService;

    @Test
    void getIngredientsReturnsIngredientsWhenFound() {
        Recipe recipe = new Recipe();
        ReflectionTestUtils.setField(recipe, "id", 5L);

        Ingredient ingredient = new Ingredient();
        ReflectionTestUtils.setField(ingredient, "id", 1L);

        when(recipeRepository.findById(5L)).thenReturn(Optional.of(recipe));
        when(ingredientRepository.findByRecipeId(5L)).thenReturn(List.of(ingredient));

        List<Ingredient> result = ingredientService.getIngredients(5L);

        assertEquals(List.of(ingredient), result);
    }

    @Test
    void getIngredientsReturnsEmptyListWhenRecipeExistsButHasNoIngredients() {
        Recipe recipe = new Recipe();
        ReflectionTestUtils.setField(recipe, "id", 5L);

        when(recipeRepository.findById(5L)).thenReturn(Optional.of(recipe));
        when(ingredientRepository.findByRecipeId(5L)).thenReturn(List.of());

        List<Ingredient> result = ingredientService.getIngredients(5L);

        assertEquals(List.of(), result);
    }

    @Test
    void getIngredientsThrowsWhenRecipeDoesNotExist() {
        when(recipeRepository.findById(5L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> ingredientService.getIngredients(5L));
    }

    @Test
    void getIngredientReturnsIngredientWhenFound() {
        Ingredient ingredient = new Ingredient();
        ReflectionTestUtils.setField(ingredient, "id", 1L);

        when(ingredientRepository.findByIdAndRecipeId(1L, 5L)).thenReturn(Optional.of(ingredient));

        Ingredient result = ingredientService.getIngredient(1L, 5L);

        assertSame(ingredient, result);
    }

    @Test
    void createIngredientLinksRecipeAndSavesIt() {
        Recipe recipe = new Recipe();
        ReflectionTestUtils.setField(recipe, "id", 5L);

        Ingredient ingredient = new Ingredient();

        when(recipeRepository.findById(5L)).thenReturn(Optional.of(recipe));

        ingredientService.createIngredient(ingredient, 5L);

        assertSame(recipe, ingredient.getRecipe());
        verify(ingredientRepository).save(ingredient);
    }

    @Test
    void deleteIngredientDeletesExistingIngredient() {
        Ingredient ingredient = new Ingredient();
        ReflectionTestUtils.setField(ingredient, "id", 1L);

        when(ingredientRepository.findById(1L)).thenReturn(Optional.of(ingredient));

        ingredientService.deleteIngredient(1L);

        verify(ingredientRepository).delete(ingredient);
    }

    @Test
    void updateIngredientDetailsUpdatesOnlyProvidedFields() {
        Ingredient ingredient = new Ingredient();
        ReflectionTestUtils.setField(ingredient, "id", 1L);
        ReflectionTestUtils.setField(ingredient, "quantity", "1");
        ReflectionTestUtils.setField(ingredient, "unit", "cup");
        ReflectionTestUtils.setField(ingredient, "name", "Old flour");
        ReflectionTestUtils.setField(ingredient, "section", "dry");

        UpdateIngredientRequest request = new UpdateIngredientRequest();
        ReflectionTestUtils.setField(request, "quantity", "2");
        ReflectionTestUtils.setField(request, "unit", "cups");
        ReflectionTestUtils.setField(request, "name", "New flour");
        ReflectionTestUtils.setField(request, "section", "baking");

        when(ingredientRepository.findByIdAndRecipeId(1L, 5L)).thenReturn(Optional.of(ingredient));

        ingredientService.updateIngredientDetails(1L, 5L, request);

        assertEquals("2", ingredient.getQuantity());
        assertEquals("cups", ingredient.getUnit());
        assertEquals("New flour", ingredient.getName());
        assertEquals("baking", ingredient.getSection());
        verify(ingredientRepository).save(ingredient);
    }

    @Test
    void updateIngredientDetailsThrowsWhenRequestHasNoUpdatableFields() {
        Ingredient ingredient = new Ingredient();
        ReflectionTestUtils.setField(ingredient, "id", 1L);

        UpdateIngredientRequest request = new UpdateIngredientRequest();

        when(ingredientRepository.findByIdAndRecipeId(1L, 5L)).thenReturn(Optional.of(ingredient));

        assertThrows(InvalidRequestException.class, () -> ingredientService.updateIngredientDetails(1L, 5L, request));
    }
}
