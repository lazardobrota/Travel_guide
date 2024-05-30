package com.example.backend.services;

import com.example.backend.entities.Destination;
import com.example.backend.repo.IDestinationRepository;

import javax.inject.Inject;
import java.util.List;

public class DestinationService {
    @Inject
    private IDestinationRepository destinationRepository;

    public List<Destination> getAllDestinations() {
        return destinationRepository.getAllDestinations();
    }
    public Destination getDestinationById(int id) {
        return destinationRepository.getDestinationById(id);
    }
    public Destination addDestination(Destination destination) {
        return destinationRepository.addDestination(destination);
    }
    public void deleteDestination(int id) {
        destinationRepository.deleteDestination(id);
    }
    public Destination updateDestination(Destination destination) {
        return  destinationRepository.updateDestination(destination);
    }
}
