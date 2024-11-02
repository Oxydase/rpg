package dm.rpg;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        /*Axe axe = new Axe();
        System.out.println(axe.asciiArt());

        Hammer hammer = new Hammer();
        System.out.println(hammer.asciiArt());

        Bow bow = new Bow();
        System.out.println(bow.asciiArt());*/

        WeaponStore store = new WeaponStore();
        /*for (Weapon w : store.getWeapons()) {
            System.out.println(w.asciiArt());
        }*/
        store.printWeapons();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a name : ");
        String name = scanner.nextLine();
        System.out.println(name);

    }
}
