package com.mjm.api.recipe.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mjm.api.recipe.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    Optional<List<Ingredient>> findByRecipeId(Long recipeId);
    
}
