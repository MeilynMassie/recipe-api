package com.mjm.api.recipe.dto.Login;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {
    private String login;
    private String password;
}