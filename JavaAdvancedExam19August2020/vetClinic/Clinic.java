package vetClinic;

import java.util.ArrayList;
import java.util.List;

public class Clinic {
    private int capacity;
    private List<Pet> data;

    public Clinic(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void add(Pet pet) {
        if (this.data.size() < capacity) {
            this.data.add(pet);
        }
    }

    public boolean remove(String name) {
        return this.data.removeIf(e -> e.getName().equals(name));
    }

    public Pet getPet(String name, String owner) {
        Pet petRequested = null;
        for (Pet pet : data) {
            if (pet.getName().equals(name)
                    && pet.getOwner().equals(owner)) {
                petRequested = pet;
            }
        }
        return petRequested;
    }

    public Pet getOldestPet() {
        Pet oldestPet = null;
        for (Pet pet : data) {
            if (oldestPet == null || oldestPet.getAge() < pet.getAge()) {
                oldestPet = pet;
            }
        }
        return oldestPet;
    }

    public int getCount() {
        return this.data.size();
    }

    public String getStatistics() {
        StringBuilder output = new StringBuilder("The clinic has the following patients:" + System.lineSeparator());
        for (Pet pet : data) {
            output.append(pet.getName())
                    .append(" ")
                    .append(pet.getOwner())
                    .append(System.lineSeparator());
        }
        return output.toString().trim();
    }
}
