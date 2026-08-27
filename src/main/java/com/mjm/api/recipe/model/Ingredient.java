package com.mjm.api.recipe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String quantity;

    private String unit;

    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    Recipe recipe;
}
