public class manaPotion extends Potion{

    manaPotion() { super("Mana Potion"); }

    @Override
    public void applyEffect(Character character) {
        int manaAmount = 50;
        character.increaseMana(manaAmount);
    }
}
