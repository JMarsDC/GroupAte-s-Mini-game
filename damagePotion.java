public class damagePotion extends Potion{
    private int damageAmount;

    public damagePotion(int damageAmount){
        super("Damage Potion");
        this.damageAmount = damageAmount;
    }

    @Override
    public void applyEffect(Character character) {
        character.increaseDMG(damageAmount);
    }
}
