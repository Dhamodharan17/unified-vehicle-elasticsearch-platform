package com.vehicle.es.repository;

import com.vehicle.es.model.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends ElasticsearchRepository<Vehicle, String> {
    
    List<Vehicle> findByMake(String make);
    
    List<Vehicle> findByModel(String model);
    
    List<Vehicle> findByYear(Integer year);
    
    List<Vehicle> findByStatus(String status);
    
    Page<Vehicle> findByMakeContainingIgnoreCase(String make, Pageable pageable);
    
    Page<Vehicle> findByModelContainingIgnoreCase(String model, Pageable pageable);
    
    List<Vehicle> findByColorContainingIgnoreCase(String color);
}
