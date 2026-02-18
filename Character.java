public class Character{
    private int health;
    private int mana;
    private int attackDMG; //this shi is randomized

    Character(int health, int mana, int attackDMG){
        this.health = health;
        this.mana = mana;
        this.attackDMG = attackDMG;
    }

    void takeDamage(int damage){
        health -= damage;
    }

    public int getHealth(){
        return health;
    }

    public int getMana(){
        return mana;
    }

    public int getAttack(){
        return attackDMG;
    }

    public boolean isAlive(){
        return health>0;
    }

}