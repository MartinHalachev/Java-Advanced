package viceCity.core.interfaces;
import viceCity.common.ConstantMessages;
import viceCity.common.ExceptionMessages;
import viceCity.core.interfaces.Controller;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;

import java.util.*;

public class ControllerImpl implements Controller {
    private final static String MAIN_PLAYER_NAME = "Tommy Vercetti";
    private final static String MAIN_PLAYER_COMMAND = "Vercetti";

    private MainPlayer mainPlayer;
    private Collection<Player> civilPlayers;
    private Deque<Gun> guns;
    private Neighbourhood neighbourhood;

    public ControllerImpl() {
        this.mainPlayer = new MainPlayer();
        this.civilPlayers = new ArrayList<>();
        this.guns = new ArrayDeque<>();
        this.neighbourhood = new GangNeighbourhood();
    }

    @Override
    public String addPlayer(String name) {
        Player player = new CivilPlayer(name);
        this.civilPlayers.add(player);

        return String.format(ConstantMessages.PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        Gun gun = null;
        String result;

        if (Pistol.class.getSimpleName().equals(type)) {
            gun = new Pistol(name);
        } else if (Rifle.class.getSimpleName().equals(type)) {
            gun = new Rifle (name);
        }

        if (gun == null) {
            result = ConstantMessages.GUN_TYPE_INVALID;
        } else {
            result = String.format(ConstantMessages.GUN_ADDED, name, type);
            this.guns.offer(gun);
        }

        return result;
    }

    @Override
    public String addGunToPlayer(String name) {
        Gun gun = guns.peek();

        if (gun == null) {
            return ConstantMessages.GUN_QUEUE_IS_EMPTY;
        }
        if (MAIN_PLAYER_COMMAND.equals(name)) {
            gun = this.guns.poll();
            this.mainPlayer.getGunRepository().add(gun);

            return String.format(ConstantMessages.GUN_ADDED_TO_MAIN_PLAYER, gun.getName(), MAIN_PLAYER_NAME);
        }
        Player player = this.civilPlayers
                .stream()
                .filter(p -> p.getName().equals(name)).findFirst().orElse(null);

        if (player == null) {
            return ConstantMessages.CIVIL_PLAYER_DOES_NOT_EXIST;
        }

        gun = this.guns.poll();
        player.getGunRepository().add(gun);
        return String.format(ConstantMessages.GUN_ADDED_TO_CIVIL_PLAYER, gun.getName(), player.getName());
    }

    @Override
    public String fight() {
        this.neighbourhood.action(this.mainPlayer, this.civilPlayers);

        long deadCivilPlayers = civilPlayers.stream()
                .filter(p->!p.isAlive())
                .count();

        StringBuilder sb = new StringBuilder();

        boolean allLifePointsCivilPlayer = this.civilPlayers.stream()
                .allMatch(p->p.getLifePoints() == 50);

        if (this.mainPlayer.getLifePoints() == 100 && allLifePointsCivilPlayer) {
            sb.append(ConstantMessages.FIGHT_HOT_HAPPENED);
        } else {
            sb.append(ConstantMessages.FIGHT_HAPPENED);
            sb.append(System.lineSeparator());
            sb.append(String.format(ConstantMessages.MAIN_PLAYER_LIVE_POINTS_MESSAGE, this.mainPlayer.getLifePoints()));
            sb.append(System.lineSeparator());
            sb.append(String.format(ConstantMessages.MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE, deadCivilPlayers));
            sb.append(System.lineSeparator());
            sb.append(String.format(ConstantMessages.CIVIL_PLAYERS_LEFT_MESSAGE, this.civilPlayers.size() - deadCivilPlayers));
        }

        return sb.toString().trim();
    }
}

