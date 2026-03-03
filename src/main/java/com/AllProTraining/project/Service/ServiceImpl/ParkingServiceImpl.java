package com.AllProTraining.project.Service.ServiceImpl;

import com.AllProTraining.project.DTO.ParkingLotSummaryResponse;
import com.AllProTraining.project.Models.ParkingLot;
import com.AllProTraining.project.Repository.ParkingLotRepository;
import com.AllProTraining.project.Repository.ParkingSpotRepository;
import com.AllProTraining.project.Service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private ParkingLotRepository lotRepository;

    @Autowired
    private ParkingSpotRepository spotRepository;

    @Override
    public List<ParkingLotSummaryResponse> getAllParkingLots() {
        System.out.println("Method Called");
//        System.out.println(lotRepository.findAll().get(0));
//        System.out.println(lotRepository.findById(1L));
//        return lotRepository.findAll().stream().map(this::toLotSummary).toList();
        return null;
    }

//    private ParkingLotSummaryResponse toLotSummary(ParkingLot lot) {
//        long total = spotRepository.countByParkingLotId(lot.getId());
//        long available = spotRepository.countByParkingLotIdAndIsAvailableTrue(lot.getId());
//        return ParkingLotSummaryResponse.builder().parkingLotId(lot.getId())
//                .name(lot.getName())
//                .address(lot.getAddress())
//                .totalFloors(lot.getTotalFloors())
//                .totalSpots(total)
//                .availableSpots(available)
//                .occupiedSpots(total-available)
//                .build();
//    }
}
