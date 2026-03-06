package com.AllProTraining.project.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TicketResponse {
    private Long id;
    private String ticketNumber;
    private String vehicleType;
    private String spotNumber;

    private String licensePlate;

    private String parkingLotName;
    private int floorNumber;
    private BigDecimal hourlyRate;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private BigDecimal totalAmount;
    private TicketStatus ticketStatus;
    private String paymentMethod;
    private String paymentStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(String spotNumber) {
        this.spotNumber = spotNumber;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }


    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }

    public String getLicensePlate() { return licensePlate; }


    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public TicketResponse() {
    }

    public TicketResponse(Long id, String ticketNumber, String vehicleType, String spotNumber, String licensePlate, String parkingLotName, int floorNumber, BigDecimal hourlyRate, LocalDateTime entryTime, LocalDateTime exitTime, BigDecimal totalAmount, TicketStatus ticketStatus, String paymentMethod, String paymentStatus) {
        this.id = id;
        this.ticketNumber = ticketNumber;
        this.vehicleType = vehicleType;
        this.spotNumber = spotNumber;

        this.licensePlate = licensePlate;

        this.parkingLotName = parkingLotName;
        this.floorNumber = floorNumber;
        this.hourlyRate = hourlyRate;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.totalAmount = totalAmount;
        this.ticketStatus = ticketStatus;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    public TicketResponse(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public String toString() {
        return "TicketResponse{" +
                "id=" + id +
                ", ticketNumber='" + ticketNumber + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", spotNumber='" + spotNumber + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", parkingLotName='" + parkingLotName + '\'' +
                ", floorNumber=" + floorNumber +
                ", hourlyRate=" + hourlyRate +
                ", entryTime=" + entryTime +
                ", exitTime=" + exitTime +
                ", totalAmount=" + totalAmount +
                ", ticketStatus=" + ticketStatus +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }
}
