package christmas;

import java.util.ArrayList;
import java.util.List;

public class Bag {
    private String color;
    private int capacity;
    private List<Present> data;

    public Bag(String color, int capacity) {
        this.color = color;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public String getColor() {
        return color;
    }

    public int getCapacity() {
        return capacity;
    }

    public int count() {
        return this.data.size();
    }

    public void add(Present present) {
        if (this.data.size() < capacity) {
            data.add(present);
        }
    }

    public boolean remove(String name) {
        return data.removeIf(e -> e.getName().equals(name));
    }

    public Present heaviestPresent() {
        Present heaviest = data.get(0);
        for (Present present : data) {
            if (present.getWeight() > heaviest.getWeight()) {
                heaviest = present;
            }
        }
        return heaviest;
    }

    public Present getPresent(String name) {
        for (Present present : data) {
            if (present.getName().equals(name)) {
                return present;
            }
        }
        return null;
    }

    public String report() {
        StringBuilder output = new StringBuilder(this.color + " bag contains:" + System.lineSeparator());
        for (Present present : data) {
            output.append(present).append(System.lineSeparator());
        }
        return output.toString().trim();
    }
}
