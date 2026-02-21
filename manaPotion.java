public class manaPotion extends Potion{
    private int manaAmount;

    public manaPotion(int manaAmount, double dropRate){
        super("Mana Potion", dropRate);
        this.manaAmount = manaAmount;
    }

    @Override
    public void applyEffect(Character character) {
        character.increaseMana(manaAmount);
    }
}
