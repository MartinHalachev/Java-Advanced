package carShopExtended;

public class Seat extends CarImpl implements Sellable {
    private Double price;

    protected Seat(String model, String color, Integer horsePower, String countryProduced, Double price) {
        super(model, color, horsePower, countryProduced);
        this.price = price;
    }

    @Override
    public Double getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator() + String.format("Leon sells for %.6f",
                this.price);
    }
}
