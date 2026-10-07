import java.awt.*;
import java.util.*;


public class ZombieDice_Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("How many players will be playing (2-5):");
        int players = sc.nextInt();
        sc.nextLine();
        System.out.println();
        String[] names = new String[players];
        int[] scores = new int[players];
        for (int i = 0; i < players; i++){
            System.out.println("Enter a player's name:");
            names[i] = sc.nextLine();
        }
        shuffleNames(names);
        ZombieDiceBucket bucket = new ZombieDiceBucket();
        boolean win = false;
        while (!win){
            for (int i = 0; i < players; i++){
                String person = names[i];
                int current = scores[i];
                System.out.println("\n"+person+" it is your turn and you have "+current+" brains in your bank");
                ArrayList<ZombieDie> hand = new ArrayList<>();
                ArrayList<ZombieDie> brains = new ArrayList<>();
                ArrayList<ZombieDie> shots = new ArrayList<>();
                ArrayList<ZombieDie> runners = new ArrayList<>();
                bucket.loadBucket();
                boolean turn = true;
                while (turn){
                    System.out.println("        Turn summary:");
                    System.out.println("                Brains:  "+brains);
                    System.out.println("                Shots:   "+shots);
                    System.out.println("                Runners: "+runners);
                    System.out.println("        1. Keep Going");
                    System.out.println("        2. Stop & add to bank");
                    System.out.println("        Enter selection:");
                    int pick = sc.nextInt();
                    if (pick == 2){
                        scores[i] += brains.size();
                        System.out.println("\n        You ate " + brains.size() + " brains this turn giving you " + scores[i] + " brains now in your bank.");
                        turn = false;
                        String winner = findWinner(names,scores);
                        if (winner!=null){
                            System.out.println(winner+" wins with "+scores[i]+" brains!");
                            win = true;
                        }
                    } else if (pick == 1){
                        hand.clear();
                        hand.addAll(runners);
                        runners.clear();
                        while (hand.size()<3){
                            ZombieDie already = bucket.draw();
                            if (already != null){
                                hand.add(already);
                            } else {
                                break;
                            }
                        }
                        for (int r = 0; r < hand.size(); r++){
                            hand.get(r).setValue(ZombieDie.NOT_ROLLED);
                        }
                        System.out.println("        After drawing you have the following dice: " + hand);
                        System.out.println("                Rolling...");
                        for (int r = 0; r < hand.size(); r++){
                            ZombieDie dice = hand.get(r);
                            dice.roll();
                            if (dice.getValue() == ZombieDie.BRAIN){
                                brains.add(dice);
                            }else if (dice.getValue() == ZombieDie.SHOT){
                                shots.add(dice);
                            } else if (dice.getValue() == ZombieDie.RUNNER){
                                runners.add(dice);
                            }
                        }
                        System.out.println("        The results of your rolls were: " + hand);
                        if (shots.size()>=3){
                            System.out.println("        Your were shot " + shots.size() + " times.");
                            System.out.println("        You lost all the brains you earned this turn.");
                            turn = false;
                        }
                    }
                }
                if (win){
                    break;
                }
            }
        }




    }


    public static void shuffleNames(String[] names){
        Collections.shuffle(Arrays.asList(names));
    }


    public static String findWinner(String[] names, int[] scores){
        for (int i = 0; i < scores.length; i++){
            if (scores[i]>=13){
                return names[i];
            }
        }
        return null;
    }




}

