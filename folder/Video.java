public class Video extends Rental{
    public static final int G = 0;
    public static final int PG = 1;
    public static final int PG13 = 2;
    public static final int R = 3;

    private String director;
    private int minutes;

    public Video(String title, double cost, int rating, String director, int minutes){
        super(title,cost,rating);
        this.director = director;
        this.minutes = minutes;
    }

    public String getDirector(){
        return director;
    }

    public int getMinutes(){
        return minutes;
    }

    public String toString(){
        String rating = "";
        if (getRating() == 0){
            rating = "G";
        } else if (getRating() == 1) {
            rating = "PG";
        } else if (getRating() == 2){
            rating = "PG-13";
        } else if (getRating() == 3){
            rating = "R";
        }
        String line = String.format("%-20s%20s","Rating:",rating);
        String line2 = String.format("%-20s%20s","Director:",director);
        String line3 = String.format("%-20s%20s","Runtime:",minutes+" minutes");
        return super.toString() + "\n" + line + "\n"+line2+"\n"+line3;
    }
}
