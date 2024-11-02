package dm.rpg;

public class Hammer extends Weapon {

    private static final double DAMAGE = 10;
    private static final double PRICE = 10;
    private static final String NAME = "Hammer";

    private static final double MONSTER_DAMAGE_RATIO = 0.4;
    private static final double OBSTACLE_DAMAGE_RATIO = 1.5;


    public Hammer() {
        super(DAMAGE, PRICE, NAME, MONSTER_DAMAGE_RATIO, OBSTACLE_DAMAGE_RATIO);
    }

    public String asciiArt() {
        return
                "             +-+\n" +
                "=============| |\n" +
                "            `:_;'";

    }
}
