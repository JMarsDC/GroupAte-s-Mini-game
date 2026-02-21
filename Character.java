public class Character{
    private int health;
    private int mana;
    private int attackDMG;
    private String name;

    Character(String name, int health, int mana, int attackDMG){
        this.name = name;
        this.health = health;
        this.mana = mana;
        this.attackDMG = attackDMG;
    }

    public String getName(){
        return name;
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

    public void increaseHealth(int amount){ health += amount; }

    public void increaseMana(int amount) { mana += amount; }

    public void increaseDMG(int amount) { attackDMG += amount; }

    public void usePotion(Potion potion) { potion.applyEffect(this); }

    public boolean isAlive(){
        return health>0;
    }

}