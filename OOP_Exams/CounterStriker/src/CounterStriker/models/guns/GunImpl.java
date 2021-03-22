package CounterStriker.models.guns;

import static CounterStriker.common.ExceptionMessages.INVALID_GUN;
import static CounterStriker.common.ExceptionMessages.INVALID_GUN_BULLETS_COUNT;

public abstract class GunImpl implements Gun {
    private String name;
    private int bulletsCount;

    protected GunImpl(String name, int bulletsCount) {
        this.setName(name);
        this.setBulletsCount(bulletsCount);
    }

    private void setName(String name) {
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException(INVALID_GUN);
        }
        this.name = name;
    }

    private void setBulletsCount(int bulletsCount) {
        if (bulletsCount < 0) {
            throw new IllegalArgumentException(INVALID_GUN_BULLETS_COUNT);
        }
        this.bulletsCount = bulletsCount;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getBulletsCount() {
        return this.bulletsCount;
    }

    @Override
    public int fire() {
        if (this.getClass().getSimpleName().equals("Pistol")) {
            this.bulletsCount -= 1;
            return 1;
        } else if (this.getClass().getSimpleName().equals("Rifle")) {
            this.bulletsCount -= 10;
            return 10;
        }
        return 0;
    }
}
