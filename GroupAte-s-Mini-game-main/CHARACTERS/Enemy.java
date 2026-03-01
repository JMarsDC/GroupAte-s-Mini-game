package CHARACTERS;
import GAME_LOGIC.Voicelines;

public class Enemy extends Character implements Voicelines {
    
    private int Level;

     Enemy(String name, int health, int mana, int attackDMG, int Level){
        super(name,health,mana,attackDMG);
        this.Level = Level;
    }

// Drops Level so player gets more buffed
    public int giveLevel(){
        return Level;
    }

    public void checkIsAlive(){
        if(!isAlive()) deathCry();
        else encounter();
    }

    @Override
    public void deathCry(){
        System.out.println("NOOOO");
    }

    @Override
    public void encounter(){
        System.out.println("Hey There!");
    }

    public int getLevel(){ return Level; }
}
