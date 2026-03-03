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
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
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
