package com.vehicle.es.service;

import com.vehicle.es.model.Vehicle;
import com.vehicle.es.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle saveVehicle(Vehicle vehicle) {
        if (vehicle.getId() == null) {
            vehicle.setId(UUID.randomUUID().toString());
        }
        return vehicleRepository.save(vehicle);
    }

    public Optional<Vehicle> getVehicleById(String id) {
        return vehicleRepository.findById(id);
    }

    public List<Vehicle> getAllVehicles() {
        return StreamSupport.stream(vehicleRepository.findAll().spliterator(), false)
                .toList();
    }

    public void deleteVehicle(String id) {
        vehicleRepository.deleteById(id);
    }

    public List<Vehicle> findByMake(String make) {
        return vehicleRepository.findByMake(make);
    }

    public List<Vehicle> findByModel(String model) {
        return vehicleRepository.findByModel(model);
    }

    public List<Vehicle> findByYear(Integer year) {
        return vehicleRepository.findByYear(year);
    }

    public List<Vehicle> findByStatus(String status) {
        return vehicleRepository.findByStatus(status);
    }

    public Page<Vehicle> searchByMake(String make, Pageable pageable) {
        return vehicleRepository.findByMakeContainingIgnoreCase(make, pageable);
    }

    public Page<Vehicle> searchByModel(String model, Pageable pageable) {
        return vehicleRepository.findByModelContainingIgnoreCase(model, pageable);
    }

    public List<Vehicle> findByColor(String color) {
        return vehicleRepository.findByColorContainingIgnoreCase(color);
    }

    public Vehicle updateVehicle(String id, Vehicle vehicleUpdates) {
        Optional<Vehicle> existingVehicle = vehicleRepository.findById(id);
        if (existingVehicle.isPresent()) {
            Vehicle vehicle = existingVehicle.get();
            if (vehicleUpdates.getMake() != null) vehicle.setMake(vehicleUpdates.getMake());
            if (vehicleUpdates.getModel() != null) vehicle.setModel(vehicleUpdates.getModel());
            if (vehicleUpdates.getYear() != null) vehicle.setYear(vehicleUpdates.getYear());
            if (vehicleUpdates.getColor() != null) vehicle.setColor(vehicleUpdates.getColor());
            if (vehicleUpdates.getPrice() != null) vehicle.setPrice(vehicleUpdates.getPrice());
            if (vehicleUpdates.getStatus() != null) vehicle.setStatus(vehicleUpdates.getStatus());
            return vehicleRepository.save(vehicle);
        }
        return null;
    }
}
