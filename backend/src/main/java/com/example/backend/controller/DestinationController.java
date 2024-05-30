package com.example.backend.controller;

import com.example.backend.entities.Destination;
import com.example.backend.services.DestinationService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/destination")
public class DestinationController {

    @Inject
    private DestinationService destinationService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDestinations() {
        return Response.ok(destinationService.getAllDestinations()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Destination getDestinationById(@PathParam("id") int id) {
        return destinationService.getDestinationById(id);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteDestination(@PathParam("id") int id) {
        destinationService.deleteDestination(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Destination addDestination(@Valid Destination destination) {
        return destinationService.addDestination(destination);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Destination updateDestination(@Valid Destination destination) {
        return destinationService.updateDestination(destination);
    }
}
