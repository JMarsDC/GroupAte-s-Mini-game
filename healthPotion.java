public class healthPotion extends Potion{

    healthPotion() { super("Health Potion"); }

    @Override
    public void applyEffect(Character character){
        int healAmount = 50;
        character.increaseHealth(healAmount);
    }
}
