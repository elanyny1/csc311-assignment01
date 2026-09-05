package edu.farmingdale.csc311.fleet;

/**
 * A passenger car: a Vehicle plus a door count.
 *
 * @author Elan Yankovsky
 */
public class Car extends Vehicle {

    private int doors;

    public Car(String vin, String make, String model, int year, String color,
               int wheels, double engineSize, FuelType fuelType, double fuelCapacity, int doors) {

        super(vin, make, model, year, color, wheels, engineSize, fuelType, fuelCapacity);

        if (doors < 2 || doors > 5) {
            throw new IllegalArgumentException("Invalid value doors: " + doors);
        }

        this.doors = doors;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        if (doors < 2 || doors > 5) {
            throw new IllegalArgumentException("Invalid value doors: " + doors);
        }

        this.doors = doors;
    }

    @Override
    public String category() {
        return "Car";
    }

    @Override
    public double rangeInMiles() {
        return getFuelCapacity() * getFuelType().getMilesPerUnit();
    }

    @Override
    public String hornSound() {
        return "Beep beep!";
    }

    @Override
    public String toString() {
        return String.format(
                "%s -> %s, doors=%d, range=%.1f mi",
                category(),
                super.toString(),
                doors,
                rangeInMiles()
        );
    }
}