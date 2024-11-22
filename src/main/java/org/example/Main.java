package org.example;

import org.example.dao.VehicleDAO;
import org.example.model.Vehicle;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create an instance of VehicleDAO
        VehicleDAO vehicleDAO = new VehicleDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display menu options
            System.out.println("\nVehicle Management System");
            System.out.println("1. Add a new vehicle");
            System.out.println("2. Update a vehicle");
            System.out.println("3. Delete a vehicle");
            System.out.println("4. View all vehicles");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character after the integer input

            switch (choice) {
                case 1:
                    // Add a new vehicle
                    System.out.println("\nEnter vehicle details:");

                    System.out.print("Make: ");
                    String make = scanner.nextLine();

                    System.out.print("Model: ");
                    String model = scanner.nextLine();

                    System.out.print("License Plate: ");
                    String licensePlate = scanner.nextLine();

                    System.out.print("Price per Day: ");
                    double pricePerDay = scanner.nextDouble();
                    scanner.nextLine();  // Consume the newline character

                    Vehicle newVehicle = new Vehicle(0, make, model, licensePlate, pricePerDay);
                    try {
                        boolean isAdded = vehicleDAO.addVehicle(newVehicle);
                        if (isAdded) {
                            System.out.println("Vehicle added successfully.");
                        } else {
                            System.out.println("Failed to add the vehicle.");
                        }
                    } catch (SQLException e) {
                        System.out.println("Error while adding vehicle: " + e.getMessage());
                    }
                    break;

                case 2:
                    // Update a vehicle
                    System.out.print("\nEnter the vehicle ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline character

                    System.out.println("\nEnter updated vehicle details:");

                    System.out.print("Make: ");
                    String updatedMake = scanner.nextLine();

                    System.out.print("Model: ");
                    String updatedModel = scanner.nextLine();

                    System.out.print("License Plate: ");
                    String updatedLicensePlate = scanner.nextLine();

                    System.out.print("Price per Day: ");
                    double updatedPricePerDay = scanner.nextDouble();
                    scanner.nextLine();  // Consume the newline character

                    Vehicle vehicleToUpdate = new Vehicle(updateId, updatedMake, updatedModel, updatedLicensePlate, updatedPricePerDay);
                    try {
                        boolean isUpdated = vehicleDAO.updateVehicle(vehicleToUpdate);
                        if (isUpdated) {
                            System.out.println("Vehicle updated successfully.");
                        } else {
                            System.out.println("Failed to update the vehicle.");
                        }
                    } catch (SQLException e) {
                        System.out.println("Error while updating vehicle: " + e.getMessage());
                    }
                    break;

                case 3:
                    // Delete a vehicle
                    System.out.print("\nEnter the vehicle ID to delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline character

                    try {
                        boolean isDeleted = vehicleDAO.deleteVehicle(deleteId);
                        if (isDeleted) {
                            System.out.println("Vehicle deleted successfully.");
                        } else {
                            System.out.println("Failed to delete the vehicle.");
                        }
                    } catch (SQLException e) {
                        System.out.println("Error while deleting vehicle: " + e.getMessage());
                    }
                    break;

                case 4:
                    // View all vehicles
                    try {
                        List<Vehicle> vehicles = vehicleDAO.getAllVehicles();
                        if (vehicles.isEmpty()) {
                            System.out.println("\nNo vehicles found.");
                        } else {
                            System.out.println("\nVehicles list:");
                            for (Vehicle vehicle : vehicles) {
                                System.out.println(vehicle.getId() + " - " + vehicle.getMake() + " " + vehicle.getModel());
                            }
                        }
                    } catch (SQLException e) {
                        System.out.println("Error while fetching vehicles: " + e.getMessage());
                    }
                    break;

                case 5:
                    // Exit
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
