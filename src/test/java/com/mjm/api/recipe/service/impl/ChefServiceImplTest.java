package com.mjm.api.recipe.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.mjm.api.recipe.exception.InvalidRequestException;
import com.mjm.api.recipe.exception.ResourceNotFoundException;
import com.mjm.api.recipe.model.Chef;
import com.mjm.api.recipe.dto.ChangeRequest.UpdateChefRequest;
import com.mjm.api.recipe.repository.ChefRepository;

@ExtendWith(MockitoExtension.class)
class ChefServiceImplTest {

    @Mock
    private ChefRepository chefRepository;

    @InjectMocks
    private ChefServiceImpl chefService;

    @Test
    void getChefByIdReturnsChefWhenFound() {
        Chef chef = new Chef();
        ReflectionTestUtils.setField(chef, "id", 1L);
        ReflectionTestUtils.setField(chef, "username", "chef1");
        ReflectionTestUtils.setField(chef, "password", "secret");

        when(chefRepository.findById(1L)).thenReturn(Optional.of(chef));

        Chef result = chefService.getChef(1L);

        assertSame(chef, result);
    }

    @Test
    void getChefByUsernameReturnsChefWhenFound() {
        Chef chef = new Chef();
        ReflectionTestUtils.setField(chef, "id", 1L);
        ReflectionTestUtils.setField(chef, "username", "chef1");
        ReflectionTestUtils.setField(chef, "password", "secret");

        when(chefRepository.findByUsernameIgnoreCase("chef1")).thenReturn(Optional.of(chef));

        Chef result = chefService.getChef("chef1");

        assertSame(chef, result);
    }

    @Test
    void getAllChefsReturnsListFromRepository() {
        Chef chef = new Chef();
        ReflectionTestUtils.setField(chef, "id", 1L);
        ReflectionTestUtils.setField(chef, "username", "chef1");
        ReflectionTestUtils.setField(chef, "password", "secret");

        when(chefRepository.findAll()).thenReturn(List.of(chef));

        List<Chef> result = chefService.getAllChefs();

        assertEquals(List.of(chef), result);
    }

    @Test
    void createChefDelegatesToRepository() {
        Chef chef = new Chef();

        chefService.createChef(chef);

        verify(chefRepository).save(chef);
    }

    @Test
    void deleteChefDeletesExistingChef() {
        Chef chef = new Chef();
        ReflectionTestUtils.setField(chef, "id", 1L);

        when(chefRepository.findById(1L)).thenReturn(Optional.of(chef));

        chefService.deleteChef(1L);

        verify(chefRepository).delete(chef);
    }

    @Test
    void updateChefDetailsUpdatesOnlyProvidedFields() {
        Chef chef = new Chef();
        ReflectionTestUtils.setField(chef, "id", 1L);
        ReflectionTestUtils.setField(chef, "username", "old-user");
        ReflectionTestUtils.setField(chef, "password", "old-pass");
        ReflectionTestUtils.setField(chef, "email", "old@example.com");

        UpdateChefRequest request = new UpdateChefRequest();
        ReflectionTestUtils.setField(request, "username", "new-user");
        ReflectionTestUtils.setField(request, "password", "new-pass");
        ReflectionTestUtils.setField(request, "email", "new@example.com");

        when(chefRepository.findById(1L)).thenReturn(Optional.of(chef));

        chefService.updateChefDetails(1L, request);

        assertEquals("new-user", chef.getUsername());
        assertEquals("new-pass", chef.getPassword());
        assertEquals("new@example.com", chef.getEmail());
        verify(chefRepository).save(chef);
    }

    @Test
    void getChefByIdThrowsResourceNotFoundWhenMissing() {
        when(chefRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> chefService.getChef(999L));
    }

    @Test
    void updateChefDetailsThrowsWhenRequestHasNoUpdatableFields() {
        Chef chef = new Chef();
        ReflectionTestUtils.setField(chef, "id", 1L);

        UpdateChefRequest request = new UpdateChefRequest();

        when(chefRepository.findById(1L)).thenReturn(Optional.of(chef));

        assertThrows(InvalidRequestException.class, () -> chefService.updateChefDetails(1L, request));
    }
}
