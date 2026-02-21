abstract class Potion {
    private String name;
    private double dropRate;

    public Potion(String name, double dropRate){
        this.name = name;
        this.dropRate = dropRate;
    }

    public String getName() { return name; }
    public double getDropRate() { return dropRate; }

    public abstract void applyEffect(Character character);
}
