-- Create the database

DROP DATABASE IF EXISTS public_transit_db;

CREATE DATABASE IF NOT EXISTS public_transit_db;
 
-- Use the database

USE public_transit_db;
 
-- Create users table

CREATE TABLE IF NOT EXISTS user (

    id INT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(255) NOT NULL,

    email VARCHAR(255) UNIQUE NOT NULL,

    password VARCHAR(255) NOT NULL,

    role ENUM('Manager', 'Operator') NOT NULL

);
 
-- Create vehicles table

CREATE TABLE IF NOT EXISTS vehicles (

    vehicleId INT AUTO_INCREMENT PRIMARY KEY,

    vehicleType ENUM('Diesel Bus', 'Electric Light Rail', 'Diesel-Electric Train') NOT NULL,

    vehicleNumber VARCHAR(20) UNIQUE NOT NULL,

    fuelType VARCHAR(20) NOT NULL,

    maxPassengers INT NOT NULL,

    assignedRoute VARCHAR(50),

    lastMaintenanceDate DATE,

    nextMaintenanceDate DATE,

    manager_id INT,

    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT chk_fuel_type CHECK (

        (vehicleType = 'Diesel Bus' AND fuelType IN ('Diesel', 'CNG')) OR

        (vehicleType = 'Electric Light Rail' AND fuelType = 'Electric') OR

        (vehicleType = 'Diesel-Electric Train' AND fuelType = 'Diesel-Electric')

    ),

    CONSTRAINT chk_passengers CHECK (

        (vehicleType = 'Diesel Bus' AND maxPassengers BETWEEN 10 AND 80) OR

        (vehicleType = 'Electric Light Rail' AND maxPassengers BETWEEN 50 AND 200) OR

        (vehicleType = 'Diesel-Electric Train' AND maxPassengers BETWEEN 100 AND 500)

    ),

    CONSTRAINT fk_manager FOREIGN KEY (manager_id) REFERENCES user(id)

);
 
-- Create break logs table (includes operator_id)

CREATE TABLE IF NOT EXISTS break_logs (

    log_id INT AUTO_INCREMENT PRIMARY KEY,

    operator_id INT NOT NULL,

    vehicle_id INT NOT NULL,

    status ENUM('Check-In', 'Check-Out', 'Out-of-Service') NOT NULL,

    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (operator_id) REFERENCES user(id),

    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicleId)

);
 
-- Create maintenance logs table (with is_scheduled flag)

CREATE TABLE IF NOT EXISTS maintenance_logs (

    log_id INT AUTO_INCREMENT PRIMARY KEY,

    vehicle_id INT NOT NULL,

    component VARCHAR(100),

    hours_used INT,

    threshold INT,

    is_scheduled BOOLEAN DEFAULT FALSE,

    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicleId)

);
 
-- Create GPS log table

CREATE TABLE IF NOT EXISTS gps_data (

    tracking_id INT AUTO_INCREMENT PRIMARY KEY,

    vehicle_id INT NOT NULL,

    location VARCHAR(255),

    timestamp DATETIME,

    status ENUM('Arrival', 'Departure'),

    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicleId)

);
 
-- Sample users

INSERT INTO user (name, email, password, role) 

VALUES 

("Ambika", "ambika@example.com", "ambika", "Manager"),

("Operator", "operator@example.com", "1234", "Operator");
 
-- Sample vehicles (linked to manager ID = 1)

INSERT INTO vehicles (

    vehicleType, vehicleNumber, fuelType, maxPassengers, assignedRoute, 

    lastMaintenanceDate, nextMaintenanceDate, manager_id

) VALUES 

('Diesel Bus', 'DB1234', 'Diesel', 40, 'Route 1', '2024-12-01', '2025-06-01', 1),

('Diesel Bus', 'DB5678', 'CNG', 50, 'Route 2', '2025-01-10', '2025-07-10', 1),

('Electric Light Rail', 'ELR9012', 'Electric', 150, 'Route 3', '2025-02-15', '2025-08-15', 1),

('Diesel-Electric Train', 'DET3456', 'Diesel-Electric', 300, 'Route 4', '2025-03-01', '2025-09-01', 1);
 
-- Sample GPS logs

INSERT INTO gps_data (vehicle_id, location, timestamp, status) VALUES

(1, 'Ottawa Station', '2025-04-06 08:00:00', 'Departure'),

(1, 'Ottawa Station', '2025-04-06 08:20:00', 'Arrival'),

(2, 'Kanata Terminal', '2025-04-06 09:00:00', 'Departure'),

(2, 'Kanata Terminal', '2025-04-06 09:25:00', 'Arrival'),

(3, 'Bayshore', '2025-04-06 10:00:00', 'Departure'),

(3, 'Bayshore', '2025-04-06 10:40:00', 'Arrival'),

(4, 'Union Station', '2025-04-06 11:00:00', 'Departure'),

(4, 'Union Station', '2025-04-06 12:00:00', 'Arrival');
 
-- Sample break logs (with operator_id = 2)

INSERT INTO break_logs (operator_id, vehicle_id, status, timestamp) VALUES

(2, 1, 'Check-In', '2025-04-06 08:30:00'),

(2, 1, 'Out-of-Service', '2025-04-06 14:00:00'),

(2, 2, 'Check-In', '2025-04-06 09:40:00'),

(2, 3, 'Out-of-Service', '2025-04-06 11:00:00'),

(2, 4, 'Check-In', '2025-04-06 12:30:00'),

(2, 4, 'Out-of-Service', '2025-04-06 18:00:00');
 
-- Sample maintenance logs (with alerts)

INSERT INTO maintenance_logs (vehicle_id, component, hours_used, threshold) VALUES

(1, 'Brakes', 120, 100),

(2, 'Axle Bearings', 80, 90),

(3, 'Pantograph', 110, 100),

(4, 'Engine', 130, 120);

 