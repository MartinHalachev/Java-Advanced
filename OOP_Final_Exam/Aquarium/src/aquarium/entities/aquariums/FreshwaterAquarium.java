package aquarium.entities.aquariums;

public class FreshwaterAquarium extends BaseAquarium {
    private final static int CAPACITY = 50;

    public FreshwaterAquarium(String name) {
        super(name, CAPACITY);
    }
}
