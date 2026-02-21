abstract class Potion {
    private String name;

    public Potion(String name){
        this.name = name;
    }

    public String getName() { return name; }

    public abstract void applyEffect(Character character);
}
