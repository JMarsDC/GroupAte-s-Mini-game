public class Skeleton extends Enemy{

    static int health = 100;
    static int mana = 50;
    static int attackDMG = 15;
    static float level = 0.1f;
    
    Skeleton(){
        super("Skeleton",health, mana, attackDMG, level);
    }

}
