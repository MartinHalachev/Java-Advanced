package aquarium.entities.decorations;

public class Plant extends BaseDecoration{
    private final static int COMFORT = 5;
    private final static double PRICE = 10;

    public Plant() {
        super(COMFORT, PRICE);
    }
}
