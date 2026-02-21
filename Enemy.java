public class Enemy extends Character implements Voicelines{
    
    private float Level;

     Enemy(String name, int health, int mana, int attackDMG, float Level){
        super(name,health,mana,attackDMG);
        this.Level = Level;
    }

// Drops Level so player gets more buffed
    public float giveLevel(){
        return Level;
    }

    public void checkIsAlive(){
        if(!isAlive()) deathCry();
        else encounter();
    }

    @Override
    public void deathCry(){
        System.out.print("NOOOO");
    }

    @Override
    public void encounter(){
        System.out.print("Hey There!");
    }

}
