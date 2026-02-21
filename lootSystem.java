public class lootSystem {
    public static Potion tryDrop(Potion potion){
        double roll = Math.random();
        if (roll < potion.getDropRate()) {
            return potion;
        }

        return null;
    }

}
