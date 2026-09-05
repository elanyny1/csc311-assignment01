package edu.farmingdale.csc311.fleet;

/**
 * A named group of vehicles stored in a plain array.
 * No ArrayList, no HashMap. Arrays and loops only.
 *
 * @author Elan Yankovsky
 */
public class Fleet {

    public static final int MAX_VEHICLES = 25;

    private final String name;
    private final Vehicle[] vehicles;
    private int count;

    public Fleet(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid value name: " + name);
        }

        this.name = name.trim();
        this.vehicles = new Vehicle[MAX_VEHICLES];
        this.count = 0;
    }

    public String getName() {
        return name;
    }

    public boolean contains(Vehicle vehicle) {
        for (int i = 0; i < count; i++) {
            if (vehicles[i].equals(vehicle)) {
                return true;
            }
        }

        return false;
    }

    public boolean add(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Invalid value vehicle: null");
        }

        if (contains(vehicle)) {
            return false;
        }

        if (count >= MAX_VEHICLES) {
            return false;
        }

        vehicles[count] = vehicle;
        count++;

        return true;
    }

    public boolean removeByVin(String vin) {
        if (vin == null || vin.trim().isEmpty()) {
            return false;
        }

        String searchVin = vin.trim();

        for (int i = 0; i < count; i++) {
            if (vehicles[i].getVin().equalsIgnoreCase(searchVin)) {

                // Shift everything after this vehicle one position left
                for (int j = i; j < count - 1; j++) {
                    vehicles[j] = vehicles[j + 1];
                }

                // Clear the now-unused last slot
                vehicles[count - 1] = null;

                count--;

                return true;
            }
        }

        return false;
    }

    public Vehicle findByVin(String vin) {
        if (vin == null || vin.trim().isEmpty()) {
            return null;
        }

        String searchVin = vin.trim();

        for (int i = 0; i < count; i++) {
            if (vehicles[i].getVin().equalsIgnoreCase(searchVin)) {
                return vehicles[i];
            }
        }

        return null;
    }

    public int size() {
        return count;
    }

    public Vehicle[] toArray() {
        Vehicle[] result = new Vehicle[count];

        for (int i = 0; i < count; i++) {
            result[i] = vehicles[i];
        }

        return result;
    }

    public Vehicle[] sortedByYear() {
        Vehicle[] result = toArray();

        // Selection sort
        for (int i = 0; i < result.length - 1; i++) {
            int smallest = i;

            for (int j = i + 1; j < result.length; j++) {
                if (result[j].getYear() < result[smallest].getYear()
                        || (result[j].getYear() == result[smallest].getYear()
                        && result[j].getMake().compareToIgnoreCase(result[smallest].getMake()) < 0)) {
                    smallest = j;
                }
            }

            Vehicle temp = result[i];
            result[i] = result[smallest];
            result[smallest] = temp;
        }

        return result;
    }

    public int countWithFuelType(FuelType fuel) {
        int count = 0;

        for (int i = 0; i < this.count; i++) {
            if (vehicles[i].getFuelType() == fuel) {
                count++;
            }
        }

        return count;
    }

    public double averageEngineSize() {
        double total = 0.0;
        int engineCount = 0;

        for (int i = 0; i < count; i++) {
            if (vehicles[i].getFuelType().hasEngine()) {
                total += vehicles[i].getEngineSize();
                engineCount++;
            }
        }

        if (engineCount == 0) {
            return 0.0;
        }

        return total / engineCount;
    }

    public Vehicle longestRange() {
        if (count == 0) {
            return null;
        }

        Vehicle longest = vehicles[0];

        for (int i = 1; i < count; i++) {
            if (vehicles[i].rangeInMiles() > longest.rangeInMiles()) {
                longest = vehicles[i];
            }
        }

        return longest;
    }
}