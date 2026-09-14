package com.mjm.api.recipe.model;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message="Chef ID required")
    @ManyToOne
    @JoinColumn(name = "chef_id", nullable = false)
    private Chef chef;

    @NotBlank(message="Recipe name required")
    private String name;

    private Integer prep_time;

    private Integer cook_time;

    private Integer servings;

    @NotEmpty(message="List of ingredients required")
    @OneToMany(mappedBy = "recipe",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    
    private List<Ingredient> ingredients;

    @NotEmpty(message="List of instructions required")
    @OneToMany(mappedBy = "recipe",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Instruction> instructions;
}
