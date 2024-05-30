package com.example.backend.controller;

import com.example.backend.entities.Activity;
import com.example.backend.services.ActivityService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/activity")
public class ActivityController {

    @Inject
    private ActivityService activityService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllActivities() {
        return Response.ok(activityService.getAllActivities()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Activity getActivityById(@PathParam("id") int id) {
        return activityService.getActivityById(id);
    }

    //todo this url is bad, make it query instead
    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Activity getActivityByName(@PathParam("name") String name) {
        return activityService.getActivityByName(name);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Activity addActivity(@Valid Activity activity) {
        return activityService.addActivity(activity);
    }
}
