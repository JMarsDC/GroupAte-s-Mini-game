public class healthPotion extends Potion{
    private int healAmount;

    public healthPotion(int healAmount){
        super("Health Potion");
        this.healAmount = healAmount;
    }

    @Override
    public void applyEffect(Character character){
        character.increaseHealth(healAmount);
    }
}
