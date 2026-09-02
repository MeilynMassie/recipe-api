package com.mjm.api.recipe.model;

import lombok.Getter;


@Getter
public class UpdateChefRequest {
    private String username;

    private String password;

    private String email;
}
