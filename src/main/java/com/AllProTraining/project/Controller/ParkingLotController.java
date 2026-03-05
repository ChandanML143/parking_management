package com.AllProTraining.project.Controller;

import com.AllProTraining.project.DTO.ParkingLotSummaryResponse;
import com.AllProTraining.project.DTO.ParkingVehicleEntry;
import com.AllProTraining.project.DTO.TicketResponse;
import com.AllProTraining.project.Models.ParkingLot;
import com.AllProTraining.project.Service.ParkingService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // create an API toi find parking lot summary by parkingLotId

    @PostMapping("/entry")
    public ResponseEntity<TicketResponse> parkEntry(@RequestBody ParkingVehicleEntry request) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingService.parkEntry(request));
    }

}
