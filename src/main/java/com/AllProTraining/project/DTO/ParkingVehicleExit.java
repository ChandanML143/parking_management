package com.AllProTraining.project.DTO;

import com.AllProTraining.project.Models.Payment;

public class ParkingVehicleExit {
    private String ticketNumber;
    private Payment paymentMethod;

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Payment getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Payment paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public ParkingVehicleExit() {
    }

    public ParkingVehicleExit(String ticketNumber, Payment paymentMethod) {
        this.ticketNumber = ticketNumber;
        this.paymentMethod = paymentMethod;
    }
}
