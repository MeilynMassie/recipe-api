package com.mjm.api.recipe.service.impl;

import org.springframework.stereotype.Service;

import com.mjm.api.recipe.exception.ResourceNotFoundException;
import com.mjm.api.recipe.model.Chef;
import com.mjm.api.recipe.repository.ChefRepository;
import com.mjm.api.recipe.service.ChefService;

@Service
public class ChefServiceImpl implements ChefService {
    private final ChefRepository chefRepository;

    public ChefServiceImpl(ChefRepository chefRepository) {
        this.chefRepository = chefRepository;
    }

    @Override
    public Chef getChef(Long id) {
        return chefRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Chef", id));
    }

    @Override
    public Chef getChef(String username) {
        Chef chef = chefRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Chef", username));
        return chef;
    }
}
