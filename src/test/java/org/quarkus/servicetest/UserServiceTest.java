package org.quarkus.servicetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.quarkus.model.User;
import org.quarkus.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
class UserServiceTest {
    private UserService userService;
    private EntityManager entityManager;
    private TypedQuery typedQuery;

    @BeforeEach
    void setUp() {
        entityManager = mock(EntityManager.class);
        typedQuery = mock(TypedQuery.class);
        userService = mock(UserService.class);
    }

    @Test
    void testGetUserByUsernameWhenUsersFound() throws JsonProcessingException {
        List<User> users = new ArrayList<>();
        when(entityManager.createNamedQuery("findUserByUserUsernameOrUserEmail", User.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter(1, "myusufalpian")).thenReturn(typedQuery);
        when(typedQuery.setParameter(2, 0)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(users);
        Response response = userService.getUserByUsername("myusufalpian");
        assertNotEquals(Response.Status.OK.getStatusCode(), response.getStatus(), "Response status should be 200 OK");
    }

    @Test
    void testGetUserByUsernameWhenNoUsersFound() throws JsonProcessingException {
        when(entityManager.createNamedQuery("findUserByUserUsernameOrUserEmail", User.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter(1, "biji bapak kau")).thenReturn(typedQuery);
        when(typedQuery.setParameter(2, 0)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(null);
        Response response = userService.getUserByUsername("myusufalpian");
        // Assert the response status
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus(), "Response status should be 404 Not Found");
        // Assert the response content
        assertTrue(response.getEntity().toString().contains("Data not found"), "Response should contain not found message");
    }
}
