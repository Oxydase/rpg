package dm.rpg;

import java.util.Scanner;

public class GameManager {
    private Character player;
    private WeaponStore weaponStore;
    private MapManager mapManager;
    private CombatManager combatManager;

    public GameManager(Character player) {
        this.player = player;
        this.weaponStore = new WeaponStore();
        this.mapManager = new MapManager(player);
        this.combatManager = new CombatManager();
    }

    public void displayMenu() {
        System.out.println("1. Acheter une arme");
        System.out.println("2. Voir l'inventaire");
        System.out.println("3. Se déplacer sur la carte");
        System.out.println("4. Voir la carte");
        System.out.println("5. Quitter");
    }

    public boolean handleChoice(int choice, Scanner scanner) {
        switch (choice) {
            case 1 -> buyWeapon(scanner);
            case 2 -> viewInventory();
            case 3 -> {
                boolean continueGame = mapManager.movePlayer(scanner, combatManager);
                if (!continueGame) return false; // Si le joueur atteint la sortie, le jeu se termine
            }
            case 4 -> mapManager.displayMap();
            case 5 -> {
                return false; // Quitter le jeu
            }
            default -> System.out.println("Choix inconnu.");
        }
        return true; // Continuer le jeu
    }

    private void buyWeapon(Scanner scanner) {
        weaponStore.printWeapons();
        System.out.println("Choisissez une arme à acheter (index): ");
        int weaponChoice = scanner.nextInt();
        Weapon chosenWeapon = weaponStore.getWeapons().get(weaponChoice);
        if (player.getMoney() >= chosenWeapon.price) {
            player.addWeapon(chosenWeapon);
            player.spendMoney(chosenWeapon.price);
            player.equipWeapon(player.getInventory().size() - 1); // Equiper automatiquement
            System.out.println("L'arme suivante à été acheté et équippé automatiquement: " + chosenWeapon);
        } else {
            System.out.println("Pas assez d'argent pour acheter cette arme.");
        }
    }

    private void viewInventory() {
        System.out.println("Inventaire: ");
        for (int i = 0; i < player.getInventory().size(); i++) {
            System.out.println("[" + i + "] " + player.getInventory().get(i));
        }
    }
}
