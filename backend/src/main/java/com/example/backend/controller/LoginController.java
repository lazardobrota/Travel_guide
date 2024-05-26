package com.example.backend.controller;

import com.example.backend.entities.user.User;
import com.example.backend.entities.user.UserLogin;
import com.example.backend.services.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/login")
public class LoginController {
    @Inject
    private UserService userService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Valid UserLogin userLogin) {
        Map<String, String> response = new HashMap<>();
        String jwt = userService.login(userLogin.getEmail(), userLogin.getPassword());

        if (jwt == null) {
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        }

        User user = userService.getUserByEmail(userLogin.getEmail());
        response.put("jwt", jwt);
        response.put("name", user.getName());
        response.put("role", String.valueOf(user.getUserTypeId()));

        return Response.ok(response).build();
    }
}
