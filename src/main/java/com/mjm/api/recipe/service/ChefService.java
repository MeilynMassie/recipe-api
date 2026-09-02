package com.mjm.api.recipe.service;

import com.mjm.api.recipe.model.Chef;
import com.mjm.api.recipe.model.UpdateChefRequest;

import java.util.List;

public interface ChefService {
    Chef getChef(Long id);
    Chef getChef(String username);
    List<Chef> getAllChefs();
    void createChef(Chef chef);
    void deleteChef(Long id);
    void updateChefDetails(Long id, UpdateChefRequest chef);
}
