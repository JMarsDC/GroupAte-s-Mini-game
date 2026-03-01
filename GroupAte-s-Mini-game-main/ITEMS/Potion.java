package ITEMS;

import CHARACTERS.Character;

public abstract class Potion {
    private String name;

    public Potion(String name){
        this.name = name;
    }

    public String getName() { return name; }
    // public double getDropRate() { return dropRate; }

    public abstract void applyEffect(Character character);
}
