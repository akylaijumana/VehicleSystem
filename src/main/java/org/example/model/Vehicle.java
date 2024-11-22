package org.example.model;

public class Vehicle {
    private int id;
    private String make;
    private String model;
    private String licensePlate;
    private double pricePerDay;

    // Constructor
    public Vehicle(int id, String make, String model, String licensePlate, double pricePerDay) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.licensePlate = licensePlate;
        this.pricePerDay = pricePerDay;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}
