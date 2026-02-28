package com.AllProTraining.project.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "parking_lot")
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(name = "total_floors", nullable = false)
    private Integer totalFloors = 1;

    // Optional: Bidirectional mapping (recommended)
    @OneToMany(
            mappedBy = "parkingLot",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ParkingSpot> parkingSpots;

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, insertable = false)
    private LocalDateTime updatedAt;
}
