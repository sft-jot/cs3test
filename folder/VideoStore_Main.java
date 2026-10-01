import java.util.*;
import java.io.*;

public class VideoStore_Main {
    public static void main(String[] args) throws Exception {
        VideoStore stock = new VideoStore();
        Scanner sc = new Scanner(System.in);
        int rentCount = 0;
        int rentedGames = 0;
        int rentedVideos = 0;
        double subtotal = 0;
        double tax = 0;
        ArrayList<Video> listVideos = new ArrayList<>();
        ArrayList<Game> listGames = new ArrayList<>();
        while (true){
            rentingMenu();
            System.out.println();
            int selection = sc.nextInt();
            if (selection == 1){
                if (stock.videosInStock().isEmpty()){
                    System.out.println("There are no more videos in stock.");
                    System.out.println();
                } else {
                    printVideoMenu(stock);
                    int videoMenuChoice = sc.nextInt();
                    if (videoMenuChoice != 0 && !stock.videosInStock().isEmpty() && videoMenuChoice - 1 < stock.videosInStock().size()) {
                        System.out.println(stock.videosInStock().get(videoMenuChoice - 1).toString());
                        System.out.println();
                        System.out.println("1 - Rent, 2 - Put Back");
                        System.out.println("Enter selection:\n");
                        int doRent = sc.nextInt();
                        if (doRent == 1) {
                            rentCount++;
                            rentedVideos++;
                            subtotal += stock.videosInStock().get(videoMenuChoice - 1).getCost();
                            tax += stock.videosInStock().get(videoMenuChoice - 1).getCost() * 0.0825;
                            listVideos.add(stock.videosInStock().get(videoMenuChoice - 1));
                            stock.videosInStock().get(videoMenuChoice - 1).setRented(true);
                        }
                    }
                }



            } else if (selection == 2){
                if (stock.gamesInStock().isEmpty()){
                    System.out.println("There are no more games in stock.");
                    System.out.println();
                } else {
                    printGameMenu(stock);
                    int videoMenuChoice = sc.nextInt();
                    if (videoMenuChoice != 0 && !stock.gamesInStock().isEmpty() && videoMenuChoice - 1 < stock.gamesInStock().size()) {
                        System.out.println(stock.gamesInStock().get(videoMenuChoice - 1).toString());
                        System.out.println();
                        System.out.println("1 - Rent, 2 - Put Back");
                        System.out.println("Enter selection:\n");
                        int doRent = sc.nextInt();
                        if (doRent == 1) {
                            rentCount++;
                            rentedGames++;
                            subtotal += stock.gamesInStock().get(videoMenuChoice - 1).getCost();
                            tax += stock.gamesInStock().get(videoMenuChoice - 1).getCost() * 0.0825;
                            listGames.add(stock.gamesInStock().get(videoMenuChoice - 1));
                            stock.gamesInStock().get(videoMenuChoice - 1).setRented(true);
                        }
                    }
                }


            } else if (selection == 3){
                if (rentCount == 0){
                    System.out.println("You did not rent anything.");
                    System.out.println("Have a good day!");
                } else {
                    System.out.println("Receipt:");
                    if (rentedVideos>0){
                        System.out.println("\nRented Videos:");
                        for (int i = 0; i < rentedVideos; i++){
                            String line = String.format("    %-25s %10.2f",listVideos.get(i).getTitle(),listVideos.get(i).getCost());
                            System.out.println(line);
                        }

                    }
                    if (rentedGames>0){
                        System.out.println("\nRented Games:");
                        for (int i = 0; i < rentedGames; i++){
                            String line = String.format("    %-25s %10.2f",listGames.get(i).getTitle(),listGames.get(i).getCost());
                            System.out.println(line);
                        }
                    }
                    System.out.println();
                    String line = String.format("%-20s%20.2f","Subtotal:",subtotal);
                    String line2 = String.format("%-20s%20.2f","Tax:",tax);
                    String line3 = String.format("%-20s%20.2f","Total:",subtotal+tax);
                    System.out.println(line);
                    System.out.println(line2);
                    System.out.println(line3);
                }
                break;
            }
        }
    }

    public static void rentingMenu(){
        System.out.println("Renting Menu\n1. Rent Video\n2. Rent Game\n3. Checkout\nEnter selection:");
    }

    public static void printVideoMenu(VideoStore stock){
        String menu = "Video Menu";
        ArrayList<Video> videos = stock.videosInStock();
        for (int i = 0; i < videos.size(); i++){
            menu+="\n"+(i+1)+". "+videos.get(i).getTitle();
        }

        menu += "\n0. Cancel";
        menu+="\nEnter selection:\n";
        System.out.println(menu);
    }

    public static void printGameMenu(VideoStore stock){
        String menu = "Game Menu";
        ArrayList<Game> game = stock.gamesInStock();
        for (int i = 0; i < game.size(); i++){
            menu+="\n"+(i+1)+". "+game.get(i).getTitle();
        }

        menu += "\n0. Cancel";
        menu+="\nEnter selection:\n";
        System.out.println(menu);
    }

}
