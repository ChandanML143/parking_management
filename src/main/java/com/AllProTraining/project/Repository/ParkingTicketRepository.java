package com.AllProTraining.project.Repository;

import com.AllProTraining.project.DTO.TicketStatus;
import com.AllProTraining.project.Models.ParkingTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long> {

    Optional<ParkingTicket> findByVehicleLicensePlateAndStatus(String licensePlate, TicketStatus status);
}
