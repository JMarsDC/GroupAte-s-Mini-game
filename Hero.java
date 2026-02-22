public class Hero extends Character implements Voicelines{
    
private int Level = 1; //plus 0.1 guro IDK

    Hero(String name, int health, int mana, int attackDMG){
        super(name,health,mana,attackDMG);
    }


    public void addLevel(int Level){
        this.Level += Level;
    }

    //NOTE: when using this, type cast it liek this -> "(Hero)Steve.checkIsAlive();"
    public void checkIsAlive(){
        if(!isAlive()) deathCry();
        else encounter();
    }

// This dude gets the base attack from Character but will override so that 
// his damage can be scaled using levels
    @Override
    public int getAttack(){
        return super.getAttack()*Level;
    }

    @Override
    public void deathCry(){
        System.out.print("RAAGHH");
    }

    @Override
    public void encounter(){
        System.out.print("Hola espanyol?");
    }

    public void recover(){
        super.increaseHealth(50);
        super.increaseMana(50);
    }
}
