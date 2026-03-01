package CHARACTERS;
import GAME_LOGIC.Voicelines;

public class Hero extends Character implements Voicelines {
    
private int Level = 1;
private int maxMana = getMana();
private int maxHealth = getHealth();

    public Hero(String name, int health, int mana, int attackDMG){
        super(name,health,mana,attackDMG);
    }

    //NOTE: when using this, type cast it liek this -> "(CHARACTERS.Hero)CHARACTERS.Steve.checkIsAlive();"
    public boolean checkIsAlive(){
        if(!isAlive()){
            deathCry();
            return false;
        }
        else encounter();

        return true;
    }

// This dude gets the base attack from CHARACTERS.Character but will override so that
// his damage can be scaled using levels
    @Override
    public int getAttack(){
        return super.getAttack();
    }

    @Override
    public void deathCry(){
        System.out.println(super.getName() +": RAAGHH");
    }

    @Override
    public void encounter(){
        System.out.println(super.getName() + ": Hola espanyol?");
    }

    public int getLevel(){ return Level; }
    public void setLevel(int Level){ this.Level += Level;}
    public void recover(){
        if(getMana() >= maxMana && getHealth() >= maxHealth){
            System.out.println("Puno imo hp og mana cuhhhhhhhhhhhhhhhhhhhhhhhh");
        } else if(getMana() >= maxMana){
            System.out.println("Di ka maka recover og mana cuh pero here oh imo hp +50");
            increaseHealth(50);
        } else if(getHealth() >= maxHealth){
            System.out.println("Anga nimo uy kita kaayo ba puno imo hp, pero sige +50 lang imo mana");
            increaseMana(50);
        } else{
            System.out.println("Maka heal naka yipppeedabitydoooo");
            super.increaseHealth(50);
            super.increaseMana(50);
        }

    }
}
