package com.AllProTraining.project.Repository;

import com.AllProTraining.project.DTO.SpotType;
import com.AllProTraining.project.Models.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {

    long countByParkingLotId(Long parkingLotId);
    long countByParkingLotIdAndIsAvailableTrue(Long parkingLotId);

    List<ParkingSpot> findByParkingLotIdAndSpotTypeAndIsAvailableTrue(Long parkingId, SpotType spotType);

}
