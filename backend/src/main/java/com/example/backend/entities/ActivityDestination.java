package com.example.backend.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ActivityDestination {

    private int id;

    @NotNull(message = "DestinationId field can't be null")
    private int destinationId;

    @NotNull(message = "ActivityId field can't be null")
    private int activityId;
}