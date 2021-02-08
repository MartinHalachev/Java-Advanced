package pizza;

public enum Doughs {

    WHITE(1.5),
    WHOLEGRAIN(1.0);
    private double modifier;

    Doughs(double modifier) {
        this.modifier = modifier;
    }

    public double getModifier() {
        return modifier;
    }
}
