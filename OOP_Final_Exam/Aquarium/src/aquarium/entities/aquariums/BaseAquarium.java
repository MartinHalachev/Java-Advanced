package aquarium.entities.aquariums;

import aquarium.entities.decorations.Decoration;
import aquarium.entities.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;

import static aquarium.common.ConstantMessages.*;
import static aquarium.common.ExceptionMessages.*;

public abstract class BaseAquarium implements Aquarium {
    private String name;
    private int capacity;
    private Collection<Decoration> decorations;
    private Collection<Fish> fish;


    protected BaseAquarium(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.decorations = new ArrayList<>();
        this.fish = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(AQUARIUM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int calculateComfort() {
        return decorations.stream().mapToInt(Decoration::getComfort).sum();
    }

    @Override
    public void addFish(Fish fish) {
        if (this.capacity == fish.getSize()) {
            throw new IllegalArgumentException(NOT_ENOUGH_CAPACITY);
        }
        this.fish.add(fish);
    }

    @Override
    public String getName() {
        return this.name;
    }


    @Override
    public void removeFish(Fish fish) {
        this.fish.remove(fish);
    }

    @Override
    public void addDecoration(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public void feed() {
        for (Fish f : fish) {
            f.eat();
        }
    }

    @Override
    public String getInfo() {
        if (this.fish.isEmpty()) {
            return "none";
        }
        StringBuilder result = new StringBuilder();
        StringBuilder printFish = new StringBuilder();
        int index = 0;
        for (Fish fish1 : fish) {
            if (index == fish.size() - 1) {
                printFish.append(fish1.getName());
            } else {
                printFish.append(fish1.getName()).append(" ");
            }
            index++;
        }
        result.append(String.format("%s (%s):%nFish: %s%nDecorations: %d%nComfort: %d",
                this.name,
                this.getClass().getSimpleName(),
                printFish.toString(),
                decorations.size(),
                this.calculateComfort()));
        return result.toString().trim();
    }

    @Override
    public Collection<Fish> getFish() {
        return this.fish;
    }

    @Override
    public Collection<Decoration> getDecorations() {
        return this.decorations;
    }
}
