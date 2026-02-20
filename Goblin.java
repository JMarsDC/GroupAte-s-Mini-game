public class Goblin extends Enemy{
    
    static int health = 100;
    static int mana = 50;
    static int attackDMG = 10;
    static float level = 0.1f;
    
    Goblin(){
        super("Goblin",health, mana, attackDMG, level);
    }
}
