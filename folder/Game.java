public class Game extends Rental{
    public static final int EARLY_CHILDHOOD = 0;
    public static final int EVERYONE = 1;
    public static final int TEEN = 2;
    public static final int MATURE = 3;

    private String platform;
    private int numberOfPlayers;

    public Game(String title, double cost, int rating, String platform, int numberOfPlayers){
        super(title,cost,rating);
        this.platform = platform;
        this.numberOfPlayers = numberOfPlayers;
    }

    public String getPlatform(){
        return platform;
    }

    public int getNumberOfPlayers(){
        return numberOfPlayers;
    }

    public String toString(){
        String rating = "";
        if (getRating() == 0){
            rating = "Early Childhood";
        } else if (getRating() == 1){
            rating = "Everyone";
        } else if (getRating()== 2){
            rating = "Teen";
        } else if (getRating() == 3){
            rating = "Mature";
        }
        String line = String.format("%-20s%20s","Rating:",rating);
        String line2 = String.format("%-20s%20s","Platform:",platform);
        String line3 = String.format("%-20s%20s","# of players:",numberOfPlayers);
        return super.toString() + "\n" + line + "\n"+line2+"\n"+line3;
    }
}
