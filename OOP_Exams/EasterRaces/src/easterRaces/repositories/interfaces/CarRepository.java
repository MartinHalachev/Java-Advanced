package easterRaces.repositories.interfaces;

import easterRaces.entities.cars.BaseCar;
import easterRaces.entities.cars.Car;

import java.util.ArrayList;
import java.util.Collection;

public class CarRepository implements Repository<Car> {
    private Collection<Car> cars = new ArrayList<>();

    @Override
    public Car getByName(String name) {
        for (Car car : cars) {
            if (car.getModel().equals(name)) {
                return car;
            }
        }
        return null;
    }

    @Override
    public Collection<Car> getAll() {
        return this.cars;
    }

    @Override
    public void add(Car model) {
        this.cars.add(model);
    }

    @Override
    public boolean remove(Car model) {
        return this.cars.remove(model);
    }
}
