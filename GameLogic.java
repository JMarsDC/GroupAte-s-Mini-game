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

        System.out.println("\nWelcome to Nilgarf, " + playerName + "!");
        System.out.println("You wield a " + weapon + " as your weapon.");

        gameLoop(player, sc);
    }
    public void gameLoop(Hero player, Scanner sc){
        boolean isRunning = true;

        if(!player.isAlive()) return;

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
                skelly.encounter();
                startCombat(player, skelly, sc);
            } else if(random.nextBoolean()){
                Goblin gobby = new Goblin();
                System.out.println("A mischievous Goblin leaps from the shadows!");
                gobby.encounter();
                startCombat(player, gobby, sc);
            } else{
                Zombie zomby = new Zombie();
                System.out.println("A creepy Zombie roars at you from behind!");
                zomby.encounter();
                startCombat(player, zomby, sc);
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
        

        while(inCombat) {
            System.out.println("--- Combat Started! ---");
            System.out.println("Your HP: " + player.getHealth() + "\nYour Mana: " + player.getMana());
            System.out.println("What will you do? ");
            System.out.println("1. Attack\n2. Use Potion\n3. Flee");
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
                        inCombat = false;
                        break;
                    } else {
                        enemyAttack(player, enemy);
                        inCombat = player.checkIsAlive();
                    }
                    break;
                case 2:
                    System.out.println("You used a potion! Recovering both your mana and hp!");
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

    public void bossBabajiCombat(Hero player, bossEnemy bossBabaji){

    }
}
