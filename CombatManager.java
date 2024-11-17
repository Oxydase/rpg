package dm.rpg;

public class CombatManager {

    public void fight(Character player, Monster monster) {
        if (player.getEquippedWeapon() == null) {
            System.out.println("Vous n'avez pas d'arme équipé. Vous ne pouvez pas vous battre");
            return;
        }

        while (monster.health > 0 && player.getHealth() > 0) {
            player.getEquippedWeapon().attack(monster);
            System.out.println("Vous attaquez le monstre ! PV du monstre: " + monster.health);

            if (monster.health <= 0) {
                System.out.println("Vous avez battu le monstre ! Vous gagnez 10XP");
                player.gainXp(10);
                break;
            }

            System.out.println("Le monstre vous attaque");
            player.takeDamage(5);
            System.out.println("Vos PV: " + player.getHealth());

            if (player.getHealth() <= 0) {
                System.out.println("Vous êtes mort sous les coups du monstre!");
                break;
            }
        }
    }
}
