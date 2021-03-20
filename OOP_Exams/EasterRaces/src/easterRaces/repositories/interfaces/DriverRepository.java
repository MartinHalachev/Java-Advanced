package easterRaces.repositories.interfaces;

import easterRaces.entities.drivers.Driver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DriverRepository implements Repository<Driver> {
    private List<Driver> drivers = new ArrayList<>();

    @Override
    public Driver getByName(String name) {
        for (Driver driver : drivers) {
            if (driver.getName().equals(name)) {
                return driver;
            }
        }
        return null;
    }

    @Override
    public Collection<Driver> getAll() {
        return this.drivers;
    }

    @Override
    public void add(Driver model) {
        this.drivers.add(model);
    }

    @Override
    public boolean remove(Driver model) {
        return this.drivers.remove(model);
    }
}
