public class Mage extends GameCharacter {
    private int spellShieldCost;
    private int spellShieldAbsorbAmount;

    public Mage(String characterName, int hitPoints, int magicPoints, int spellShieldCost, int spellShieldAbsorbAmount){
        super(characterName,hitPoints,magicPoints);
        this.spellShieldCost = spellShieldCost;
        this.spellShieldAbsorbAmount = spellShieldAbsorbAmount;
    }

    public int getSpellShieldCost(){
        return spellShieldCost;
    }

    public int getSpellShieldAbsorbAmount(){
        return spellShieldAbsorbAmount;
    }

    public void setSpellShieldCost(int spellShieldCost){
        this.spellShieldCost = spellShieldCost;
    }

    public void setSpellShieldAbsorbAmount(int spellShieldAbsorbAmount){
        this.spellShieldAbsorbAmount = spellShieldAbsorbAmount;
    }

    public void drinkManaPotion(int gain){
        super.setMagicPoints(super.getMagicPoints()+gain);
    }

    public void takeDamage(int damage){
        if (super.getMagicPoints() >= spellShieldCost){
            super.takeDamage(Math.max(0,damage-spellShieldAbsorbAmount));
            super.setMagicPoints(super.getMagicPoints()-spellShieldCost);
        } else {
            super.takeDamage(damage);
        }
    }

}
