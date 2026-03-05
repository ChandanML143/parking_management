package com.AllProTraining.project.DTO;

import lombok.*;


public class VehicleRequest {

    @NonNull
    private String licensePlate;

    @NonNull
    private VehicleType vehicleType;

    @NonNull
    private String ownerName;

    private String ownerPhone;

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public VehicleRequest() {
    }

    public VehicleRequest(@NonNull String licensePlate, @NonNull VehicleType vehicleType, @NonNull String ownerName, String ownerPhone) {
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
        this.ownerName = ownerName;
        this.ownerPhone = ownerPhone;
    }
}
