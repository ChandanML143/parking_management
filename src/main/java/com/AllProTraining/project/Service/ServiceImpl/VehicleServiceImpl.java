package com.AllProTraining.project.Service.ServiceImpl;

import com.AllProTraining.project.Models.Vehicle;
import com.AllProTraining.project.Repository.VehicleRepository;
import com.AllProTraining.project.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle findByLicensePlate(String plateNum) {
        return vehicleRepository.findByLicensePlate(plateNum).orElseThrow(() -> new RuntimeException("Vehicle Not found"));
    }
}
