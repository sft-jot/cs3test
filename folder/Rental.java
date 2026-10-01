public class Rental {
    private String title;
    private double cost;
    private int rating;
    private boolean rented;

    public Rental(String title, double cost, int rating){
        this.title = title;
        this.cost = cost;
        this.rating = rating;
        rented = false;
    }

    public String getTitle(){
        return title;
    }

    public double getCost(){
        return cost;
    }

    public int getRating(){
        return rating;
    }

    public boolean isRented(){
        return rented;
    }

    public void setRented(boolean rented){
        this.rented = rented;
    }

    public String toString(){
        String line = String.format("%-20s%20s","Title:",title);
        String line2 = String.format("%-20s%20s","Cost:",cost);
        return line+"\n"+line2;
    }
}
