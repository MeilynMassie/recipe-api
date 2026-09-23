package com.mjm.api.recipe.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mjm.api.recipe.exception.InvalidRequestException;
import com.mjm.api.recipe.exception.ResourceNotFoundException;
import com.mjm.api.recipe.model.Chef;
import com.mjm.api.recipe.dto.ChangeRequest.UpdateChefRequest;
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
        return chefRepository.findByUsernameIgnoreCase(username).orElseThrow(() -> new ResourceNotFoundException("Chef", username));
    }

    @Override
    public List<Chef> getAllChefs() {
        return chefRepository.findAll();
    }

    @Override
    public void createChef(Chef chef) {
        chefRepository.save(chef);
    }

    @Override
    public void deleteChef(Long id) {
        Chef chef = chefRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Chef", id));
        chefRepository.delete(chef);
    }

    @Override
    public void updateChefDetails(Long id, UpdateChefRequest chefChangeRequest) {
        Chef chef = chefRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Chef", id));

        if (!UpdateRequestValidator.hasAnyChefUpdate(chefChangeRequest)) {
            throw new InvalidRequestException("PATCH request body must contain at least one valid field to update");
        }

        if (chefChangeRequest.getUsername() != null) {
            chef.setUsername(chefChangeRequest.getUsername());
        }
        if (chefChangeRequest.getPassword() != null) {
            chef.setPassword(chefChangeRequest.getPassword());
        }
        if (chefChangeRequest.getEmail() != null) {
            chef.setEmail(chefChangeRequest.getEmail());
        }

        chefRepository.save(chef);
    }

    @Override
    public Chef getChefByLogin(String login) {
        return chefRepository.findByEmailOrUsernameIgnoreCase(login, login).orElseThrow(() -> new ResourceNotFoundException("Chef", login));
    }
}
