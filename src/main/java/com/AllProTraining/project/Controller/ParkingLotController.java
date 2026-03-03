package com.AllProTraining.project.Controller;

import com.AllProTraining.project.DTO.ParkingLotSummaryResponse;
import com.AllProTraining.project.Models.ParkingLot;
import com.AllProTraining.project.Service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingLotController {

    @Autowired
    ParkingService parkingService;

    @GetMapping("/lots")
    public List<ParkingLotSummaryResponse> getAllParkingLotController() {
        return parkingService.getAllParkingLots();
    }

}
