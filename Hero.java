public class Hero extends Character implements Voicelines{
    
private int Level = 1;

    Hero(String name, int health, int mana, int attackDMG){
        super(name,health,mana,attackDMG);
    }

    //NOTE: when using this, type cast it liek this -> "(Hero)Steve.checkIsAlive();"
    public boolean checkIsAlive(){
        if(!isAlive()){
            deathCry();
            return false;
        }
        else encounter();

        return true;
    }

// This dude gets the base attack from Character but will override so that 
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
    public void recover(){
        super.increaseHealth(50);
        super.increaseMana(50);
    }
}
