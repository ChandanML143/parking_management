package com.AllProTraining.project.Service;

import com.AllProTraining.project.Models.Vehicle;

import java.util.List;

public interface VehicleService {
    public List<Vehicle> findAll();
    public Vehicle findByLicensePlate(String plateNum);
    public Vehicle findById(Long id);
}
