package aquarium.entities.aquariums;

public class SaltwaterAquarium extends BaseAquarium {
    private final static int CAPACITY = 25;

    public SaltwaterAquarium(String name) {
        super(name, CAPACITY);
    }
}
