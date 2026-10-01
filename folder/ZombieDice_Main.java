import java.util.*;

public class ZombieDice_Main {
    public static void main(String[] args){
        // variables
        String[] names;
        int[] scores;
        ArrayList<ZombieDie> hand;
        ArrayList<ZombieDie> brains;
        ArrayList<ZombieDie> shots;
        ArrayList<ZombieDie> runners;

        Scanner sc = new Scanner(System.in);
        // players
        System.out.println("How many players will be playing (2-5): ");
        int players = sc.nextInt();
        System.out.println();
        names = new String[players];
        int[] nameVals = new int[players];
        sc.nextLine();
        ZombieDie[][] data = new ZombieDie[3][3];
        ZombieDiceBucket bucket = new ZombieDiceBucket();
        for (int i = 0; i < players; i++){
            System.out.println("Enter a player's name:");
            String name = sc.nextLine();
            names[i] = name;
        }
        System.out.println();
        while (true){
            printStats(,"Sifat",0);

        }


    }

    public static void shuffleNames(String[] names){
        Collections.shuffle(Arrays.asList(names));
    }

    public static String findWinner(String[] names, int[] scores){
        return null;
    }

    public static void printStats(int value, String name,int index, ZombieDie[][] data){
       System.out.println(name + " it is your turn and you have "+value+"brains in your bank.\n" +
               "\n" +
               "        Turn summary:\n" +
               "                Brains:   " +Arrays.toString(new ZombieDie[]{data[index][0]})+
               "\n                Shots:   " + Arrays.toString(new ZombieDie[]{data[index][1]})+
               "\n                Runners: " + Arrays.toString(new ZombieDie[]{data[index][2]})+
               "\n        1. Keep Going\n" +
               "        2. Stop & add to bank\n" +
               "        Enter selection:");
    }

    public static void rollStats(int value, String name, int index, ZombieDie[][] data, ZombieDiceBucket bucket){
        ZombieDie[] color = new ZombieDie[3];
        color[0] = bucket.draw();
        color[1] = bucket.draw();
        color[2] = bucket.draw();

        System.out.println("        After drawing you have the following dice: "+Arrays.toString(color) + "\n" +
                "                Rolling...\n" +
                "        The results of your rolls were: [Red-Shot, Green-Runner, Red-Runner]\n" +
                "        Turn summary:\n" +
                "                Brains:  []\n" +
                "                Shots:   [Red-Shot]\n" +
                "                Runners: [Green-Runner, Red-Runner]\n" +
                "        1. Keep Going\n" +
                "        2. Stop & add to bank\n" +
                "        Enter selection:");
    }
}
