package org.garageApp.repository;

import org.garageApp.model.Garage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GarageRepository extends JpaRepository<Garage, Long> {
}
