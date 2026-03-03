package com.AllProTraining.project.Controller;

import com.AllProTraining.project.Models.Vehicle;
import com.AllProTraining.project.Service.VehicleService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
