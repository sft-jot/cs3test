public class ZombieDie {
    public static final int NOT_ROLLED = 0;
    public static final int RUNNER = 1;
    public static final int BRAIN = 2;
    public static final int SHOT = 3;
    public static final int RED = 1;
    public static final int GREEN = 2;
    public static final int YELLOW = 3;

    private int dieColor;
    private int value;

    public ZombieDie(int dieColor){
        this.dieColor = dieColor;
        value = NOT_ROLLED;
    }

    public int getValue(){
        return value;
    }

    public int getDieColor(){
        return dieColor;
    }

    public void setDieColor(int dieColor){
        this.dieColor = dieColor;
    }

    public void setValue(int value){
        this.value = value;
    }

    public String toString(){
        if (value == NOT_ROLLED){
            String[] returns = {"Red","Green","Yellow"};
            return returns[dieColor-1];
        } else {
            String[] returns = {"Red","Green","Yellow"};
            String[] vals = {"Runner","Brain","Shot"};
            String line = ""+returns[dieColor-1]+"-"+vals[value-1];
            return line;
        }
    }

    public void roll(){
        value = NOT_ROLLED;
    }
}
