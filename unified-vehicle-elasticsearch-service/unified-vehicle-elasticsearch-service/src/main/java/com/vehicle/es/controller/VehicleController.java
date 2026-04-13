package com.vehicle.es.controller;

import com.vehicle.es.model.Vehicle;
import com.vehicle.es.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = "*", maxAge = 3600)
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
        Vehicle savedVehicle = vehicleService.saveVehicle(vehicle);
        return new ResponseEntity<>(savedVehicle, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable String id) {
        Optional<Vehicle> vehicle = vehicleService.getVehicleById(id);
        return vehicle.map(v -> new ResponseEntity<>(v, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable String id, @RequestBody Vehicle vehicle) {
        Vehicle updatedVehicle = vehicleService.updateVehicle(id, vehicle);
        if (updatedVehicle != null) {
            return new ResponseEntity<>(updatedVehicle, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable String id) {
        vehicleService.deleteVehicle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search/make")
    public ResponseEntity<List<Vehicle>> findByMake(@RequestParam String make) {
        List<Vehicle> vehicles = vehicleService.findByMake(make);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/search/model")
    public ResponseEntity<List<Vehicle>> findByModel(@RequestParam String model) {
        List<Vehicle> vehicles = vehicleService.findByModel(model);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/search/year")
    public ResponseEntity<List<Vehicle>> findByYear(@RequestParam Integer year) {
        List<Vehicle> vehicles = vehicleService.findByYear(year);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/search/status")
    public ResponseEntity<List<Vehicle>> findByStatus(@RequestParam String status) {
        List<Vehicle> vehicles = vehicleService.findByStatus(status);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/search/color")
    public ResponseEntity<List<Vehicle>> findByColor(@RequestParam String color) {
        List<Vehicle> vehicles = vehicleService.findByColor(color);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/search/make-pageable")
    public ResponseEntity<Page<Vehicle>> searchByMake(
            @RequestParam String make,
            Pageable pageable) {
        Page<Vehicle> vehicles = vehicleService.searchByMake(make, pageable);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/search/model-pageable")
    public ResponseEntity<Page<Vehicle>> searchByModel(
            @RequestParam String model,
            Pageable pageable) {
        Page<Vehicle> vehicles = vehicleService.searchByModel(model, pageable);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return new ResponseEntity<>("Elasticsearch API is running", HttpStatus.OK);
    }
}
