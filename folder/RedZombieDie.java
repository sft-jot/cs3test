import java.util.Random;

public class RedZombieDie extends ZombieDie{
    public RedZombieDie(){
        super(RED);
    }

    public void roll(){
        Random roller = new Random();
        int roll = roller.nextInt((6-1)+1)+1;
        if (roll == 1 || roll == 2){
            super.setValue(RUNNER);
        } else if (roll == 3){
            super.setValue(BRAIN);
        } else{
            super.setValue(SHOT);
        }
    }
}
