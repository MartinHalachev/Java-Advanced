package CounterStriker.repositories;

import CounterStriker.models.guns.Gun;
import CounterStriker.models.players.Player;

import java.util.*;

public class PlayerRepository implements Repository<Player> {
    private Collection<Player> models = new ArrayList<>();


    @Override
    public Collection<Player> getModels() {
        return this.models;
    }

    @Override
    public void add(Player player) {
        models.add(player);
    }

    @Override
    public boolean remove(Player player) {
        return models.remove(player);
    }

    @Override
    public Player findByName(String name) {
        for (Player model : models) {
            if (model.getUsername().equals(name)) {
                return model;
            }
        }
        return null;
    }
}
