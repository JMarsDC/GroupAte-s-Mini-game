package ITEMS;

import CHARACTERS.Character;

public class HealthPotion extends Potion {

    public HealthPotion() { super("Health Potion"); }

    @Override
    public void applyEffect(Character character){
        int healAmount = 50;
        character.increaseHealth(healAmount);
    }
}
