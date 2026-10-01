public class Thief extends GameCharacter{
    private int stealthPoints;
    private int inventoryWeight;

    public Thief(String characterName, int hitPoints, int stealthPoints, int inventoryWeight){
        super(characterName,hitPoints,0);
        this.stealthPoints = stealthPoints;
        this.inventoryWeight = inventoryWeight;
    }

    public int getStealthPoints(){
        return stealthPoints;
    }

    public int getInventoryWeight(){
        return inventoryWeight;
    }

    public void setStealthPoints(int stealthPoints){
        this.stealthPoints = stealthPoints;
    }

    public void setInventoryWeight(int inventoryWeight){
        this.inventoryWeight = inventoryWeight;
    }

    public int stealthAbility(){
        return Math.max(0,(stealthPoints-(inventoryWeight/2)));
    }

    public void takeDamage(int damage){
        damage = Math.max(0,damage-stealthAbility());
        super.takeDamage(damage);
    }
}
