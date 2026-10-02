import java.util.Random;

public class YellowZombieDie extends ZombieDie{
    public YellowZombieDie(){
        super(YELLOW);
    }

    public void roll(){
        Random roller = new Random();
        int roll = roller.nextInt(6)+1;
        if (roll == 1 || roll == 2){
            super.setValue(RUNNER);
        } else if (roll == 3 || roll == 4){
            super.setValue(BRAIN);
        } else{
            super.setValue(SHOT);
        }
    }

}
