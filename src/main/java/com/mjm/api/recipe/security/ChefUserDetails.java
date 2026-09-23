package com.mjm.api.recipe.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mjm.api.recipe.model.Chef;

public class ChefUserDetails implements UserDetails {

    private final Chef chef;

    public ChefUserDetails(Chef chef) {
        this.chef = chef;
    }

    public String getEmail() {
        return chef.getEmail();
    }

    @Override
    public String getUsername() {
        return chef.getUsername();
    }

    @Override
    public String getPassword() {
        return chef.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority("ROLE_CHEF")
        );
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}