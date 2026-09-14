package com.mjm.api.recipe.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mjm.api.recipe.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByChefId(Long chefId);
}
