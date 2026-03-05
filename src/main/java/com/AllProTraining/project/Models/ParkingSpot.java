package com.AllProTraining.project.Models;


import com.AllProTraining.project.DTO.SpotType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "parking_spot",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_lot_spot",
                        columnNames = {"parking_lot_id", "spot_number"}
                )
        },
        indexes = {
                @Index(
                        name = "idx_spot_available",
                        columnList = "is_available, spot_type"
                )
        }
)

public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign Key Relationship
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            foreignKey = @ForeignKey(name = "fk_spot_lot")
    )
    private ParkingLot parkingLot;

    @Column(name = "spot_number", nullable = false, length = 20)
    private String spotNumber;

    @Column(name = "floor_number", nullable = false)
    private Integer floorNumber = 1;

    @Enumerated(EnumType.STRING)
    @Column(name = "spot_type", nullable = false, length = 20)
    private SpotType spotType = SpotType.REGULAR;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable = true;

    @Column(name = "hourly_rate", nullable = false, precision = 6, scale = 2)
    private BigDecimal hourlyRate = new BigDecimal("50.00");

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, insertable = false)
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public String getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(String spotNumber) {
        this.spotNumber = spotNumber;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public void setSpotType(SpotType spotType) {
        this.spotType = spotType;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


    public ParkingSpot() {
    }

    public ParkingSpot(Long id, ParkingLot parkingLot, String spotNumber, Integer floorNumber, SpotType spotType, Boolean isAvailable, BigDecimal hourlyRate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.parkingLot = parkingLot;
        this.spotNumber = spotNumber;
        this.floorNumber = floorNumber;
        this.spotType = spotType;
        this.isAvailable = isAvailable;
        this.hourlyRate = hourlyRate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "id=" + id +
                ", parkingLot=" + parkingLot +
                ", spotNumber='" + spotNumber + '\'' +
                ", floorNumber=" + floorNumber +
                ", spotType=" + spotType +
                ", isAvailable=" + isAvailable +
                ", hourlyRate=" + hourlyRate +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
