package edu.farmingdale.csc311.fleet;

/**
 * The fuels a fleet vehicle can run on.
 *
 * @author YOUR NAME HERE
 */
public enum FuelType {

    GASOLINE("Gasoline", "gallons", 28.0),
    DIESEL("Diesel", "gallons", 34.0),
    ELECTRIC("Electric", "kWh", 3.2),
    HYBRID("Hybrid", "gallons", 48.0);

    private final String label;
    private final String unit;
    private final double milesPerUnit;

    FuelType(String label, String unit, double milesPerUnit) {
        this.label = label;
        this.unit = unit;
        this.milesPerUnit = milesPerUnit;
    }

    public String getLabel() {
        return label;
    }

    public String getUnit() {
        return unit;
    }

    public double getMilesPerUnit() {
        return milesPerUnit;
    }

    /** False for ELECTRIC, true for the rest. */
    public boolean hasEngine() {
        return this != ELECTRIC;
    }

    /**
     * Finds a constant by its label, ignoring case and outer spaces.
     * Throws IllegalArgumentException if the text matches nothing.
     */
    public static FuelType fromLabel(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Unknown fuel type: null");
        }

        String cleaned = text.trim();

        for (FuelType fuelType : values()) {
            if (fuelType.label.equalsIgnoreCase(cleaned)) {
                return fuelType;
            }
        }

        throw new IllegalArgumentException(
                "Unknown fuel type: " + text
        );
    }
}