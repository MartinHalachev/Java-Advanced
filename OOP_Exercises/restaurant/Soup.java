package restaurant;

import java.math.BigDecimal;

public class Soup extends Starter {
    private double calories;

    public Soup(String name, BigDecimal price, double grams, double calories) {
        super(name, price, grams);
        this.calories = calories;
    }
}
