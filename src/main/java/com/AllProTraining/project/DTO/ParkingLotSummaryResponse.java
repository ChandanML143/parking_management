package com.AllProTraining.project.DTO;

import lombok.*;

//@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class ParkingLotSummaryResponse {
    private Long parkingLotId;
    private String name;
    private String address;
    private int totalFloors;
    private long totalSpots;
    private long availableSpots;
    private long occupiedSpots;

    public Long getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Long parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalFloors() {
        return totalFloors;
    }

    public void setTotalFloors(int totalFloors) {
        this.totalFloors = totalFloors;
    }

    public long getTotalSpots() {
        return totalSpots;
    }

    public void setTotalSpots(long totalSpots) {
        this.totalSpots = totalSpots;
    }

    public long getAvailableSpots() {
        return availableSpots;
    }

    public void setAvailableSpots(long availableSpots) {
        this.availableSpots = availableSpots;
    }

    public long getOccupiedSpots() {
        return occupiedSpots;
    }

    public void setOccupiedSpots(long occupiedSpots) {
        this.occupiedSpots = occupiedSpots;
    }

    public ParkingLotSummaryResponse() {
    }

    public ParkingLotSummaryResponse(Long parkingLotId, String name, String address, int totalFloors, long totalSpots, long availableSpots, long occupiedSpots) {
        this.parkingLotId = parkingLotId;
        this.name = name;
        this.address = address;
        this.totalFloors = totalFloors;
        this.totalSpots = totalSpots;
        this.availableSpots = availableSpots;
        this.occupiedSpots = occupiedSpots;
    }
}
