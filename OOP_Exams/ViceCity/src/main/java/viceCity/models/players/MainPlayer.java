package viceCity.models.players;

public class MainPlayer extends BasePlayer {
    protected final static String NAME = "Tommy Vercetti";
    protected final static int LIFE_POINTS = 100;

    public MainPlayer() {
        super(NAME, LIFE_POINTS);
    }
}
