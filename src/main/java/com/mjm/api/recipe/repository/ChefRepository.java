package com.mjm.api.recipe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mjm.api.recipe.model.Chef;


public interface ChefRepository extends JpaRepository<Chef, Long> {
    Optional<Chef> findByUsername(String username);
}