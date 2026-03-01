package GAME_LOGIC;

import CHARACTERS.*;
import ITEMS.HealthPotion;
import ITEMS.ManaPotion;

import java.util.Random;
import java.util.Scanner;

public class GameLogic {

    private int totalEnemiesDefeated = 0;
    private int stepsTaken = 0;
    private int totalSteps = 0;
    private Random random = new Random();
    private String weapon = "";
    private ManaPotion manaPot = new ManaPotion();
    private HealthPotion healthPot = new HealthPotion();
    // index at 0 is mana index at 1 is hp
    private final int potionBag[] = new int[2];
    // chooses between 0-2 and adds 5 to ensure a boss encounter by the nth step
    private int bossEncounter = random.nextInt(3) + 5;
    public void GAMESTARTOOOOO() {

        Scanner sc = new Scanner(System.in);

        System.out.println("What is your name adventurer? ");
        System.out.print("Input: ");
        String playerName = sc.nextLine();

        System.out.println("Choose class:");
        System.out.println("1. Swordsman");
        System.out.println("2. Ranger");
        System.out.println("3. Barbarian");
        System.out.println("4. Saitama (dakog tama)");
        System.out.print("Input: "); // new line
        int chc = sc.nextInt();

        Hero player = null;


        switch(chc){
            case 1:
                player = new Hero(playerName, 200, 40, 18);
                weapon = "sword";
                break;
            case 2:
                player = new Hero(playerName, 180, 60, 15);
                weapon = "bow";
                break;
            case 3:
                player = new Hero(playerName, 250, 30, 22);
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

        if(!player.isAlive()) return;

        while(isRunning){
            System.out.println("Where do you want to go now? ");
            System.out.println("1. Forward\t 4. Right");
            System.out.println("2. Backward\t 5. Rest");
            System.out.println("3. Left\t     6. Quit");
            System.out.print("Input: "); // new line
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
            bossBabajiCombat(player, bossBabaji, sc);
            return;
        }

        // roll chances to make sure that something happens in between steps
        // 50/50 to meet an enemy 20% chance to see a potion and a 30% chance to not find anything at all
        if(roll < 50){
            System.out.println("An enemy appears!");
            if (random.nextBoolean()) {
                Skeleton skelly = new Skeleton();
                System.out.println("A bony CHARACTERS.Skeleton rattles toward you!");
                skelly.encounter();
                startCombat(player, skelly, sc);
            } else if(random.nextBoolean()){
                Goblin gobby = new Goblin();
                System.out.println("A mischievous CHARACTERS.Goblin leaps from the shadows!");
                gobby.encounter();
                startCombat(player, gobby, sc);
            } else{
                Zombie zomby = new Zombie();
                System.out.println("A creepy CHARACTERS.Zombie roars at you from behind!");
                zomby.encounter();
                startCombat(player, zomby, sc);
            }
        } else if(roll <= 100 && roll >= 80){
            System.out.println("You found a potion!");
            int fiftyfiftytypeshi = random.nextInt(100);
            if(fiftyfiftytypeshi % 2 == 0){
                System.out.println("kita kag mana potion cuh");
                potionBag[0]++;
            } else {
                System.out.println("kita kag hp potion cuh");
                potionBag[1]++;
            }
        } else{
            System.out.println("The path is quiet....");
        }
    }

    public void resetBossEncounter(){
        totalSteps += stepsTaken;
        stepsTaken = 0;
        bossEncounter = random.nextInt(3) + 5;
    }

    public void startCombat(Hero player, Enemy enemy, Scanner sc){
        boolean inCombat = true;

        System.out.println("\n--- Combat Started! ---");

        while(inCombat) {
            System.out.println("Your HP: " + player.getHealth() + "\t\tYour Mana: " + player.getMana());
            System.out.println(enemy.getName() + " Health: " + enemy.getHealth()); // new line

            System.out.println("What will you do? ");
            System.out.println("1. Attack\n2. Use Potion\n3. Flee");
            System.out.print("Input: "); // new line
            int chc = sc.nextInt();

            switch (chc) {
                case 1:
                if(player.getMana()<=0){
                    System.out.println("You don't have enough mana to fight!");
                        enemyAttack(player, enemy);
                        inCombat = player.checkIsAlive();
                    break;
                }
                    playerAttack(player, enemy);
                    if (!enemy.isAlive()) {
                        enemy.deathCry();
                        System.out.println("You have gained a level!");
                        player.increaseDMG(enemy.giveLevel());
                        totalEnemiesDefeated++;
                        player.setLevel(enemy.getLevel());
                        inCombat = false;
                        break;
                    } else {
                        enemyAttack(player, enemy);
                        inCombat = player.checkIsAlive();
                    }
                    break;
                case 2:
                    potionUsage(player, sc);
                    //potion function here
                    enemyAttack(player, enemy);
                        inCombat = player.checkIsAlive();
                    break;
                case 3:
                    System.out.println("You run!");
                    System.out.println("\n--- Combat Ended ---");
                    inCombat = false;
                    break;
            }
        }
    }

    public void enemyAttack(Hero player, Enemy enemy){
        System.out.println(enemy.getName() + " attacks you for " + enemy.getAttack() + "!");
        player.takeDamage(enemy.getAttack());
    }

    public void playerAttack(Hero player, Enemy enemy){
        System.out.println("You strike the enemy with your " + weapon);
        player.reduceMana(10);
        enemy.takeDamage(player.getAttack());
        System.out.println(enemy.getName() + " takes " + player.getAttack() + " damage!");
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

    public void bossBabajiCombat(Hero player, bossEnemy bossBabaji, Scanner sc) {
        boolean inCombat = true;

        while (inCombat) {
            System.out.println("\n--- COMBAT WITH BOSSBABAJI ---");
            System.out.println("Your HP: " + player.getHealth() + " | Your Mana: " + player.getMana());
            System.out.println(bossBabaji.getName() + " Health: " + bossBabaji.getHealth());

            System.out.println("What will you do? ");
            System.out.println("1. Attack\n2. Use Potion\n3. Flee");
            System.out.print("Input: ");

            int chc = 0;
            try {
                // Use nextInt() but consume the leftover newline immediately
                chc = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Palihog pag butang og numero cuh!");
                sc.nextLine(); // Clear the bad input
                continue;
            }

            switch (chc) {
                case 1:
                    if (player.getMana() <= 0) {
                        System.out.println("You're out of mana cuh! You lose your turn.");
                        enemyAttack(player, bossBabaji);
                    } else {
                        playerAttack(player, bossBabaji);

                        // Check if Boss died
                        if (!bossBabaji.isAlive()) {
                            bossBabaji.deathCry();
                            System.out.println("Imo na gi pildi ang pinakaisog cuh!");
                            System.out.println("Naka gain napud kag level type shiii +5 naka dong");
                            player.increaseDMG(bossBabaji.giveLevel());
                            totalEnemiesDefeated++;
                            player.setLevel(bossBabaji.getLevel());
                            inCombat = false;
                            return; // Exit the method and go back to the game map
                        }

                        // If boss is alive, it hits back
                        enemyAttack(player, bossBabaji);
                    }
                    break;

                case 2:
                    System.out.println("Nigamit ka og potion cuh!");
                    // Logic for potion (example: player.heal())
                    enemyAttack(player, bossBabaji);
                    break;

                case 3:
                    System.out.println("You cannot run away from the bossbabajiiiiiiiiiii!");
                    System.out.println("Babaji hits you while you try to run!");
                    enemyAttack(player, bossBabaji);
                    break;

                default:
                    System.out.println("Invalid input! You lose a turn!");
                    enemyAttack(player, bossBabaji);
                    break;
            }

            // --- THE "GAME OVER" EXIT ---
            // We check health at the end of every turn
            if (!player.checkIsAlive() || player.getHealth() <= 0) {
                System.out.println("\n================================");
                System.out.println("         GAME OVER, CUH!        ");
                System.out.println("Boss Babaji cooked you into rice.");
                System.out.println("================================");

                // This command shuts down the entire program immediately
                System.exit(0);
            }
        }
    }

    public void potionUsage(Hero player, Scanner sc){
        System.out.println("Potion Bag:");
        System.out.println("1. Mana Potion [" + potionBag[0] + "]");
        System.out.println("2. Health Potion [" + potionBag[1] + "]");
        System.out.print("Input: "); // new line
        int chc = sc.nextInt();

        if(chc == 0){
            System.out.println("Closing potion bag...");
            return;
        }

        if(chc == 1){
            if(isPotionBagEmpty(0)){
                System.out.println("Potion Bag is out of Mana Potions!");
                potionBag[0] = 0;
            } else{
                System.out.println("You used a Mana Potion!");
                System.out.println("You only have " + potionBag[0] + " Mana Potions left!");
                manaPot.applyEffect(player);
            }

        } else if(chc == 2){
            if(isPotionBagEmpty(1)){
                System.out.println("Potion Bag is out of Health Potions!");
                potionBag[1] = 0;
            } else{
                System.out.println("You used a Health Potion");
                System.out.println("You only have " + potionBag[1] + " Health Potions left!");
                healthPot.applyEffect(player);
            }
        }
    }

    public boolean isPotionBagEmpty(int index){
        potionBag[index]--;
        return potionBag[index] < 0;
    }
}
