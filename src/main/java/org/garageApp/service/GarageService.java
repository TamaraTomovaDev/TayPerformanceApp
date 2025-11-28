package org.garageApp.service;

import jakarta.validation.Valid;
import org.garageApp.exception.ResourceNotFoundException;
import org.garageApp.model.Garage;
import org.garageApp.repository.GarageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GarageService {

    private final GarageRepository garageRepository;

    public GarageService(GarageRepository garageRepository) {
        this.garageRepository = garageRepository;
    }

    public List<Garage> getAllGarages() {
        return garageRepository.findAll();
    }

    public Garage getGarageById(Long id) {
        return garageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Garage met ID " + id + " niet gevonden"));
    }

    public Garage createGarage(@Valid Garage garage) {
        // JPA validatie via @NotBlank wordt automatisch gecontroleerd
        return garageRepository.save(garage);
    }

    public Garage updateGarage(Long id, @Valid Garage garageDetails) {
        Garage garage = garageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Garage met ID " + id + " niet gevonden"));

        garage.setName(garageDetails.getName());
        garage.setAddress(garageDetails.getAddress());
        garage.setCity(garageDetails.getCity());
        garage.setPostalCode(garageDetails.getPostalCode());

        return garageRepository.save(garage);
    }

    public void deleteGarage(Long id) {
        Garage garage = garageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Garage met ID " + id + " niet gevonden"));
        garageRepository.delete(garage);
    }
}
