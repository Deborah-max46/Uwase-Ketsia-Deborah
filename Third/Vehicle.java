package Third;

import java.time.LocalDate;
import java.util.*;

class Vehicle {
    private String vehicleId;
    private String vehicleMake;
    private String vehicleModel;
    private int vehicleYear;
    private String vehicleType;
    private int engineCapacity;

    public Vehicle(String vehicleMake, String vehicleModel, int vehicleYear, String vehicleType, int engineCapacity) {
        this.vehicleId = "VEH-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;
        this.vehicleYear = vehicleYear;
        this.vehicleType = vehicleType;
        this.engineCapacity = engineCapacity;
    }

    // Getters and Setters
    public String getVehicleId() {
        return vehicleId;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public int getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(int vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    // Validate vehicle details
    public boolean validate() {
        if (vehicleMake == null || vehicleMake.trim().isEmpty()) {
            System.out.println("Vehicle make cannot be empty.");
            return false;
        }

        if (vehicleModel == null || vehicleModel.trim().isEmpty()) {
            System.out.println("Vehicle model cannot be empty.");
            return false;
        }

        if (vehicleYear < 1900 || vehicleYear > LocalDate.now().getYear() + 1) {
            System.out.println("Invalid vehicle year.");
            return false;
        }

        if (vehicleType == null || vehicleType.trim().isEmpty()) {
            System.out.println("Vehicle type cannot be empty.");
            return false;
        }

        if (engineCapacity <= 0) {
            System.out.println("Engine capacity must be greater than zero.");
            return false;
        }

        return true;
    }
}


