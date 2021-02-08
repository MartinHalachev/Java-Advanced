package pizza;

public enum Toppings {
    MEAT(1.2),
    VEGGIES(0.8),
    CHEESE(1.1),
    SAUCE(0.9);

    private double modifier;

    Toppings(double modifier) {
        this.modifier = modifier;
    }

    public double getModifier() {
        return modifier;
    }
}
