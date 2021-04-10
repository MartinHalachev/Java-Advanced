package aquarium.core;

import aquarium.entities.aquariums.Aquarium;
import aquarium.entities.aquariums.FreshwaterAquarium;
import aquarium.entities.aquariums.SaltwaterAquarium;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.decorations.Ornament;
import aquarium.entities.decorations.Plant;
import aquarium.entities.fish.Fish;
import aquarium.entities.fish.FreshwaterFish;
import aquarium.entities.fish.SaltwaterFish;
import aquarium.repositories.DecorationRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import static aquarium.common.ConstantMessages.*;
import static aquarium.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private DecorationRepository decorationRepository;
    private Collection<Aquarium> aquariums;

    public ControllerImpl() {
        this.decorationRepository = new DecorationRepository();
        this.aquariums = new ArrayList<>();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {
        if (aquariumType.equals("FreshwaterAquarium")) {
            this.aquariums.add(new FreshwaterAquarium(aquariumName));
        } else if (aquariumType.equals("SaltwaterAquarium")) {
            this.aquariums.add(new SaltwaterAquarium(aquariumName));
        } else {
            throw new NullPointerException(INVALID_AQUARIUM_TYPE);
        }

        return String.format(SUCCESSFULLY_ADDED_AQUARIUM_TYPE, aquariumType);
    }

    @Override
    public String addDecoration(String type) {
        if (type.equals("Ornament")) {
            decorationRepository.add(new Ornament());
        } else if (type.equals("Plant")) {
            decorationRepository.add(new Plant());
        } else {
            throw new IllegalArgumentException(INVALID_DECORATION_TYPE);
        }
        return String.format(SUCCESSFULLY_ADDED_DECORATION_TYPE, type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {
        Decoration decoration = this.decorationRepository.findByType(decorationType);
        if (decoration == null) {
            throw new IllegalArgumentException(String.format(NO_DECORATION_FOUND, decorationType));
        }
        for (Aquarium aquarium : aquariums) {
            if (aquarium.getName().equals(aquariumName)) {
                aquarium.addDecoration(decoration);
                decorationRepository.remove(decoration);
                return String.format(SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM, decorationType, aquariumName);
            }
        }
        return null;
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {
        Fish fish = null;
        if (fishType.equals("FreshwaterFish")) {
            fish = new FreshwaterFish(fishName, fishSpecies, price);
        } else if (fishType.equals("SaltwaterFish")) {
            fish = new SaltwaterFish(fishName, fishSpecies, price);
        } else {
            throw new IllegalArgumentException(INVALID_FISH_TYPE);
        }
        for (Aquarium aquarium : aquariums) {
            if (aquarium.getName().equals(aquariumName)) {
                if (aquarium.getClass().getSimpleName().equals("SaltwaterAquarium") && fishType.equals("SaltwaterFish")) {
                    aquarium.addFish(fish);
                    return String.format(SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName);
                } else if (aquarium.getClass().getSimpleName().equals("FreshwaterAquarium") && fishType.equals("FreshwaterFish")) {
                    return String.format(SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName);
                } else {
                    throw new IllegalArgumentException(WATER_NOT_SUITABLE);
                }
            }
        }
        return null;
    }

    @Override
    public String feedFish(String aquariumName) {
        int fishFed = 0;
        for (Aquarium aquarium : aquariums) {
            if (aquarium.getName().equals(aquariumName)) {
                aquarium.feed();
                fishFed = aquarium.getFish().size();
                break;
            }
        }
        return String.format(FISH_FED, fishFed);
    }

    @Override
    public String calculateValue(String aquariumName) {
        double value = 0;
        for (Aquarium aquarium : aquariums) {
            if (aquarium.getName().equals(aquariumName)) {
                value += aquarium.getDecorations().stream().mapToDouble(Decoration::getPrice).sum();
                value += aquarium.getFish().stream().mapToDouble(Fish::getPrice).sum();
                break;
            }
        }
        return String.format(VALUE_AQUARIUM, aquariumName, value);
    }

    @Override
    public String report() {
        StringBuilder result = new StringBuilder();
        for (Aquarium aquarium : aquariums) {
            result.append(aquarium.getInfo()).append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
