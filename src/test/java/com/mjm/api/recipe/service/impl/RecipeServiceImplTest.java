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

import com.mjm.api.recipe.exception.ResourceNotFoundException;
import com.mjm.api.recipe.model.Chef;
import com.mjm.api.recipe.model.Ingredient;
import com.mjm.api.recipe.model.Instruction;
import com.mjm.api.recipe.model.Recipe;
import com.mjm.api.recipe.model.ChangeRequest.UpdateRecipeRequest;
import com.mjm.api.recipe.repository.ChefRepository;
import com.mjm.api.recipe.repository.RecipeRepository;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private ChefRepository chefRepository;

    @InjectMocks
    private RecipeServiceImpl recipeService;

    @Test
    void getRecipeReturnsRecipeWhenFound() {
        Recipe recipe = new Recipe();
        ReflectionTestUtils.setField(recipe, "id", 1L);

        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));

        Recipe result = recipeService.getRecipe(1L);

        assertSame(recipe, result);
    }

    @Test
    void getRecipesReturnsRecipesWhenFound() {
        Recipe recipe = new Recipe();
        ReflectionTestUtils.setField(recipe, "id", 1L);

        when(recipeRepository.findByChefId(5L)).thenReturn(List.of(recipe));

        List<Recipe> result = recipeService.getRecipes(5L);

        assertEquals(List.of(recipe), result);
    }

    @Test
    void getRecipesThrowsWhenChefHasNoRecipes() {
        when(recipeRepository.findByChefId(5L)).thenReturn(List.of());

        assertThrows(ResourceNotFoundException.class, () -> recipeService.getRecipes(5L));
    }

    @Test
    void createRecipeLinksChildEntitiesToRecipeAndChef() {
        Chef chef = new Chef();
        ReflectionTestUtils.setField(chef, "id", 7L);

        Recipe recipe = new Recipe();
        Ingredient ingredient = new Ingredient();
        Instruction instruction = new Instruction();
        ReflectionTestUtils.setField(recipe, "ingredients", List.of(ingredient));
        ReflectionTestUtils.setField(recipe, "instructions", List.of(instruction));

        when(chefRepository.findById(7L)).thenReturn(Optional.of(chef));

        recipeService.createRecipe(recipe, 7L);

        assertSame(chef, recipe.getChef());
        assertSame(recipe, ingredient.getRecipe());
        assertSame(recipe, instruction.getRecipe());
        verify(recipeRepository).save(recipe);
    }

    @Test
    void deleteRecipeDeletesExistingRecipe() {
        Recipe recipe = new Recipe();
        ReflectionTestUtils.setField(recipe, "id", 1L);

        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));

        recipeService.deleteRecipe(1L);

        verify(recipeRepository).delete(recipe);
    }

    @Test
    void updateRecipeDetailsUpdatesOnlyProvidedFields() {
        Recipe recipe = new Recipe();
        ReflectionTestUtils.setField(recipe, "id", 1L);
        ReflectionTestUtils.setField(recipe, "name", "Old name");
        ReflectionTestUtils.setField(recipe, "prep_time", 10);
        ReflectionTestUtils.setField(recipe, "cook_time", 20);
        ReflectionTestUtils.setField(recipe, "servings", 4);

        UpdateRecipeRequest request = new UpdateRecipeRequest();
        ReflectionTestUtils.setField(request, "name", "New name");
        ReflectionTestUtils.setField(request, "prep_time", 12);
        ReflectionTestUtils.setField(request, "cook_time", 22);
        ReflectionTestUtils.setField(request, "servings", 5);

        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));

        recipeService.updateRecipeDetails(1L, request);

        assertEquals("New name", recipe.getName());
        assertEquals(12, recipe.getPrep_time());
        assertEquals(22, recipe.getCook_time());
        assertEquals(5, recipe.getServings());
        verify(recipeRepository).save(recipe);
    }

    @Test
    void getRecipeThrowsResourceNotFoundWhenMissing() {
        when(recipeRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> recipeService.getRecipe(999L));
    }
}
