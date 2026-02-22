import java.util.Random;
import java.util.Scanner;

public class GameLogic {

    private int totalEnemiesDefeated = 0;
    private int stepsTaken = 0;
    private int totalSteps = 0;
    private Random random = new Random();
    private String weapon = "";
    // chooses between 0-2 and adds 5 to ensure a boss encounter by the nth step
    private int bossEncounter = random.nextInt(3) + 5;
    public void GAMESTARTOOOOO() {

        Scanner sc = new Scanner(System.in);

        System.out.print("What is your name adventurer? ");
        String playerName = sc.nextLine();

        System.out.println("Choose class:");
        System.out.println("1. Swordsman");
        System.out.println("2. Ranger");
        System.out.println("3. Barbarian");
        System.out.println("4. Saitama (dakog tama)");

        int chc = sc.nextInt();

        Hero player = null;


        switch(chc){
            case 1:
                player = new Hero(playerName, 200, 85, 70);
                weapon = "sword";
                break;
            case 2:
                player = new Hero(playerName, 150, 85, 100);
                weapon = "bow";
                break;
            case 3:
                player = new Hero(playerName, 300, 50, 50);
                weapon = "war axe";
                break;
            case 4:
                player = new Hero(playerName, 9999999, 9999999, 9999999);
                weapon = "kumo";
                break;
        }

        System.out.println("\nWelcome to [placeholder], " + playerName + "!");
        System.out.println("You wield a " + weapon + " as your weapon.");

        gameLoop(player, sc);
    }
    public void gameLoop(Hero player, Scanner sc){
        boolean isRunning = true;

        while(isRunning){
            System.out.println("Where do you want to go now? ");
            System.out.println("1. Forward");
            System.out.println("2. Backward");
            System.out.println("3. Left");
            System.out.println("4. Right");
            System.out.println("5. Rest");
            System.out.println("6. Quit");

            int chc = sc.nextInt();
            switch(chc){
                case 1: move("forward", player, sc); break;
                case 2: move("backward", player, sc); break;
                case 3: move("leftward", player, sc); break;
                case 4: move("rightward", player, sc); break;
                case 5:
                    System.out.println("You choose to rest! You have restored some hp and mana.");
                    player.recover();
                    break;
                case 6:
                    System.out.println("You leave the adventure..");
                    quitMessage(player);
                    isRunning = false;
            }
        }
    }
    public void move(String direction, Hero player, Scanner sc){
        stepsTaken++;
        System.out.println("You moved " + direction + "...");
        checkEncounter(player, sc);
    }
    public void checkEncounter(Hero player, Scanner sc){
        int roll = random.nextInt(100);
        // Boss Encounter after certain steps
        if(stepsTaken >= bossEncounter && roll < 30){
            System.out.println("!!! A BOSS APPEARS !!!");
            resetBossEncounter();
            bossEnemy bossBabaji = new bossEnemy();
            bossBabajiCombat(player, bossBabaji);
            return;
        }

        if(roll < 50){
            System.out.println("An enemy appears!");
            if (random.nextBoolean()) {
                Skeleton skelly = new Skeleton();
                System.out.println("A bony Skeleton rattles toward you!");
                startCombat(player, skelly, sc);
            } else {
                Goblin gobby = new Goblin();
                System.out.println("A mischievous Goblin leaps from the shadows!");
                startCombat(player, gobby, sc);
            }
        } else if(roll < 100 && roll > 80){
            System.out.println("You found a potion!");
        } else{
            System.out.println("The path is quiet....");
        }
    }

    public void resetBossEncounter(){
        totalSteps += stepsTaken;
        stepsTaken = 0;
        bossEncounter = random.nextInt(3) + 5;
    }

    public void startCombat(Hero player, Enemy enemy,Scanner sc){
        boolean inCombat = true;
        System.out.println("--- Combat Started! ---");

        while(inCombat) {
            System.out.println("Your HP: " + player.getHealth() + "\nYour Mana: " + player.getMana());
            System.out.println("What will you do? ");
            System.out.println("1. Attack\n2. Use Potion\n3. Flee");
            int chc = sc.nextInt();

            switch (chc) {
                case 1:
                    System.out.println("You strike the enemy with your " + weapon);
                    enemy.takeDamage(player.getAttack());
                    System.out.println(enemy.getName() + " takes " + player.getAttack() + " damage!");
                    if (!enemy.isAlive()) {
                        System.out.println("You have defeated the enemy!");
                        System.out.println("You have gained a level!");
                        totalEnemiesDefeated++;
                        inCombat = false;
                        break;
                    } else {
                        System.out.println(enemy.getName() + " attacks you for " + enemy.getAttack() + "!");
                        player.takeDamage(enemy.getAttack());
                    }
                    break;
                case 2:
                    System.out.println("You used a potion! Recovering both your mana and hp!");
                    System.out.println(enemy.getName() + " attacks you for " + enemy.getAttack() + "!");
                    player.takeDamage(enemy.getAttack());
                    break;
                case 3:
                    System.out.println("You run!");
                    System.out.println("\n--- Combat Ended ---");
                    inCombat = false;
                    break;
            }
        }
    }
    public void quitMessage(Hero player){
        System.out.println("--- Adventure Ended ---");
        System.out.println("      Statistics");
        System.out.println("Player Name: " + player.getName());
        System.out.println("Total Steps Taken: " + totalSteps);
        System.out.println("Player Level: " + player.getLevel());
        System.out.println("Enemies Defeated: " + totalEnemiesDefeated);
        System.out.println("\nThank you for playing this experience!");
    }

    public void bossBabajiCombat(Hero player, bossEnemy bossBabaji){

    }
}
