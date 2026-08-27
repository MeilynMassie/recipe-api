package com.mjm.api.recipe.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mjm.api.recipe.model.Instruction;

public interface InstructionRepository extends JpaRepository<Instruction, Long> {

    Optional<List<Instruction>> findByRecipeId(Long recipeId);
}
