package dm.rpg;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialisation du jeu
        System.out.println("Choisissez un nom: ");
        String name = scanner.nextLine();
        System.out.println("Choisissez une classe (ex Sorcier, Guerrier): ");
        String caste = scanner.nextLine();
        Character player = new Character(name, caste, 100, 100);

        GameManager gameManager = new GameManager(player);

        // Lancement du jeu
        boolean gameRunning = true;
        while (gameRunning) {
            gameManager.displayMenu();
            int choice = scanner.nextInt();
            gameRunning = gameManager.handleChoice(choice, scanner);
        }

        System.out.println("Game Over!");
    }
}
