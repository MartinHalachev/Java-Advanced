package aquarium.entities.decorations;

public class Ornament extends BaseDecoration {
    private final static int COMFORT = 1;
    private final static double PRICE = 5;

    public Ornament() {
        super(COMFORT, PRICE);
    }
}
