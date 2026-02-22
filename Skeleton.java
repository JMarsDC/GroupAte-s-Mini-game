public class Skeleton extends Enemy{

    static int health = 90;
    static int mana = 999;
    static int attackDMG = 15;
    static int level = 3;
    
    Skeleton(){
        super("Skeleton",health, mana, attackDMG, level);
    }

}
