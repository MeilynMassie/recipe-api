package com.mjm.api.recipe.security;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mjm.api.recipe.model.Chef;
import com.mjm.api.recipe.service.ChefService;

public class ChefUserDetailsService implements UserDetailsService {

    private final ChefService chefService;
    public ChefUserDetailsService(ChefService chefService) {
        this.chefService = chefService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
         Chef chef = chefService.getChefByLogin(login);

        return new ChefUserDetails(chef);
    }
}