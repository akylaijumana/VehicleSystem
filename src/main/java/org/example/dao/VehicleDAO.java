package org.example.dao;
import org.example.model.Vehicle;
import org.example.database.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {
    // Create a new vehicle in the database
    public boolean addVehicle(Vehicle vehicle) throws SQLException {
        String query = "INSERT INTO vehicles (make, model, license_plate, price_per_day) VALUES (?, ?, ?, ?)";

        // Try-with-resources to ensure the connection and statement are closed automatically
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the parameters for the PreparedStatement
            stmt.setString(1, vehicle.getMake());
            stmt.setString(2, vehicle.getModel());
            stmt.setString(3, vehicle.getLicensePlate());
            stmt.setDouble(4, vehicle.getPricePerDay());

            // Execute the update and return true if the insert is successful
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace for debugging
            return false;
        }
    }

    // Update an existing vehicle in the database
    public boolean updateVehicle(Vehicle vehicle) throws SQLException {
        String query = "UPDATE vehicles SET make = ?, model = ?, license_plate = ?, price_per_day = ? WHERE id = ?";

        // Try-with-resources to ensure the connection and statement are closed automatically
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the parameters for the PreparedStatement
            stmt.setString(1, vehicle.getMake());
            stmt.setString(2, vehicle.getModel());
            stmt.setString(3, vehicle.getLicensePlate());
            stmt.setDouble(4, vehicle.getPricePerDay());
            stmt.setInt(5, vehicle.getId());

            // Execute the update and return true if successful
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace for debugging
            return false;
        }
    }

    // Delete a vehicle from the database by its ID
    public boolean deleteVehicle(int vehicleId) throws SQLException {
        String query = "DELETE FROM vehicles WHERE id = ?";

        // Try-with-resources to ensure the connection and statement are closed automatically
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the parameter for the PreparedStatement
            stmt.setInt(1, vehicleId);

            // Execute the delete operation and return true if successful
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace for debugging
            return false;
        }
    }

    // Fetch all vehicles from the database
    public List<Vehicle> getAllVehicles() throws SQLException {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles"; // SQL query to get all vehicles

        // Try-with-resources to ensure the connection, statement, and result set are closed automatically
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Iterate over the result set and create Vehicle objects
            while (rs.next()) {
                int id = rs.getInt("id");
                String make = rs.getString("make");
                String model = rs.getString("model");
                String licensePlate = rs.getString("license_plate");
                double pricePerDay = rs.getDouble("price_per_day");

                // Add Vehicle object to the list
                vehicles.add(new Vehicle(id, make, model, licensePlate, pricePerDay));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace for debugging
        }

        return vehicles;
    }

    // Fetch a single vehicle by its ID
    public Vehicle getVehicleById(int id) throws SQLException {
        String query = "SELECT * FROM vehicles WHERE id = ?";
        Vehicle vehicle = null;

        // Try-with-resources to ensure the connection, statement, and result set are closed automatically
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the parameter for the PreparedStatement
            stmt.setInt(1, id);

            // Execute the query and retrieve the result
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String make = rs.getString("make");
                    String model = rs.getString("model");
                    String licensePlate = rs.getString("license_plate");
                    double pricePerDay = rs.getDouble("price_per_day");

                    vehicle = new Vehicle(id, make, model, licensePlate, pricePerDay);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace for debugging
        }

        return vehicle;
    }
}
