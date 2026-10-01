import java.util.ArrayList;

public class VideoStore {
    private ArrayList<Rental> rentals;

    public VideoStore(){
        rentals = new ArrayList<>();
        rentals.add(new Game("Assassin's Creed",4.99, 3, "Playstation 3", 1));
        rentals.add(new Game("Frogger Advanced",2.99, 0, "Game Boy Advanced", 1));
        rentals.add(new Game("Bomberman Land",2.99, 2, "Super NES", 4));

        rentals.add(new Video("Iron Man 3",3.99, 2, "Shawn Black", 130));
        rentals.add(new Video("The Wolverine",2.79, 3, "James Mangold", 126));
        rentals.add(new Video("The Avengers",1.99, 2, "Joss Whedon", 143));
    }

    public ArrayList<Game> getGames(){
        ArrayList<Game> answer = new ArrayList<>();
        for (int i = 0; i < rentals.size(); i++){
            if (rentals.get(i) instanceof Game){
                answer.add((Game) rentals.get(i));
            }
        }
        return answer;
    }

    public ArrayList<Video> getVideos(){
        ArrayList<Video> answer = new ArrayList<>();
        for (int i = 0; i < rentals.size(); i++){
            if (rentals.get(i) instanceof Video){
                answer.add((Video) rentals.get(i));
            }
        }
        return answer;
    }

    public ArrayList<Rental> rented(){
        ArrayList<Rental> answer = new ArrayList<>();
        for (int i = 0; i < rentals.size(); i++){
            if (rentals.get(i).isRented()){
                answer.add(rentals.get(i));
            }
        }
        return answer;
    }

    public ArrayList<Game> gamesInStock(){
        ArrayList<Game> answer = new ArrayList<>();
        for (int i = 0; i < rentals.size(); i++){
            if (rentals.get(i) instanceof Game && !rentals.get(i).isRented()){
                answer.add((Game) rentals.get(i));
            }
        }
        return answer;
    }

    public ArrayList<Video> videosInStock(){
        ArrayList<Video> answer = new ArrayList<>();
        for (int i = 0; i < rentals.size(); i++){
            if (rentals.get(i) instanceof Video && !rentals.get(i).isRented()){
                answer.add((Video) rentals.get(i));
            }
        }
        return answer;
    }


}
