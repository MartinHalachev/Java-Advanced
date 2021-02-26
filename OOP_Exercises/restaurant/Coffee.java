package restaurant;

import java.math.BigDecimal;

public class Coffee extends HotBeverage {
    private static final double COFFEE_MILLILITER = 50;
    private static final BigDecimal COFFEE_PRICE = BigDecimal.valueOf(3.50);
    private double caffeine;

    public Coffee(String name, double caffeine) {
        super(name, COFFEE_PRICE, COFFEE_MILLILITER);
        this.caffeine = caffeine;
    }

    public double getCaffeine() {
        return this.caffeine;
    }

}
