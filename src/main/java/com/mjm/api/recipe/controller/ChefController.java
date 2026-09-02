package com.mjm.api.recipe.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

import com.mjm.api.recipe.model.Chef;
import com.mjm.api.recipe.model.UpdateChefRequest;
import com.mjm.api.recipe.service.ChefService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


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
            throw new RuntimeException("Either 'id' or 'username' must be provided");
        }
    }

    @GetMapping("/all")
    public List<Chef> getAllChefs() {
        return chefService.getAllChefs();
    }

    @PostMapping
    public void createChef(@RequestBody Chef chef) {
        chefService.createChef(chef);
    }

    @DeleteMapping("/{id}")
    public void deleteChef(@PathVariable Long id) {
        chefService.deleteChef(id);
    }

    @PatchMapping("/{id}")
    public String updateChefDetails(@PathVariable Long id, @RequestBody UpdateChefRequest chef) {
        chefService.updateChefDetails(id, chef);
        return "Chef updated successfully";
    }
}