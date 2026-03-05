package com.AllProTraining.project.Controller;

import com.AllProTraining.project.DTO.VehicleRequest;
import com.AllProTraining.project.Models.Vehicle;
import com.AllProTraining.project.Service.VehicleService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @GetMapping("/all")
    public List<Vehicle> findAllVehicles() {
        return vehicleService.findAll();
    }

    @GetMapping("/plate/{plateNum}")
    public Vehicle getByPlate(@PathVariable String plateNum) {
        return vehicleService.findByLicensePlate(plateNum);
    }

    @GetMapping("/id/{id}")
    public Vehicle getById(@PathVariable Long id) {return vehicleService.findById(id); }

    @PostMapping("/save")
    public ResponseEntity<Vehicle> registerVehicleControl(@RequestBody VehicleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleService.registerVehicle(request));
    }
}
