package com.example.backend.filters;

import com.example.backend.controller.UserController;
import com.example.backend.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

//@Provider //TODO uncomment
public class AuthorizationFilter implements ContainerRequestFilter {
    @Inject
    private UserService userService;


    @Override
    public void filter(ContainerRequestContext requestContext) {
        if (!this.isAuthRequired(requestContext)) {
            return;
        }

        String token = requestContext.getHeaderString("Authorization");

        if (token != null && token.startsWith("Bearer "))
            token = token.replace("Bearer ", "");

        try {
            if (!this.userService.isAuthorized(token))
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        } catch (Exception e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private boolean isAuthRequired(ContainerRequestContext requestContext) {
        if (requestContext.getUriInfo().getPath().contains("login"))
            return false;

        for (Object matchedResource : requestContext.getUriInfo().getMatchedResources()) {
            if (matchedResource instanceof UserController)
                return true;
        }

        return false;
    }
}
