package com.mjm.api.recipe.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mjm.api.recipe.model.Chef;
import com.mjm.api.recipe.service.ChefService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("${app.api.base-path}/chef")
public class ChefController {

    private final ChefService chefService;

    public ChefController(ChefService chefService) {
        this.chefService = chefService;
    }

    @GetMapping
    public Chef getChef(@RequestParam(required = false) Long id, @RequestParam(required = false) String username) {
        if (id != null) {
            return chefService.getChef(id);
        } else if (username != null) {
            return chefService.getChef(username);
        } else {
            throw new IllegalArgumentException("Either 'id' or 'username' must be provided");
        }
    }
    
}