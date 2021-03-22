package CounterStriker.core;

import CounterStriker.models.field.Field;
import CounterStriker.models.field.FieldImpl;
import CounterStriker.models.guns.Gun;
import CounterStriker.models.guns.Pistol;
import CounterStriker.models.guns.Rifle;
import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;
import CounterStriker.models.players.Terrorist;
import CounterStriker.repositories.GunRepository;
import CounterStriker.repositories.PlayerRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static CounterStriker.common.ExceptionMessages.INVALID_GUN_TYPE;
import static CounterStriker.common.ExceptionMessages.INVALID_PLAYER_TYPE;
import static CounterStriker.common.OutputMessages.SUCCESSFULLY_ADDED_GUN;
import static CounterStriker.common.OutputMessages.SUCCESSFULLY_ADDED_PLAYER;

public class ControllerImpl implements Controller {
    private GunRepository gunRepository;
    private PlayerRepository playerRepository;
    private Field field;

    public ControllerImpl() {
        this.gunRepository = new GunRepository();
        this.playerRepository = new PlayerRepository();
        this.field = new FieldImpl();
    }

    @Override
    public String addGun(String type, String name, int bulletsCount) {
        if (type.equals("Pistol")) {
            gunRepository.add(new Pistol(name, bulletsCount));
        } else if (type.equals("Rifle")) {
            gunRepository.add(new Rifle(name, bulletsCount));
        } else {
            throw new IllegalArgumentException(INVALID_GUN_TYPE);
        }


        return String.format(SUCCESSFULLY_ADDED_GUN, name);
    }

    @Override
    public String addPlayer(String type, String username, int health, int armor, String gunName) {
        boolean containsGun = false;
        for (Gun model : gunRepository.getModels()) {
            if (model.getName().equals(gunName)) {
                containsGun = true;
                break;
            }
        }
        if (!containsGun) {
            throw new IllegalArgumentException(INVALID_GUN_TYPE);
        }
        if (!type.equals("Terrorist") && !type.equals("CounterTerrorist")) {
            throw new IllegalArgumentException(INVALID_PLAYER_TYPE);
        }
        switch (type) {
            case "CounterTerrorist":
                playerRepository.add(new CounterTerrorist(username, health, armor, gunRepository.findByName(gunName)));
                break;
            case "Terrorist":
                playerRepository.add(new Terrorist(username, health, armor, gunRepository.findByName(gunName)));
                break;
        }
        return String.format(SUCCESSFULLY_ADDED_PLAYER, username);
    }

    @Override
    public String startGame() {
        return field.start(playerRepository.getModels());
    }

    @Override
    public String report() {
        field.start(playerRepository.getModels());
        StringBuilder result = new StringBuilder();
        Map<String, Player> counterTerrorists = new TreeMap<>();
        Map<String, Player> terrorists = new TreeMap<>();
        for (Player player : playerRepository.getModels()) {
            if (player.getClass().getSimpleName().equals("CounterTerrorist")) {
                counterTerrorists.put(player.getUsername(), player);
            } else if (player.getClass().getSimpleName().equals("Terrorist")) {
                terrorists.put(player.getUsername(), player);
            }
        }
        for (Player value : counterTerrorists.values()) {
            result.append(value.toString().trim()).append(System.lineSeparator());
        }
        for (Player value : terrorists.values()) {
            result.append(value.toString().trim()).append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
