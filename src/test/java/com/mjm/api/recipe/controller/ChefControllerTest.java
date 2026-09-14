package com.mjm.api.recipe.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.mjm.api.recipe.model.Chef;
import com.mjm.api.recipe.model.ChangeRequest.UpdateChefRequest;
import com.mjm.api.recipe.service.ChefService;

@ExtendWith(MockitoExtension.class)
class ChefControllerTest {

    @Mock
    private ChefService chefService;

    @InjectMocks
    private ChefController controller;

    @Test
    void getChefByIdDelegatesToService() {
        Chef chef = new Chef();
        ReflectionTestUtils.setField(chef, "id", 1L);

        when(chefService.getChef(1L)).thenReturn(chef);

        Chef result = controller.getChef(1L, null);

        assertSame(chef, result);
        verify(chefService).getChef(1L);
    }

    @Test
    void getChefByUsernameDelegatesToService() {
        Chef chef = new Chef();
        ReflectionTestUtils.setField(chef, "id", 1L);

        when(chefService.getChef("chef1")).thenReturn(chef);

        Chef result = controller.getChef(null, "chef1");

        assertSame(chef, result);
        verify(chefService).getChef("chef1");
    }

    @Test
    void getChefWithoutIdentifierThrowsRuntimeException() {
        assertThrows(RuntimeException.class, () -> controller.getChef(null, null));
    }

    @Test
    void getAllChefsDelegatesToService() {
        Chef chef = new Chef();
        when(chefService.getAllChefs()).thenReturn(List.of(chef));

        List<Chef> result = controller.getAllChefs();

        assertEquals(List.of(chef), result);
        verify(chefService).getAllChefs();
    }

    @Test
    void createChefDelegatesToService() {
        Chef chef = new Chef();

        controller.createChef(chef);

        verify(chefService).createChef(chef);
    }

    @Test
    void deleteChefDelegatesToService() {
        controller.deleteChef(1L);

        verify(chefService).deleteChef(1L);
    }

    @Test
    void updateChefDetailsDelegatesToServiceAndReturnsNoContent() {
        UpdateChefRequest request = new UpdateChefRequest();

        ResponseEntity<Void> result = controller.updateChefDetails(1L, request);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(chefService).updateChefDetails(1L, request);
    }
}
