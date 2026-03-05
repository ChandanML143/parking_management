package com.AllProTraining.project.DTO;

import lombok.NonNull;

public class ParkingVehicleEntry {

        @NonNull
        private String licensePlate;
        @NonNull
        private Long parkingLotId;

        private Long parkingSpotId;

        private SpotType preferredSpotType;

    public ParkingVehicleEntry() {
    }

    public ParkingVehicleEntry(@NonNull String licensePlate, @NonNull Long parkingLotId, Long parkingSpotId, SpotType preferredSpotType) {
        this.licensePlate = licensePlate;
        this.parkingLotId = parkingLotId;
        this.parkingSpotId = parkingSpotId;
        this.preferredSpotType = preferredSpotType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Long getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Long parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public Long getParkingSpotId() {
        return parkingSpotId;
    }

    public void setParkingSpotId(Long parkingSpotId) {
        this.parkingSpotId = parkingSpotId;
    }

    public SpotType getPreferredSpotType() {
        return preferredSpotType;
    }

    public void setPreferredSpotType(SpotType preferredSpotType) {
        this.preferredSpotType = preferredSpotType;
    }
}
