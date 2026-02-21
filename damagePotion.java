public class damagePotion extends Potion{
    private int damageAmount;

    public damagePotion(int damageAmount, double dropRate){
        super("Damage Potion", dropRate);
        this.damageAmount = damageAmount;
    }

    @Override
    public void applyEffect(Character character) {
        character.increaseDMG(damageAmount);
    }
}
