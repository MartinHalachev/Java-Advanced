package viceCity.repositories.interfaces;

import viceCity.models.guns.Gun;

import java.util.*;

public class GunRepository implements Repository<Gun> {

    private Map<String, Gun> models;

    public GunRepository() {
        this.models = new HashMap<>();
    }

    @Override
    public Collection<Gun> getModels() {
        return Collections.unmodifiableCollection(this.models.values());
    }

    @Override
    public void add(Gun model) {
        if (!models.containsKey(model)) {
            this.models.put(model.getName(), model);
        }
    }

    @Override
    public boolean remove(Gun model) {
        Gun removeGun = models.get(model.getName());
        boolean isRemoved = true;

        if (removeGun == null) {
            isRemoved = false;
        }
        return isRemoved;
    }

    @Override
    public Gun find(String name) {
        return this.models.get(name);
    }
}
