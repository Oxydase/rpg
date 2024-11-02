package dm.rpg;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final int MAP_SIZE = 5;
    private static String[][] map = new String[MAP_SIZE][MAP_SIZE];
    private static int playerX = MAP_SIZE - 1;
    private static int playerY = 0;
    private static Monster[][] monsters = new Monster[MAP_SIZE][MAP_SIZE];

    public static void main(String[] args) throws CloneNotSupportedException {
        Scanner scanner = new Scanner(System.in);

        // Initialise la carte et place des monstres
        initializeMap();

        // Création du personnage
        System.out.println("Choose a name: ");
        String name = scanner.nextLine();
        System.out.println("Choose a caste (ex Sorcerer, Elf): ");
        String caste = scanner.nextLine();
        Character player = new Character(name, caste, 100, 100); // Initial money and health

        WeaponStore store = new WeaponStore();

        // Lancement du jeu
        boolean gameRunning = true;
        while (gameRunning) {
            System.out.println("1. Buy weapon");
            System.out.println("2. View inventory");
            System.out.println("3. Move on the map");
            System.out.println("4. View map");
            System.out.println("5. Quit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    store.printWeapons();
                    System.out.println("Choose a weapon to buy (index): ");
                    int weaponChoice = scanner.nextInt();
                    Weapon chosenWeapon = store.getWeapons().get(weaponChoice);
                    if (player.getMoney() >= chosenWeapon.price) {
                        player.addWeapon(chosenWeapon);
                        player.spendMoney(chosenWeapon.price);
                        player.equipWeapon(player.getInventory().size() - 1); // Automatically equip the purchased weapon
                        System.out.println("Bought and equipped: " + chosenWeapon);
                    } else {
                        System.out.println("Not enough money to buy this weapon.");
                    }
                    break;
                case 2:
                    System.out.println("Inventory: ");
                    for (int i = 0; i < player.getInventory().size(); i++) {
                        System.out.println("[" + i + "] " + player.getInventory().get(i));
                    }
                    break;
                case 3:
                    movePlayer(scanner, player);
                    break;
                case 4:
                    displayMap();
                    break;
                case 5:
                    gameRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }

        System.out.println("Game Over!");
    }

    private static void initializeMap() {
        Random random = new Random();
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                map[i][j] = "."; // Default empty space
                if (random.nextDouble() < 0.2 && !(i == MAP_SIZE - 1 && j == 0)) { // 20% chance to place a monster
                    monsters[i][j] = new Monster();
                    map[i][j] = "M"; // Monster on the map
                }
            }
        }
        map[playerX][playerY] = "P"; // Player's starting position
    }

    private static void movePlayer(Scanner scanner, Character player) {
        // Enleve la position actuelle
        map[playerX][playerY] = ".";

        System.out.println("Move (Z/Q/S/D): ");
        char move = scanner.next().toUpperCase().charAt(0);
        switch (move) {
            case 'Z':
                if (playerX > 0) playerX--;
                break;
            case 'Q':
                if (playerY > 0) playerY--;
                break;
            case 'S':
                if (playerX < MAP_SIZE - 1) playerX++;
                break;
            case 'D':
                if (playerY < MAP_SIZE - 1) playerY++;
                break;
            default:
                System.out.println("Invalid move.");
                return;
        }

        // Vérifie la présence de monstre
        if (monsters[playerX][playerY] != null) {
            System.out.println("You encountered a monster! Prepare to fight!");
            fightMonster(player, monsters[playerX][playerY]);
            monsters[playerX][playerY] = null; // Remove monster after the fight if defeated
        }

        // Change la position
        map[playerX][playerY] = "P";
        System.out.println("Moved to: (" + playerX + ", " + playerY + ")");
    }

    private static void fightMonster(Character player, Monster monster) {
        if (player.getEquippedWeapon() == null) {
            System.out.println("You have no weapon equipped! You can't fight.");
            return;
        }

        while (monster.health > 0 && player.getHealth() > 0) {
            player.getEquippedWeapon().attack(monster);
            System.out.println("Attacked the monster! Monster health: " + monster.health);

            if (monster.health <= 0) {
                System.out.println("You defeated the monster and gained 10 XP!");
                player.gainXp(10);
                break;
            }

            System.out.println("The monster attacks back!");
            player.takeDamage(5); // Simplified combat: monster does a fixed damage of 5
            System.out.println("Your health: " + player.getHealth());

            if (player.getHealth() <= 0) {
                System.out.println("You have been defeated by the monster!");
                break;
            }
        }
    }

    private static void displayMap() {
        System.out.println("Map:");
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
