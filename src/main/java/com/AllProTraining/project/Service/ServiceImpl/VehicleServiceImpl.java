package com.AllProTraining.project.Service.ServiceImpl;

import com.AllProTraining.project.DTO.VehicleRequest;
import com.AllProTraining.project.Models.Vehicle;
import com.AllProTraining.project.Repository.VehicleRepository;
import com.AllProTraining.project.Service.VehicleService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public Vehicle findById(Long id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new RuntimeException("Vehicle Not found"));
    }

    @Override
    @Transactional
    public Vehicle registerVehicle(VehicleRequest request) {
        vehicleRepository.findByLicensePlate(request.getLicensePlate().toUpperCase()).ifPresent(v -> {
            try {
                throw new BadRequestException("Vehicle already registered: " + v.getLicensePlate());
            } catch (BadRequestException e) {
                throw new RuntimeException(e);
            }
        });
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate(request.getLicensePlate().toUpperCase());
        vehicle.setVehicleType(request.getVehicleType());
        vehicle.setOwnerName(request.getOwnerName());
        vehicle.setOwnerPhone(request.getOwnerPhone());

        return vehicleRepository.save(vehicle);
    }


}
