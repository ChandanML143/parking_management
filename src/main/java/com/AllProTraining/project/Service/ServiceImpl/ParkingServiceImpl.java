package com.AllProTraining.project.Service.ServiceImpl;

import com.AllProTraining.project.DTO.*;
import com.AllProTraining.project.Models.*;
import com.AllProTraining.project.Repository.*;
import com.AllProTraining.project.Service.ParkingService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private ParkingLotRepository lotRepository;

    @Autowired
    private ParkingSpotRepository spotRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ParkingTicketRepository parkingTicketRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<ParkingLotSummaryResponse> getAllParkingLots() {
        return lotRepository.findAll().stream().map(this::toLotSummary).toList();
    }

    private ParkingLotSummaryResponse toLotSummary(ParkingLot lot) {
        long total = spotRepository.countByParkingLotId(lot.getId());
        long available = spotRepository.countByParkingLotIdAndIsAvailableTrue(lot.getId());
        ParkingLotSummaryResponse response = new ParkingLotSummaryResponse();
                response.setParkingLotId(lot.getId());
                response.setName(lot.getName());
                response.setAddress(lot.getAddress());
                response.setTotalFloors(lot.getTotalFloors());
                response.setTotalSpots(total);
                response.setAvailableSpots(available);
                response.setOccupiedSpots(total-available);
        return response;
    }

    //--------------------Entry Methods -------------------------

    @Override
    @Transactional
    public TicketResponse parkEntry(ParkingVehicleEntry request) throws BadRequestException {
        //Step1. check the vehicle if exists
        Vehicle vehicle = vehicleRepository.findByLicensePlate(request.getLicensePlate().toUpperCase())
                .orElseThrow(() -> new RuntimeException("Vehicle Not Found, Register the vehicle"));

        //Step2. check if the vehicle is already parked
        parkingTicketRepository.findByVehicleLicensePlateAndStatus(vehicle.getLicensePlate(), TicketStatus.ACTIVE)
                .ifPresent(t -> {
                    try {
                        throw new BadRequestException("Vehicle is already Parked with Active TicketNumber: " + t.getTicketNumber());
                    } catch (BadRequestException e) {
                        throw new RuntimeException(e);
                    }
                });

        // Step3. find parking Spot
        ParkingSpot spot;
        if(request.getParkingSpotId() != null) {
            spot = spotRepository.findById(request.getParkingSpotId())
                    .orElseThrow(() -> new RuntimeException("Spot not found"));
            if(!spot.getAvailable()) {
                throw new BadRequestException("Spot already occupied!");
            }
        } else {
            List<ParkingSpot> available = getAllAvailableSpots(request.getParkingLotId(), request.getPreferredSpotType());
            if (available.isEmpty()) {
                throw new BadRequestException("No Spots available for the requested type");
            }
            spot = available.get(0);
        }

        //Step4. Mark the spot
        spot.setAvailable(false);
        spotRepository.save(spot);

        //Step 5. create Ticket
        String ticketNumber = "TKt-" + UUID.randomUUID().toString().substring(2,9).toUpperCase();

        ParkingTicket ticket = new ParkingTicket();
        ticket.setTicketNumber(ticketNumber);
        ticket.setVehicle(vehicle);
        ticket.setParkingSpot(spot);
        ticket.setEntryTime(LocalDateTime.now());
        ticket.setStatus(TicketStatus.ACTIVE);
        parkingTicketRepository.save(ticket);

        return toTicketResponse(ticket, null);
    }

    public List<ParkingSpot> getAllAvailableSpots(Long lotId, SpotType spotType) {
        if(spotType!= null) {
            return spotRepository.findByParkingLotIdAndSpotTypeAndIsAvailableTrue(lotId, spotType);
        }
        return null;
    }

   @Override
   public ParkingLotSummaryResponse getParkingLotById(Long lotId) {

       ParkingLot lot = lotRepository.findById(lotId)
               .orElseThrow(() -> new RuntimeException("Parking Lot not found with id: " + lotId));

       return toLotSummary(lot);
   }

   /* private TicketResponse toTicketResponse(ParkingTicket parkingTicket, Payment payment) {
        TicketResponse response = new TicketResponse();
        response.setId(parkingTicket.getId());
        response.setTicketNumber(parkingTicket.getTicketNumber());
        response.setVehicleType(parkingTicket.getVehicle().getVehicleType().name());
        response.setSpotNumber(parkingTicket.getParkingSpot().getSpotNumber());
        response.setHourlyRate(parkingTicket.getParkingSpot().getHourlyRate());
        response.setEntryTime(parkingTicket.getEntryTime());
        response.setExitTime(parkingTicket.getExitTime());
        response.setTicketStatus(parkingTicket.getStatus());

        return response;
    }*/
    private TicketResponse toTicketResponse(ParkingTicket parkingTicket, Payment payment) {

        TicketResponse response = new TicketResponse();

        response.setId(parkingTicket.getId());
        response.setTicketNumber(parkingTicket.getTicketNumber());
        response.setVehicleType(parkingTicket.getVehicle().getVehicleType().name());
        response.setSpotNumber(parkingTicket.getParkingSpot().getSpotNumber());

        response.setLicensePlate(parkingTicket.getVehicle().getLicensePlate());

        response.setFloorNumber(parkingTicket.getParkingSpot().getFloorNumber());
        response.setHourlyRate(parkingTicket.getParkingSpot().getHourlyRate());
        response.setParkingLotName(parkingTicket.getParkingSpot().getParkingLot().getName());
        response.setEntryTime(parkingTicket.getEntryTime());
        response.setExitTime(parkingTicket.getExitTime());
        response.setTicketStatus(parkingTicket.getStatus());
        if (payment != null) {
            response.setTotalAmount(payment.getAmount());
            response.setPaymentMethod(payment.getPaymentMethod().name());
            response.setPaymentStatus(payment.getPaymentStatus().name());
        }
        System.out.println(response);
        return response;
    }

    //exit vehicle logic

    @Override
    @Transactional
    public TicketResponse parkExit(ParkingVehicleExit request) throws BadRequestException {

        //Check Vehicle and Vehicle Status
        ParkingTicket ticket = parkingTicketRepository.findByTicketNumber(request.getTicketNumber())
                .orElseThrow(() -> new RuntimeException("Ticket not Found!"));
        if(ticket.getStatus() != TicketStatus.ACTIVE) {
            throw new BadRequestException("Vehicle already Exit");
        }

        //calculate the
        LocalDateTime exitTime = LocalDateTime.now();
        Duration duration = Duration.between(ticket.getEntryTime(), exitTime);
        long hours = Math.max(1, (long) Math.ceil(duration.toMinutes() / 60.0));
        BigDecimal totalAmount = ticket.getParkingSpot().getHourlyRate()
                .multiply(BigDecimal.valueOf(hours))
                .setScale(2, RoundingMode.HALF_UP);

        //update ticket
        ticket.setExitTime(exitTime);
        ticket.setTotalAmount(totalAmount);
        ticket.setStatus(TicketStatus.COMPLETED);
        parkingTicketRepository.save(ticket);

        //free the parking spot
        ParkingSpot spot = ticket.getParkingSpot();
        spot.setAvailable(true);
        spotRepository.save(spot);

        //create Payment

        PaymentMethod method = request.getPaymentMethod() != null ? request.getPaymentMethod().getPaymentMethod() : PaymentMethod.CASH;

        Payment payment = new Payment();
        payment.setTicket(ticket);
        payment.setAmount(totalAmount);
        payment.setPaymentMethod(method);
        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        payment.setPaidAt(LocalDateTime.now());

        paymentRepository.save(payment);

        return toTicketResponse(ticket, payment);
    }
}
