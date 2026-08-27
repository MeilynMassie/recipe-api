package com.mjm.api.recipe.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mjm.api.recipe.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Optional<Recipe> findByChefId(Long chefId);
    
}
