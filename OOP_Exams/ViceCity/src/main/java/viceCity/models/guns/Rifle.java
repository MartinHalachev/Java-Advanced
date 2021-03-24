package viceCity.models.guns;

public class Rifle extends BaseGun {
    private final static int DEFAULT_BULLETS_IN_BARREL = 50;
    private final static int DEFAULT_BULLETS_TOTAL = 500;
    private final static int BULLETS_FIRE_RIFLE = 5;


    public Rifle(String name) {
        super(name, DEFAULT_BULLETS_IN_BARREL, DEFAULT_BULLETS_TOTAL);
    }

    @Override
    public int fire() {
        if (this.getBulletsPerBarrel() == 0) {
            this.reload();
            if (this.getBulletsPerBarrel() == 0) {
                return 0;
            }
        }
        this.setBulletsPerBarrel(this.getBulletsPerBarrel() - BULLETS_FIRE_RIFLE);
        return BULLETS_FIRE_RIFLE;
    }

    private void reload() {
        if (this.getTotalBullets() > 0) {
            int restTotalBullets = this.getTotalBullets() - DEFAULT_BULLETS_IN_BARREL;
            this.setTotalBullets(restTotalBullets);
            this.setBulletsPerBarrel(DEFAULT_BULLETS_IN_BARREL);
        }
    }
}
