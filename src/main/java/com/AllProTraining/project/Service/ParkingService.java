package com.AllProTraining.project.Service;

import com.AllProTraining.project.DTO.ParkingLotSummaryResponse;
import com.AllProTraining.project.Models.ParkingLot;

import java.util.List;

public interface ParkingService {

    public List<ParkingLotSummaryResponse> getAllParkingLots();
}
