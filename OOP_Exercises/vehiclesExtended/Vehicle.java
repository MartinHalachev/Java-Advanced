package vehiclesExtended;

import java.text.DecimalFormat;

public class Vehicle {
    private double fuelQuantity;
    private double fuelConsumptionPerKm;
    private double tankCapacity;

    protected Vehicle(double fuelQuantity, double fuelConsumptionPerKm, double tankCapacity) {
        setFuelQuantity(fuelQuantity);
        setFuelConsumptionPerKm(fuelConsumptionPerKm);
        setTankCapacity(tankCapacity);
    }

    protected double getFuelQuantity() {
        return this.fuelQuantity;
    }

    protected double getFuelConsumptionPerKm() {
        return this.fuelConsumptionPerKm;
    }

    protected double getTankCapacity() {
        return this.tankCapacity;
    }

    private void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public void setFuelConsumptionPerKm(double fuelConsumptionPerKm) {
        this.fuelConsumptionPerKm = fuelConsumptionPerKm;
    }

    private void setTankCapacity(double tankCapacity) {
        checkTankQuantityNonNegative(tankCapacity);
        this.tankCapacity = tankCapacity;
    }

    public String drive(double km) {
        double fuelNeeded = km * fuelConsumptionPerKm;
        if (fuelQuantity >= fuelNeeded) {
            fuelQuantity -= fuelNeeded;
            DecimalFormat format = new DecimalFormat("#.##");
            return String.format("%s travelled %s km", this.getClass().getSimpleName(), format.format(km));
        } else {
            return String.format("%s needs refueling", this.getClass().getSimpleName());
        }
    }

    public void refuel(double liters) {
        checkEmptySpaceInTank(liters);
        checkTankQuantityNonNegative(liters);
        this.fuelQuantity += liters;
    }

    public void checkTankQuantityNonNegative(double fuelToAdd) {
        if (fuelToAdd <= 0) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        }
    }

    public void checkEmptySpaceInTank(double fuelToAdd) {
        if (fuelToAdd > tankCapacity) {
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }
    }
}