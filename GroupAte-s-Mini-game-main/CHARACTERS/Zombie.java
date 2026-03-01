package CHARACTERS;

public class Zombie extends Enemy {

    static int health = 100;
    static int mana = 999;
    static int attackDMG = 12;
    static int level = 2;
    
    public Zombie(){
        super("CHARACTERS.Zombie",health, mana, attackDMG, level);
    }
}
