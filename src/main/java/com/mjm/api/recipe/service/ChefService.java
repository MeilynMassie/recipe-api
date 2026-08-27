package com.mjm.api.recipe.service;

import com.mjm.api.recipe.model.Chef;

public interface ChefService {
    Chef getChef(Long id);
    Chef getChef(String username);
}
