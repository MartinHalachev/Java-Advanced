package guild;

import java.util.ArrayList;
import java.util.List;

public class Guild {
    private String name;
    private int capacity;
    private List<Player> roster;

    public Guild(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.roster = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        if (this.roster.size() < capacity) {
            this.roster.add(player);
        }
    }

    public boolean removePlayer(String name) {
        return this.roster.removeIf(e -> e.getName().equals(name));
    }

    public void promotePlayer(String name) {
        for (Player player : roster) {
            if (player.getName().equals(name) && !player.getClazz().equals("Member")) {
                player.setRank("Member");
                break;
            }
        }
    }

    public void demotePlayer(String name) {
        for (Player player : roster) {
            if (player.getName().equals(name) && !player.getClazz().equals("Trial")) {
                player.setRank("Trial");
                break;
            }
        }
    }

    public Player[] kickPlayersByClass(String clazz) {
        List<Player> kicked = new ArrayList<>();
        for (Player player : roster) {
            if (player.getClazz().equals(clazz)) {
                kicked.add(player);
            }
        }
        for (int i = 0; i < roster.size(); i++) {
            if (roster.get(i).getClazz().equals(clazz)) {
                roster.remove(i);
                i--;
            }
        }
        Player[] kickedPlayerInArray = new Player[kicked.size()];
        for (int i = 0; i < kicked.size(); i++) {
            kickedPlayerInArray[i] = kicked.get(i);
        }
        return kickedPlayerInArray;
    }

    public int count() {
        return this.roster.size();
    }

    public String report() {
        StringBuilder output = new StringBuilder("Players in the guild: " + this.name + ":").append(System.lineSeparator());
        for (Player player : roster) {
            output.append(player).append(System.lineSeparator());
        }
        return output.toString();
    }
}
