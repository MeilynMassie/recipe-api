package com.mjm.api.recipe.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mjm.api.recipe.model.Chef;
import com.mjm.api.recipe.dto.ChangeRequest.UpdateChefRequest;
import com.mjm.api.recipe.service.ChefService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("${app.api.base-path}/chef")
public class ChefController {

    private final ChefService chefService;
    private final PasswordEncoder passwordEncoder;

    public ChefController(ChefService chefService, PasswordEncoder passwordEncoder) {
        this.chefService = chefService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public Chef getChef(@RequestParam(required = false) Long id, @RequestParam(required = false) String username) {
        if (id != null) {
            return chefService.getChef(id);
        } else if (username != null) {
            return chefService.getChef(username);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Either 'id' or 'username' must be provided");
        }
    }

    @GetMapping("/all")
    public List<Chef> getAllChefs() {
        return chefService.getAllChefs();
    }

    @PostMapping
    public ResponseEntity<Void> createChef(@Valid @RequestBody Chef chef) {
        chef.setPassword(passwordEncoder.encode(chef.getPassword()));
        chefService.createChef(chef);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChef(@PathVariable Long id) {
        chefService.deleteChef(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateChefDetails(@PathVariable Long id, @RequestBody UpdateChefRequest chef) {
        chefService.updateChefDetails(id, chef);
        return ResponseEntity.noContent().build();
    }
}