package rabbits;

import java.util.ArrayList;
import java.util.List;

public class Cage {
    private String name;
    private int capacity;
    private List<Rabbit> data;

    public Cage(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void add(Rabbit rabbit) {
        if (this.data.size() < capacity) {
            data.add(rabbit);
        }
    }

    public boolean removeRabbit(String name) {
        return data.removeIf(e -> e.getName().equals(name));
    }

    public Rabbit sellRabbit(String name) {
        Rabbit toSell = null;
        for (Rabbit rabbit : data) {
            if (rabbit.getName().equals(name)) {
                rabbit.setAvailable(false);
                toSell = rabbit;
                return toSell;
            }
        }
        return null;
    }

    public List<Rabbit> sellRabbitBySpecies(String species) {
        List<Rabbit> listOfRabbitsSell = new ArrayList<>();
        for (Rabbit rabbit : data) {
            if (rabbit.getSpecies().equals(species)) {
                rabbit.setAvailable(false);
                listOfRabbitsSell.add(rabbit);
            }
        }
        return listOfRabbitsSell;
    }

    public int count() {
        return this.data.size();
    }

    public String report() {
        StringBuilder output = new StringBuilder("Rabbits available at " + this.name + ":" + System.lineSeparator());
        for (Rabbit rabbit : data) {
            output.append(rabbit).append(System.lineSeparator());
        }
        return output.toString();
    }
}
