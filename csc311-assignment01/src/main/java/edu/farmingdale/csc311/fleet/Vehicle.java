package edu.farmingdale.csc311.fleet;

/**
 * Base class for everything the motor pool owns. Abstract on purpose:
 * the fleet holds cars and trucks, never a plain "vehicle".
 *
 * @author Elan Yankovsky
 */
public abstract class Vehicle implements Honkable {
    private final String vin;
    private final String make;
    private final String model;
    private int year;
    private String color;
    private int wheels;
    private final double engineSize;
    private final FuelType fuelType;
    private double fuelCapacity;

    protected Vehicle(String vin, String make, String model, int year, String color,
                      int wheels, double engineSize, FuelType fuelType, double fuelCapacity) {

        if (vin == null || vin.trim().length() != 17) {
            throw new IllegalArgumentException("Invalid value vin: " + vin);
        }

        make = requireText("make", make);
        model = requireText("model", model);
        color = requireText("color", color);

        if (year < 1900 || year > 2100) {
            throw new IllegalArgumentException("Invalid value year: " + year);
        }

        if (wheels < 2 || wheels > 18) {
            throw new IllegalArgumentException("Invalid value wheels: " + wheels);
        }

        if (fuelType == null) {
            throw new IllegalArgumentException("Invalid value fuelType: " + fuelType);
        }

        if (fuelType.hasEngine()) {
            if (engineSize <= 0.0 || engineSize > 8.5) {
                throw new IllegalArgumentException(
                        "Invalid value engineSize: " + engineSize
                );
            }
        } else if (engineSize != 0.0) {
            throw new IllegalArgumentException(
                    "Invalid value engineSize: " + engineSize
            );
        }

        if (fuelCapacity <= 0.0) {
            throw new IllegalArgumentException(
                    "Invalid value fuelCapacity: " + fuelCapacity
            );
        }

        this.vin = vin.trim().toUpperCase();
        this.make = make;
        this.model = model;
        this.engineSize = engineSize;
        this.fuelType = fuelType;

        setYear(year);
        setColor(color);
        setWheels(wheels);
        setFuelCapacity(fuelCapacity);
    }

    private static String requireText(String field, String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Invalid value " + field + ": " + value
            );
        }

        return value.trim();
    }


    public String getVin() {
        return vin;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year < 1900 || year > 2100) {
            throw new IllegalArgumentException("Invalid value year: " + year);
        }

        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid value color: " + color);
        }

        this.color = color.trim();
    }

    public int getWheels() {
        return wheels;
    }

    public void setWheels(int wheels) {
        if (wheels < 2 || wheels > 18) {
            throw new IllegalArgumentException("Invalid value wheels: " + wheels);
        }

        this.wheels = wheels;
    }

    public double getEngineSize() {
        return engineSize;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public double getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(double fuelCapacity) {
        if (fuelCapacity <= 0.0) {
            throw new IllegalArgumentException(
                    "Invalid value fuelCapacity: " + fuelCapacity
            );
        }

        this.fuelCapacity = fuelCapacity;
    }

    @Override
    public void honk() {
        System.out.println(hornSound());
    }

    @Override
    public void honk(int times) {
        if (times < 1) {
            throw new IllegalArgumentException("Invalid value times: " + times);
        }

        for (int i = 0; i < times; i++) {
            System.out.println(hornSound());
        }
    }

    public abstract String hornSound();

    public abstract String category();

    public abstract double rangeInMiles();

    @Override
    public String toString() {
        String engine = fuelType.hasEngine()
                ? String.format("%.1fL", engineSize)
                : "n/a";

        return String.format(
                "%d %s %s [VIN=%s] color=%s, wheels=%d, engine=%s, fuel=%s, capacity=%.1f %s",
                year,
                make,
                model,
                vin,
                color,
                wheels,
                engine,
                fuelType.getLabel(),
                fuelCapacity,
                fuelType.getUnit()
        );
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Vehicle)) {
            return false;
        }

        Vehicle vehicle = (Vehicle) other;

        return vin.equals(vehicle.vin);
    }

    @Override
    public int hashCode() {
        return vin.hashCode();
    }
}