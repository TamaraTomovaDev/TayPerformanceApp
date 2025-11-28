package org.garageApp.controller;

import jakarta.validation.Valid;
import org.garageApp.model.Garage;
import org.garageApp.service.GarageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/garages")
public class GarageController {

    private final GarageService garageService;

    public GarageController(GarageService garageService) {
        this.garageService = garageService;
    }

    @GetMapping
    public ResponseEntity<List<Garage>> getAllGarages() {
        List<Garage> garages = garageService.getAllGarages();
        return ResponseEntity.ok(garages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Garage> getGarageById(@PathVariable Long id) {
        Garage garage = garageService.getGarageById(id);
        return ResponseEntity.ok(garage);
    }

    @PostMapping
    public ResponseEntity<Garage> createGarage(@Valid @RequestBody Garage garage) {
        Garage savedGarage = garageService.createGarage(garage);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGarage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Garage> updateGarage(@PathVariable Long id,
                                               @Valid @RequestBody Garage garageDetails) {
        Garage updatedGarage = garageService.updateGarage(id, garageDetails);
        return ResponseEntity.ok(updatedGarage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGarage(@PathVariable Long id) {
        garageService.deleteGarage(id);
        return ResponseEntity.noContent().build();
    }
}
