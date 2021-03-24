package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;

import java.util.Collection;
import java.util.stream.Collectors;

public class GangNeighbourhood implements Neighbourhood {
    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {
        for (Player civilPlayer : civilPlayers) {
            for (Gun gun : mainPlayer.getGunRepository().getModels()) {
                while (civilPlayer.isAlive() && gun.canFire()) {
                    civilPlayer.takeLifePoints(gun.fire());
                }
            }
        }
        for (Player civilPlayer : civilPlayers) {
            if (civilPlayer.isAlive()) {
                for (Gun gun : civilPlayer.getGunRepository().getModels()) {
                    while (mainPlayer.isAlive() && gun.canFire()) {
                        mainPlayer.takeLifePoints(gun.fire());
                    }
                }
            }
            if (!mainPlayer.isAlive()) {
                break;
            }
        }
    }
}
