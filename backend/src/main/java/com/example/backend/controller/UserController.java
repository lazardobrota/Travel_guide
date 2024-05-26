package com.example.backend.controller;

import com.example.backend.entities.user.UserType;
import com.example.backend.entities.user.User;
import com.example.backend.services.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserController {
    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        return Response.ok(userService.getAllUsers()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User addUser(@Valid User user) {
        return userService.addUser(user);
    }

    @POST
    @Path("/type")
    @Produces(MediaType.APPLICATION_JSON)
    public UserType addUserType(@Valid UserType userType) {
        return userService.addUserType(userType);
    }

    @GET
    @Path("/type/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserType getUserType(@PathParam("id") int id) {
        return userService.getUserTypeById(id);
    }
}