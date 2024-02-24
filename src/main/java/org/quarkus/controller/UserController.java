package org.quarkus.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.quarkus.dto.RequestNewUserDto;
import org.quarkus.service.UserService;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GET
    @Path("/panache")
    public Response getAllDataPanache() throws JsonProcessingException {
        return userService.getAllDataPanache();
    }

    @GET
    @Path("/panache/{username}")
    public Response getUserByUsername(@PathParam("username") String username) throws JsonProcessingException {
        return userService.getUserByUsername(username);
    }

    @POST
    @Path("/panache/addNewUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewUser(@RequestBody RequestNewUserDto request) throws JsonProcessingException {
        return userService.addNewUser(request);
    }
}
