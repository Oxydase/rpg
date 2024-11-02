package dm.rpg;

public abstract class Weapon {

    protected double damage;
    protected double price;
    protected String name;

    protected double monster_damage_ratio;
    protected double obstacle_damage_ratio;

    public Weapon(double d, double p, String name, double monsterRatio, double obstacleRatio) {
        this.damage = d;
        this.price = p;
        this.name = name;
        this.monster_damage_ratio = monsterRatio;
        this.obstacle_damage_ratio = obstacleRatio;
    }

    public double getMonsterDamageRatio() {
        return this.monster_damage_ratio;
    }

    public double getObstacleDamageRatio() {
        return this.obstacle_damage_ratio;
    }

    public abstract String asciiArt();

    public void attack(Monster m) {
        m.hit(this.damage * this.getMonsterDamageRatio());
    }

    public void attack(Obstacle o) {
        o.hit(this.damage * this.getObstacleDamageRatio());
    }

    @Override
    public String toString() {
        return this.name + " - damage = " + this.damage + " - prix = " + this.price + "$";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Weapon)) {
            return false;
        }
        Weapon w = (Weapon) obj;
        return this.damage == w.damage;
    }

}
