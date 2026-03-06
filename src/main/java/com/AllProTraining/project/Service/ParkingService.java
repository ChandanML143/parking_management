package com.AllProTraining.project.Service;

import com.AllProTraining.project.DTO.*;
import com.AllProTraining.project.Models.ParkingSpot;
import com.AllProTraining.project.Models.ParkingTicket;
import com.AllProTraining.project.Models.Payment;
import org.apache.coyote.BadRequestException;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface ParkingService {

    public List<ParkingLotSummaryResponse> getAllParkingLots();

    TicketResponse parkEntry(ParkingVehicleEntry request) throws BadRequestException;

    public List<ParkingSpot> getAllAvailableSpots(Long lotId, SpotType spotType);

    ParkingLotSummaryResponse getParkingLotById(Long lotId);

    TicketResponse parkExit(ParkingVehicleExit request) throws BadRequestException;

    TicketResponse getTicketByTicketNumber(String ticketNumber);

    List<TicketResponse> getAllActiveTickets();
}
