package dm.rpg;

import java.util.Random;
import java.util.Scanner;

public class MapManager {
    private static final int MAP_SIZE = 5;
    private String[][] map = new String[MAP_SIZE][MAP_SIZE];
    private int playerX = MAP_SIZE - 1;
    private int playerY = 0;
    private Monster[][] monsters = new Monster[MAP_SIZE][MAP_SIZE];
    private int exitX;
    private int exitY;
    private Character player;

    public MapManager(Character player) {
        this.player = player;
        initializeMap();
    }

    private void initializeMap() {
        Random random = new Random();
        // Placer les monstres et la sortie
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                map[i][j] = ".";
                if (random.nextDouble() < 0.2 && !(i == MAP_SIZE - 1 && j == 0)) {
                    monsters[i][j] = new Monster();
                    map[i][j] = "M";
                }
            }
        }

        // Positionner la sortie (E) à une place aléatoire différente de la position du joueur
        do {
            exitX = random.nextInt(MAP_SIZE);
            exitY = random.nextInt(MAP_SIZE);
        } while (exitX == MAP_SIZE - 1 && exitY == 0); // S'assurer que la sortie n'est pas sur le joueur

        map[exitX][exitY] = "E";
        map[playerX][playerY] = "P"; // Position du joueur
    }

    public boolean movePlayer(Scanner scanner, CombatManager combatManager) {
        map[playerX][playerY] = ".";
        System.out.println("Se déplacer (Z/Q/S/D): ");
        char move = scanner.next().toUpperCase().charAt(0);
        switch (move) {
            case 'Z' -> { if (playerX > 0) playerX--; }
            case 'Q' -> { if (playerY > 0) playerY--; }
            case 'S' -> { if (playerX < MAP_SIZE - 1) playerX++; }
            case 'D' -> { if (playerY < MAP_SIZE - 1) playerY++; }
            default -> {
                System.out.println("Choix inconnu.");
                return true; // Continuer le jeu
            }
        }

        // Vérifie si le joueur atteint la sortie
        if (playerX == exitX && playerY == exitY) {
            System.out.println("Félicitations! Vous avez trouvé la sortie !");
            return false; // Fin du jeu
        }

        // Vérifie la présence de monstre
        if (monsters[playerX][playerY] != null) {
            System.out.println("Un monstre est sur votre chemin! Le combat commence !");
            combatManager.fight(player, monsters[playerX][playerY]);
            monsters[playerX][playerY] = null;
        }

        map[playerX][playerY] = "P";
        System.out.println("Déplacé à : (" + playerX + ", " + playerY + ")");
        return true; // Continuer le jeu
    }

    public void displayMap() {
        System.out.println("Carte :");
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
