package com.AllProTraining.project.Repository;

import com.AllProTraining.project.Models.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {

    long countByParkingLotId(Long parkingLotId);
    long countByParkingLotIdAndIsAvailableTrue(Long parkingLotId);

}
