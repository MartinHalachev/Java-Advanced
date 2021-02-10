package vehiclesExtended;

public class Car extends Vehicle {
    private static final double ADDITIONAL_CONSUMPTION = 0.9;

    protected Car(double fuelQuantity, double fuelConsumptionPerKm, double tankCapacity) {
        super(fuelQuantity, fuelConsumptionPerKm + ADDITIONAL_CONSUMPTION, tankCapacity);
    }
}