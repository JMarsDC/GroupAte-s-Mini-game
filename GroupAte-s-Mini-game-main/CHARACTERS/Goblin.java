package CHARACTERS;

public class Goblin extends Enemy {
    
    static int health = 110;
    static int mana = 999;
    static int attackDMG = 10;
    static int level = 2;
    
    public Goblin(){
        super("CHARACTERS.Goblin",health, mana, attackDMG, level);
    }
}
