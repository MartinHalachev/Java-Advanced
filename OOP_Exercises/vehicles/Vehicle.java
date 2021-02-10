package vehicles;

import java.text.DecimalFormat;

public abstract class Vehicle {
    private double fuelQuantity;
    private double fuelConsumptionPerKm;

    protected Vehicle(double fuelQuantity, double fuelConsumptionPerKm) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumptionPerKm = fuelConsumptionPerKm;
    }

    protected double getFuelQuantity() {
        return this.fuelQuantity;
    }

    protected double getFuelConsumptionPerKm() {
        return this.fuelConsumptionPerKm;
    }


    public String drive(double km) {
        String output = "";
        double fuelNeeded = km * fuelConsumptionPerKm;
        if (fuelQuantity >= fuelNeeded) {
            fuelQuantity -= fuelNeeded;

            DecimalFormat format = new DecimalFormat("#.##");

            output = String.format("%s travelled %s km", this.getClass().getSimpleName(), format.format(km));
        } else {
            output = String.format("%s needs refueling", this.getClass().getSimpleName());
        }
        return output;
    }

    public void refuel(double liters) {
        this.fuelQuantity += liters;
    }
}
