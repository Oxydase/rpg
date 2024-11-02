package dm.rpg;

import java.util.ArrayList;

public class Character {
    private String name;
    private String caste;
    private double money;
    private int xp;
    private double health;
    private ArrayList<Weapon> inventory;
    private Weapon equippedWeapon;

    public Character(String name, String caste, double money, double health) {
        this.name = name;
        this.caste = caste;
        this.money = money;
        this.xp = 0;
        this.health = health;
        this.inventory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getCaste() {
        return caste;
    }

    public double getMoney() {
        return money;
    }

    public int getXp() {
        return xp;
    }

    public double getHealth() {
        return health;
    }

    public ArrayList<Weapon> getInventory() {
        return inventory;
    }

    public void addWeapon(Weapon weapon) {
        this.inventory.add(weapon);
    }

    public void equipWeapon(int index) {
        if (index >= 0 && index < inventory.size()) {
            this.equippedWeapon = inventory.get(index);
            System.out.println("Equipped: " + equippedWeapon);
        } else {
            System.out.println("Invalid weapon choice.");
        }
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void addMoney(double amount) {
        this.money += amount;
    }

    public void spendMoney(double amount) {
        if (amount <= money) {
            this.money -= amount;
        } else {
            System.out.println("Not enough money!");
        }
    }

    public void gainXp(int amount) {
        this.xp += amount;
    }

    public void takeDamage(double amount) {
        this.health -= amount;
        if (this.health < 0) {
            this.health = 0;
        }
    }
}
