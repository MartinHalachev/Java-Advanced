package rabbits;

public class Rabbit {
    private String name;
    private String species;
    private boolean available = true;


    public Rabbit(String name, String species) {
        this.name = name;
        this.species = species;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }


    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public boolean isAvailable() {
        return available;
    }

    @Override
    public String toString() {
        return String.format("Rabbit (%s): %s", this.species, this.getName());
    }
}
