package easterRaces.core;

import easterRaces.core.interfaces.Controller;
import easterRaces.entities.cars.Car;
import easterRaces.entities.cars.MuscleCar;
import easterRaces.entities.cars.SportsCar;
import easterRaces.entities.drivers.Driver;
import easterRaces.entities.drivers.DriverImpl;
import easterRaces.entities.racers.Race;
import easterRaces.entities.racers.RaceImpl;
import easterRaces.repositories.interfaces.CarRepository;
import easterRaces.repositories.interfaces.DriverRepository;
import easterRaces.repositories.interfaces.RaceRepository;
import easterRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static easterRaces.common.ExceptionMessages.*;
import static easterRaces.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private Repository<Car> carRepository;
    private Repository<Driver> driverRepository;
    private Repository<Race> raceRepository;

    public ControllerImpl(Repository<Driver> riderRepository, Repository<Car> motorcycleRepository, Repository<Race> raceRepository) {
        this.carRepository = motorcycleRepository;
        this.driverRepository = riderRepository;
        this.raceRepository = raceRepository;

    }

    @Override
    public String createDriver(String driver) {
        containsDriver(driver);
        Driver driverToAdd = new DriverImpl(driver);
        driverRepository.add(driverToAdd);
        return String.format(DRIVER_CREATED, driver);
    }


    @Override
    public String createCar(String type, String model, int horsePower) {
        containsCar(model);
        Car car = null;
        switch (type) {
            case "Muscle":
                car = new MuscleCar(model, horsePower);
                break;
            case "Sports":
                car = new SportsCar(model, horsePower);
                break;
        }
        assert carRepository != null;
        carRepository.add(car);
        return String.format(CAR_CREATED, car.getClass().getSimpleName(), model);
    }


    @Override
    public String addCarToDriver(String driverName, String carModel) {
        boolean containsDriver = false;
        boolean containsCar = false;
        for (Driver driver : driverRepository.getAll()) {
            if (driver.getName().equals(driverName)) {
                containsDriver = true;
                break;
            }
        }
        for (Car car : carRepository.getAll()) {
            if (car.getModel().equals(carModel)) {
                containsCar = true;
                break;
            }
        }
        if (!containsDriver) {

            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }
        if (!containsCar) {
            throw new IllegalArgumentException(String.format(CAR_NOT_FOUND, carModel));
        }


        this.driverRepository.getByName(driverName).addCar(this.carRepository.getByName(carModel));
        return String.format(CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        boolean containsDriver = false;
        boolean containsRace = false;

        for (Driver driver : driverRepository.getAll()) {
            if (driver.getName().equals(driverName)) {
                containsDriver = true;
                break;
            }
        }
        for (Race race : raceRepository.getAll()) {
            if (race.getName().equals(raceName)) {
                containsRace = true;
                break;
            }
        }
        if (!containsRace) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }
        if (!containsDriver) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }
        this.raceRepository.getByName(raceName).addDriver(this.driverRepository.getByName(driverName));
        return String.format(DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String createRace(String name, int laps) {
        boolean containsRace = false;
        for (Race race : raceRepository.getAll()) {
            if (race.getName().equals(name)) {
                containsRace = true;
                break;
            }
        }
        if (containsRace) {
            throw new IllegalArgumentException(String.format(RACE_EXISTS, name));
        }
        this.raceRepository.add(new RaceImpl(name, laps));
        return String.format(RACE_CREATED, name);
    }

    @Override
    public String startRace(String raceName) {
        boolean containsRace = false;
        for (Race race : raceRepository.getAll()) {
            if (race.getName().equals(raceName)) {
                containsRace = true;
                break;
            }
        }
        if (!containsRace) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }
        List<Driver> sorted = driverRepository.getAll().stream()
                .sorted((f, s) -> (int) (s.getCar().calculateRacePoints(this.raceRepository.getByName(raceName).getLaps())
                        - f.getCar().calculateRacePoints(this.raceRepository.getByName(raceName).getLaps()))).collect(Collectors.toList());
        if (sorted.size() < 3) {
            throw new IllegalArgumentException(String.format(RACE_INVALID, raceName, 3));
        }
        return String.format(DRIVER_FIRST_POSITION, sorted.get(0).getName(), raceName) + System.lineSeparator() +
                String.format(DRIVER_SECOND_POSITION, sorted.get(1).getName(), raceName) + System.lineSeparator() +
                String.format(DRIVER_THIRD_POSITION, sorted.get(2).getName(), raceName);
    }


    private void containsDriver(String driver) {
        if (this.driverRepository != null) {
            for (Driver currentDriver : driverRepository.getAll()) {
                if (currentDriver.getName().equals(driver)) {
                    throw new IllegalArgumentException(String.format(DRIVER_EXISTS, driver));
                }
            }
        }
    }

    private void containsCar(String model) {
        if (carRepository != null) {
            for (Car car : carRepository.getAll()) {
                if (car.getModel().equals(model)) {
                    throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
                }
            }
        }
    }
}
