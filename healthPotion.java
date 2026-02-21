public class healthPotion extends Potion{
    private int healAmount;

    public healthPotion(int healAmount, double dropRate){
        super("Health Potion", dropRate);
        this.healAmount = healAmount;
    }

    @Override
    public void applyEffect(Character character){
        character.increaseHealth(healAmount);
    }
}
