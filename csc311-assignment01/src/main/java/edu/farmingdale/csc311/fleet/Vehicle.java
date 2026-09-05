package edu.farmingdale.csc311.fleet;

/**
 * Base class for everything the motor pool owns. Abstract on purpose:
 * the fleet holds cars and trucks, never a plain "vehicle".
 *
 * @author YOUR NAME HERE
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

        this.year = 0;
        this.color = null;
        this.wheels = 0;
        this.engineSize = engineSize;
        this.fuelType = fuelType;
        this.fuelCapacity = 0.0;

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

    /* ------------------------------------------------------------------
     * TODO-04     commit: TODO-04: implement honk methods from Honkable
     *
     * Vehicle says "implements Honkable" but supplies no horn code yet.
     *
     *      honk()          print hornSound() on one line
     *      honk(int)       print hornSound() that many times, one per line.
     *                      Throw IllegalArgumentException when times < 1.
     *
     * Do not implement hornSound() here. Car and Truck each answer it,
     * and honk() calls whichever one the object actually is.
     * ------------------------------------------------------------------ */

    @Override
    public void honk() {
        throw new UnsupportedOperationException("TODO-04");
    }

    @Override
    public void honk(int times) {
        throw new UnsupportedOperationException("TODO-04");
    }

    /** Subclasses answer these two. Do not write bodies here. */
    public abstract String category();

    public abstract double rangeInMiles();

    /* ------------------------------------------------------------------
     * TODO-05     commit: TODO-05: add toString, equals and hashCode
     *
     * toString() returns exactly this shape, built with String.format:
     *
     *   2023 Honda Accord [VIN=1HGCM82633A004352] color=Blue, wheels=4,
     *   engine=2.0L, fuel=Gasoline, capacity=15.8 gallons
     *
     * (one line, no period at the end). When fuelType.hasEngine() is false
     * the engine part reads engine=n/a instead of a number. Use getLabel()
     * for the fuel and getUnit() after the capacity.
     *
     * Two vehicles are equal when their VINs match. Follow the usual steps:
     * same object, then instanceof, then compare the VIN strings.
     * Base hashCode on the VIN so it agrees with equals.
     * ------------------------------------------------------------------ */

    @Override
    public String toString() {
        throw new UnsupportedOperationException("TODO-05");
    }

    @Override
    public boolean equals(Object other) {
        throw new UnsupportedOperationException("TODO-05");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("TODO-05");
    }
}
