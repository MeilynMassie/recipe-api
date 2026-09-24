package com.mjm.api.recipe.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.mjm.api.recipe.dto.Login.LoginRequest;
import com.mjm.api.recipe.dto.Login.LoginResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("${app.api.base-path}/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(
            loginRequest.getLogin(),
            loginRequest.getPassword()
        ));
        return new LoginResponse("Login successful");
    }
}