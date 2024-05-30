package com.example.backend.repo;

import com.example.backend.entities.Destination;

import java.util.List;

public interface IDestinationRepository {

    List<Destination> getAllDestinations();
    Destination getDestinationById(int id);
    Destination addDestination(Destination destination);
    void deleteDestination(int id);
    Destination updateDestination(Destination destination);
}
