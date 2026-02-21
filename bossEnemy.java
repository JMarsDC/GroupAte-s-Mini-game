public class bossEnemy extends Enemy implements Voicelines{

    private int phaseCount;

    public bossEnemy(String name, int health, int mana, int attackDMG, float Level) {
        super(name, health, mana, attackDMG, Level);
        this.phaseCount = phaseCount;
    }

    public void nextPhase(){
        if (phaseCount > 1){
            System.out.println(getName() + " enters phase " + phaseCount + "!");
            phaseCount--;
        } else
            System.out.println(getName() + " enters their final phase!");
    }

    @Override
    public void deathCry() { System.out.println("AGAY! *dies*"); }

    @Override
    public void encounter() { super.encounter(); }
}
