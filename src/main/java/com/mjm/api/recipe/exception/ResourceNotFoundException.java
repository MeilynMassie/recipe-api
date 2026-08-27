package com.mjm.api.recipe.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, Long id) {
        super(resource + " not found with id: " + id);
    }

    public ResourceNotFoundException(String resource, String username) {
        super(resource + " not found with username: " + username);
    }
}
