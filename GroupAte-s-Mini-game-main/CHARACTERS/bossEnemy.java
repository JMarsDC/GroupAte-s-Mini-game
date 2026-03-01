package CHARACTERS;
import GAME_LOGIC.Voicelines;

public class bossEnemy extends Enemy implements Voicelines {

    private int phaseCount = 2;
    static int health = 200;
    static int mana = 80;
    static int attackDMG = 20;
    static int level = 5;

    /*  CHARACTERS.bossEnemy(String name, int health, int mana, int attackDMG, float Level) {
        super(name, health, mana, attackDMG, Level);
        this.phaseCount = phaseCount;
    } */

    //gi tapol nakog add via gameLogic ari nalang nako ilisan type shi
    public bossEnemy(){ super("BOSSBABAJI", health, mana, attackDMG, level);}

    public void nextPhase() {
        if (phaseCount > 1) {
            System.out.println(getName() + " enters phase " + phaseCount + "!");
            increaseHealth(50);
            increaseMana(30);
            phaseCount--;
        } else{
            System.out.println(getName() + " enters their final phase!");
            increaseHealth(30);
            increaseMana(30);
            increaseDMG(20);
        }
    }

    @Override
    public void deathCry() { System.out.println("AGAY! *dies*"); }

    @Override
    public void encounter() { super.encounter(); }
}
