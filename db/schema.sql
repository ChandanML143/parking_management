-- ============================================================
-- Parking Management System - Database Schema
-- MySQL 8.x compatible
-- ============================================================

CREATE DATABASE IF NOT EXISTS parking_management;
USE parking_management;

-- ============================================================
-- 1. PARKING LOT
-- ============================================================
CREATE TABLE parking_lot (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(100)    NOT NULL,
    address         VARCHAR(255)    NOT NULL,
    total_floors    INT             NOT NULL DEFAULT 1,
    created_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================================
-- 2. PARKING SPOT
-- ============================================================
CREATE TABLE parking_spot (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    parking_lot_id  BIGINT          NOT NULL,
    spot_number     VARCHAR(20)     NOT NULL,
    floor_number    INT             NOT NULL DEFAULT 1,
    spot_type       ENUM('COMPACT','REGULAR','LARGE','HANDICAPPED','EV_CHARGING')
                                    NOT NULL DEFAULT 'REGULAR',
    is_available    BOOLEAN         NOT NULL DEFAULT TRUE,
    hourly_rate     DECIMAL(6,2)    NOT NULL DEFAULT 50.00,
    created_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_spot_lot FOREIGN KEY (parking_lot_id) REFERENCES parking_lot(id)
        ON DELETE CASCADE,
    UNIQUE KEY uk_lot_spot (parking_lot_id, spot_number)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX idx_spot_available ON parking_spot(is_available, spot_type);

-- ============================================================
-- 3. VEHICLE
-- ============================================================
CREATE TABLE vehicle (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    license_plate   VARCHAR(20)     NOT NULL UNIQUE,
    vehicle_type    ENUM('MOTORCYCLE','CAR','SUV','TRUCK','BUS')
                                    NOT NULL DEFAULT 'CAR',
    owner_name      VARCHAR(100)    NOT NULL,
    owner_phone     VARCHAR(20),
    created_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================================
-- 4. PARKING TICKET (core transaction table)
-- ============================================================
CREATE TABLE parking_ticket (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    ticket_number   VARCHAR(30)     NOT NULL UNIQUE,
    vehicle_id      BIGINT          NOT NULL,
    parking_spot_id BIGINT          NOT NULL,
    entry_time      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    exit_time       TIMESTAMP       NULL,
    total_amount    DECIMAL(10,2)   NULL,
    status          ENUM('ACTIVE','COMPLETED','CANCELLED')
                                    NOT NULL DEFAULT 'ACTIVE',
    created_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_ticket_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicle(id),
    CONSTRAINT fk_ticket_spot    FOREIGN KEY (parking_spot_id) REFERENCES parking_spot(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX idx_ticket_status ON parking_ticket(status);
CREATE INDEX idx_ticket_entry  ON parking_ticket(entry_time);

-- ============================================================
-- 5. PAYMENT
-- ============================================================
CREATE TABLE payment (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    ticket_id       BIGINT          NOT NULL,
    amount          DECIMAL(10,2)   NOT NULL,
    payment_method  ENUM('CASH','CREDIT_CARD','DEBIT_CARD','UPI','WALLET')
                                    NOT NULL DEFAULT 'CASH',
    payment_status  ENUM('PENDING','SUCCESS','FAILED','REFUNDED')
                                    NOT NULL DEFAULT 'PENDING',
    paid_at         TIMESTAMP       NULL,
    created_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_payment_ticket FOREIGN KEY (ticket_id) REFERENCES parking_ticket(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- ============================================================
-- SEED DATA
-- ============================================================

-- Parking Lots
INSERT INTO parking_lot (name, address, total_floors) VALUES
('City Center Parking', '123 Main Street, Bareilly', 3),
('Mall Road Parking',   '456 Mall Road, Bareilly',   2);

-- Spots for City Center (lot 1)
INSERT INTO parking_spot (parking_lot_id, spot_number, floor_number, spot_type, hourly_rate) VALUES
(1, 'A-001', 1, 'COMPACT',       30.00),
(1, 'A-002', 1, 'REGULAR',       50.00),
(1, 'A-003', 1, 'REGULAR',       50.00),
(1, 'A-004', 1, 'LARGE',         80.00),
(1, 'A-005', 1, 'HANDICAPPED',   20.00),
(1, 'B-001', 2, 'REGULAR',       50.00),
(1, 'B-002', 2, 'REGULAR',       50.00),
(1, 'B-003', 2, 'EV_CHARGING',  100.00),
(1, 'C-001', 3, 'COMPACT',       30.00),
(1, 'C-002', 3, 'REGULAR',       50.00);

-- Spots for Mall Road (lot 2)
INSERT INTO parking_spot (parking_lot_id, spot_number, floor_number, spot_type, hourly_rate) VALUES
(2, 'G-001', 1, 'REGULAR',       40.00),
(2, 'G-002', 1, 'REGULAR',       40.00),
(2, 'G-003', 1, 'LARGE',         70.00),
(2, 'G-004', 1, 'HANDICAPPED',   15.00),
(2, 'F-001', 2, 'COMPACT',       25.00),
(2, 'F-002', 2, 'REGULAR',       40.00);

-- Sample Vehicles
INSERT INTO vehicle (license_plate, vehicle_type, owner_name, owner_phone) VALUES
('UP25AB1234', 'CAR',        'Rahul Sharma',  '9876543210'),
('UP25CD5678', 'SUV',        'Priya Singh',   '9876543211'),
('UP25EF9012', 'MOTORCYCLE', 'Amit Verma',    '9876543212');
