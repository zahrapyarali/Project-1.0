-- Create the database if it doesn't exist
DROP DATABASE IF EXISTS public_transit_db;

CREATE DATABASE IF NOT EXISTS public_transit_db;

-- Switch to using the newly created database
USE public_transit_db;

-- Create users table to store system users
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('Manager', 'Operator') NOT NULL
);

-- Create vehicles table to store transit vehicle information
CREATE TABLE IF NOT EXISTS vehicles (
    vehicleId INT AUTO_INCREMENT PRIMARY KEY,
    vehicleType ENUM('Diesel Bus', 'Electric Light Rail', 'Diesel-Electric Train') NOT NULL,
    vehicleNumber VARCHAR(20) UNIQUE NOT NULL,
    fuelType VARCHAR(20) NOT NULL,
    maxPassengers INT NOT NULL,
    assignedRoute VARCHAR(50),
    lastMaintenanceDate DATE,
    nextMaintenanceDate DATE,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    -- Constraint to ensure fuel type matches vehicle type    
    CONSTRAINT chk_fuel_type CHECK (
        (vehicleType = 'Diesel Bus' AND fuelType IN ('Diesel', 'CNG')) OR
        (vehicleType = 'Electric Light Rail' AND fuelType = 'Electric') OR
        (vehicleType = 'Diesel-Electric Train' AND fuelType = 'Diesel-Electric')
    ),
    
    -- Constraint to ensure passenger capacity is appropriate for vehicle type
    CONSTRAINT chk_passengers CHECK (
        (vehicleType = 'Diesel Bus' AND maxPassengers BETWEEN 10 AND 80) OR
        (vehicleType = 'Electric Light Rail' AND maxPassengers BETWEEN 50 AND 200) OR
        (vehicleType = 'Diesel-Electric Train' AND maxPassengers BETWEEN 100 AND 500)
    )
);
INSERT INTO vehicles (vehicleType, vehicleNumber, fuelType, maxPassengers, assignedRoute, lastMaintenanceDate, nextMaintenanceDate)
VALUES 
('Diesel Bus', 'DB1234', 'Diesel', 40, 'Route 1', '2024-12-01', '2025-06-01'),
('Electric Light Rail', 'ELR5678', 'Electric', 120, 'Route 2', '2025-01-15', '2025-07-15');

-- Create gps_data table to store GPS logs
CREATE TABLE IF NOT EXISTS gps_data (
    tracking_id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_id INT NOT NULL,
    location VARCHAR(255),
    timestamp DATETIME,
    status ENUM('Arrival', 'Departure'), 
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicleId)
);

--ALTER TABLE gps_data ADD COLUMN status ENUM('Arrival', 'Departure');

