package ITEMS;

import CHARACTERS.Character;

public class ManaPotion extends Potion {

    public ManaPotion() { super("Mana Potion"); }

    @Override
    public void applyEffect(Character character) {
        int manaAmount = 50;
        character.increaseMana(manaAmount);
    }
}
