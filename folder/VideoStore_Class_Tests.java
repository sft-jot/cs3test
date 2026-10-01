import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class VideoStore_Class_Tests
{
    public static class Scores
    {
        public static LinkedHashMap<String, double[]> grading = new LinkedHashMap<String, double[]>();

        static
        {
            grading.put("RentalConstructor", new double[]{1.5, 0.0, 0.0});
            grading.put("RentalGetTitle", new double[]{1.5, 0.0, 0.0});
            grading.put("RentalGetCost", new double[]{1.5, 0.0, 0.0});
            grading.put("RentalGetRating", new double[]{1.5, 0.0, 0.0});
            grading.put("RentalIsRented", new double[]{1.5, 0.0, 0.0});
            grading.put("RentalSetRented", new double[]{2, 0.0, 0.0});
            grading.put("VideoConstructor", new double[]{2, 0.0, 0.0});
            grading.put("VideoGetDirector", new double[]{1.5, 0.0, 0.0});
            grading.put("VideoGetMinutes", new double[]{1.5, 0.0, 0.0});
            grading.put("GameConstructor", new double[]{1.5, 0.0, 0.0});
            grading.put("GameGetPlatform", new double[]{1.5, 0.0, 0.0});
            grading.put("GameGetNumberOfPlayers", new double[]{1.5, 0.0, 0.0});
            grading.put("VideoStoreConstructor", new double[]{2, 0.0, 0.0});
            grading.put("VideoStoreGetGames", new double[]{1.5, 0.0, 0.0});
            grading.put("VideoStoreGetVideos", new double[]{1.5, 0.0, 0.0});
            grading.put("VideoStoreGamesInStock", new double[]{2, 0.0, 0.0});
            grading.put("VideoStoreVideosInStock", new double[]{2, 0.0, 0.0});
            grading.put("VideoStoreRented", new double[]{2, 0.0, 0.0});
        }
    }

    public String generateClassName(String name)
    {
        if(getClass().toString().contains("."))
        {
            return getClass().toString().substring(6, getClass().toString().lastIndexOf(".") + 1) + name;
        }

        return name;
    }

    public void recordTotal(String category)
    {
        Scores.grading.get(category)[2]++;
    }

    public void recordPass(String category)
    {
        Scores.grading.get(category)[1]++;
    }

    private Object buildRental(String title, double cost, int rating) throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("Rental"));
        Object rental = classRef.getConstructor(String.class, double.class, int.class).newInstance(title, cost, rating);

        return rental;
    }

    private Object buildVideo(String title, double cost, int rating, String director, int minutes) throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("Video"));
        Object video = classRef.getConstructor(String.class, double.class, int.class, String.class, int.class).newInstance(title, cost, rating, director, minutes);

        return video;
    }

    private Object buildGame(String title, double cost, int rating, String platform, int numberOfPlayers) throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("Game"));
        Object game = classRef.getConstructor(String.class, double.class, int.class, String.class, int.class).newInstance(title, cost, rating, platform, numberOfPlayers);

        return game;
    }

    private Object buildStore() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("VideoStore"));
        Object store = classRef.getConstructor().newInstance();

        return store;
    }

    private Object call(Object object, String methodName) throws Exception
    {
        Method method = object.getClass().getMethod(methodName);

        try
        {
            return method.invoke(object);
        }
        catch(InvocationTargetException e)
        {
            throw (Exception) e.getCause();
        }
    }

    private void callSetRented(Object object, boolean value) throws Exception
    {
        Method method = object.getClass().getMethod("setRented", boolean.class);

        try
        {
            method.invoke(object, value);
        }
        catch(InvocationTargetException e)
        {
            throw (Exception) e.getCause();
        }
    }

    private int sizeOfList(Object list)
    {
        return ((ArrayList<?>) list).size();
    }

    private Object listItem(Object list, int index)
    {
        return ((ArrayList<?>) list).get(index);
    }

    private void assertRental(Object rental, String title, double cost, int rating) throws Exception
    {
        Assert.assertEquals(title, call(rental, "getTitle"));
        Assert.assertEquals(cost, (Double) call(rental, "getCost"), 0.001);
        Assert.assertEquals(rating, call(rental, "getRating"));
    }

    private void assertVideo(Object video, String title, double cost, int rating, String director, int minutes) throws Exception
    {
        Assert.assertTrue(video instanceof Rental);
        assertRental(video, title, cost, rating);
        Assert.assertEquals(director, call(video, "getDirector"));
        Assert.assertEquals(minutes, call(video, "getMinutes"));
    }

    private void assertGame(Object game, String title, double cost, int rating, String platform, int players) throws Exception
    {
        Assert.assertTrue(game instanceof Rental);
        assertRental(game, title, cost, rating);
        Assert.assertEquals(platform, call(game, "getPlatform"));
        Assert.assertEquals(players, call(game, "getNumberOfPlayers"));
    }

    @Test(timeout = 250)
    public void RentalConstructor1() throws Exception
    {
        String category = "RentalConstructor";
        recordTotal(category);
        Object rental = buildRental("Title", 14.98, 4);
        assertRental(rental, "Title", 14.98, 4);
        Assert.assertEquals(false, call(rental, "isRented"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void RentalGetTitle1() throws Exception
    {
        String category = "RentalGetTitle";
        recordTotal(category);
        Object rental = buildRental("Title", 14.98, 4);
        Assert.assertEquals("Title", call(rental, "getTitle"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void RentalGetCost1() throws Exception
    {
        String category = "RentalGetCost";
        recordTotal(category);
        Object rental = buildRental("Title", 14.98, 4);
        Assert.assertEquals(14.98, (Double) call(rental, "getCost"), 0.001);
        recordPass(category);
    }

    @Test(timeout = 250)
    public void RentalGetRating1() throws Exception
    {
        String category = "RentalGetRating";
        recordTotal(category);
        Object rental = buildRental("Title", 14.98, 4);
        Assert.assertEquals(4, call(rental, "getRating"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void RentalConstructor2() throws Exception
    {
        String category = "RentalConstructor";
        recordTotal(category);
        Object rental = buildRental("Game 2", 3.99, 2);
        assertRental(rental, "Game 2", 3.99, 2);
        Assert.assertEquals(false, call(rental, "isRented"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void RentalGetTitle2() throws Exception
    {
        String category = "RentalGetTitle";
        recordTotal(category);
        Object rental = buildRental("Game 2", 3.99, 2);
        Assert.assertEquals("Game 2", call(rental, "getTitle"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void RentalGetCost2() throws Exception
    {
        String category = "RentalGetCost";
        recordTotal(category);
        Object rental = buildRental("Game 2", 3.99, 2);
        Assert.assertEquals(3.99, (Double) call(rental, "getCost"), 0.001);
        recordPass(category);
    }

    @Test(timeout = 250)
    public void RentalGetRating2() throws Exception
    {
        String category = "RentalGetRating";
        recordTotal(category);
        Object rental = buildRental("Game 2", 3.99, 2);
        Assert.assertEquals(2, call(rental, "getRating"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void RentalConstructor3() throws Exception
    {
        String category = "RentalConstructor";
        recordTotal(category);
        Object rental = buildRental("Vid", 1.63, 1);
        assertRental(rental, "Vid", 1.63, 1);
        Assert.assertEquals(false, call(rental, "isRented"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void RentalGetTitle3() throws Exception
    {
        String category = "RentalGetTitle";
        recordTotal(category);
        Object rental = buildRental("Vid", 1.63, 1);
        Assert.assertEquals("Vid", call(rental, "getTitle"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void RentalGetCost3() throws Exception
    {
        String category = "RentalGetCost";
        recordTotal(category);
        Object rental = buildRental("Vid", 1.63, 1);
        Assert.assertEquals(1.63, (Double) call(rental, "getCost"), 0.001);
        recordPass(category);
    }

    @Test(timeout = 250)
    public void RentalGetRating3() throws Exception
    {
        String category = "RentalGetRating";
        recordTotal(category);
        Object rental = buildRental("Vid", 1.63, 1);
        Assert.assertEquals(1, call(rental, "getRating"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void RentalConstructor4() throws Exception
    {
        String category = "RentalConstructor";
        recordTotal(category);
        Object rental = buildRental("Rental A", 0.99, 0);
        assertRental(rental, "Rental A", 0.99, 0);
        Assert.assertEquals(false, call(rental, "isRented"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void RentalGetTitle4() throws Exception
    {
        String category = "RentalGetTitle";
        recordTotal(category);
        Object rental = buildRental("Rental A", 0.99, 0);
        Assert.assertEquals("Rental A", call(rental, "getTitle"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void RentalGetCost4() throws Exception
    {
        String category = "RentalGetCost";
        recordTotal(category);
        Object rental = buildRental("Rental A", 0.99, 0);
        Assert.assertEquals(0.99, (Double) call(rental, "getCost"), 0.001);
        recordPass(category);
    }

    @Test(timeout = 250)
    public void RentalGetRating4() throws Exception
    {
        String category = "RentalGetRating";
        recordTotal(category);
        Object rental = buildRental("Rental A", 0.99, 0);
        Assert.assertEquals(0, call(rental, "getRating"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void RentalConstructor5() throws Exception
    {
        String category = "RentalConstructor";
        recordTotal(category);
        Object rental = buildRental("Rental B", 22.22, 3);
        assertRental(rental, "Rental B", 22.22, 3);
        Assert.assertEquals(false, call(rental, "isRented"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void RentalGetTitle5() throws Exception
    {
        String category = "RentalGetTitle";
        recordTotal(category);
        Object rental = buildRental("Rental B", 22.22, 3);
        Assert.assertEquals("Rental B", call(rental, "getTitle"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void RentalGetCost5() throws Exception
    {
        String category = "RentalGetCost";
        recordTotal(category);
        Object rental = buildRental("Rental B", 22.22, 3);
        Assert.assertEquals(22.22, (Double) call(rental, "getCost"), 0.001);
        recordPass(category);
    }

    @Test(timeout = 250)
    public void RentalGetRating5() throws Exception
    {
        String category = "RentalGetRating";
        recordTotal(category);
        Object rental = buildRental("Rental B", 22.22, 3);
        Assert.assertEquals(3, call(rental, "getRating"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void RentalIsRented1() throws Exception
    {
        String category = "RentalIsRented";
        recordTotal(category);
        Object rental = buildRental("Title", 14.98, 4);
        Assert.assertEquals(false, call(rental, "isRented"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void RentalSetRented1() throws Exception
    {
        String category = "RentalSetRented";
        recordTotal(category);
        Object rental = buildRental("Title", 14.98, 4);
        callSetRented(rental, true);
        Assert.assertEquals(true, call(rental, "isRented"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void RentalIsRented2() throws Exception
    {
        String category = "RentalIsRented";
        recordTotal(category);
        Object rental = buildVideo("Video A", 2.22, 4, "Test Dir", 1);
        Assert.assertEquals(false, call(rental, "isRented"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void RentalSetRented2() throws Exception
    {
        String category = "RentalSetRented";
        recordTotal(category);
        Object rental = buildVideo("Video A", 2.22, 4, "Test Dir", 1);
        callSetRented(rental, true);
        callSetRented(rental, false);
        Assert.assertEquals(false, call(rental, "isRented"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void RentalIsRented3() throws Exception
    {
        String category = "RentalIsRented";
        recordTotal(category);
        Object rental = buildGame("Fall 17", 17.11, 2, "XBox", 2);
        Assert.assertEquals(false, call(rental, "isRented"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void RentalSetRented3() throws Exception
    {
        String category = "RentalSetRented";
        recordTotal(category);
        Object rental = buildGame("Fall 17", 17.11, 2, "XBox", 2);
        callSetRented(rental, true);
        Assert.assertEquals(true, call(rental, "isRented"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void RentalIsRented4() throws Exception
    {
        String category = "RentalIsRented";
        recordTotal(category);
        Object rental = buildRental("Rental C", 5.55, 0);
        Assert.assertEquals(false, call(rental, "isRented"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void RentalSetRented4() throws Exception
    {
        String category = "RentalSetRented";
        recordTotal(category);
        Object rental = buildRental("Rental C", 5.55, 0);
        callSetRented(rental, true);
        callSetRented(rental, false);
        Assert.assertEquals(false, call(rental, "isRented"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void RentalIsRented5() throws Exception
    {
        String category = "RentalIsRented";
        recordTotal(category);
        Object rental = buildGame("Game C", 4.50, 1, "PC", 2);
        Assert.assertEquals(false, call(rental, "isRented"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void RentalSetRented5() throws Exception
    {
        String category = "RentalSetRented";
        recordTotal(category);
        Object rental = buildGame("Game C", 4.50, 1, "PC", 2);
        callSetRented(rental, true);
        Assert.assertEquals(true, call(rental, "isRented"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoConstructor1() throws Exception
    {
        String category = "VideoConstructor";
        recordTotal(category);
        Object video = buildVideo("Video 1", 7.98, 0, "Jack Smith", 73);
        assertVideo(video, "Video 1", 7.98, 0, "Jack Smith", 73);
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoGetDirector1() throws Exception
    {
        String category = "VideoGetDirector";
        recordTotal(category);
        Object video = buildVideo("Video 1", 7.98, 0, "Jack Smith", 73);
        Assert.assertEquals("Jack Smith", call(video, "getDirector"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoGetMinutes1() throws Exception
    {
        String category = "VideoGetMinutes";
        recordTotal(category);
        Object video = buildVideo("Video 1", 7.98, 0, "Jack Smith", 73);
        Assert.assertEquals(73, call(video, "getMinutes"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoConstructor2() throws Exception
    {
        String category = "VideoConstructor";
        recordTotal(category);
        Object video = buildVideo("Video 2", 3.33, 1, "Targon", 89);
        assertVideo(video, "Video 2", 3.33, 1, "Targon", 89);
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoGetDirector2() throws Exception
    {
        String category = "VideoGetDirector";
        recordTotal(category);
        Object video = buildVideo("Video 2", 3.33, 1, "Targon", 89);
        Assert.assertEquals("Targon", call(video, "getDirector"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoGetMinutes2() throws Exception
    {
        String category = "VideoGetMinutes";
        recordTotal(category);
        Object video = buildVideo("Video 2", 3.33, 1, "Targon", 89);
        Assert.assertEquals(89, call(video, "getMinutes"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoConstructor3() throws Exception
    {
        String category = "VideoConstructor";
        recordTotal(category);
        Object video = buildVideo("Bob", 1.23, 3, "Bob Hammer", 152);
        assertVideo(video, "Bob", 1.23, 3, "Bob Hammer", 152);
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoGetDirector3() throws Exception
    {
        String category = "VideoGetDirector";
        recordTotal(category);
        Object video = buildVideo("Bob", 1.23, 3, "Bob Hammer", 152);
        Assert.assertEquals("Bob Hammer", call(video, "getDirector"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoGetMinutes3() throws Exception
    {
        String category = "VideoGetMinutes";
        recordTotal(category);
        Object video = buildVideo("Bob", 1.23, 3, "Bob Hammer", 152);
        Assert.assertEquals(152, call(video, "getMinutes"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoConstructor4() throws Exception
    {
        String category = "VideoConstructor";
        recordTotal(category);
        Object video = buildVideo("Video A", 2.22, 4, "Test Dir", 1);
        assertVideo(video, "Video A", 2.22, 4, "Test Dir", 1);
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoGetDirector4() throws Exception
    {
        String category = "VideoGetDirector";
        recordTotal(category);
        Object video = buildVideo("Video A", 2.22, 4, "Test Dir", 1);
        Assert.assertEquals("Test Dir", call(video, "getDirector"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoGetMinutes4() throws Exception
    {
        String category = "VideoGetMinutes";
        recordTotal(category);
        Object video = buildVideo("Video A", 2.22, 4, "Test Dir", 1);
        Assert.assertEquals(1, call(video, "getMinutes"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoConstructor5() throws Exception
    {
        String category = "VideoConstructor";
        recordTotal(category);
        Object video = buildVideo("Movie", 5.55, 2, "Director Name", 120);
        assertVideo(video, "Movie", 5.55, 2, "Director Name", 120);
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoGetDirector5() throws Exception
    {
        String category = "VideoGetDirector";
        recordTotal(category);
        Object video = buildVideo("Movie", 5.55, 2, "Director Name", 120);
        Assert.assertEquals("Director Name", call(video, "getDirector"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoGetMinutes5() throws Exception
    {
        String category = "VideoGetMinutes";
        recordTotal(category);
        Object video = buildVideo("Movie", 5.55, 2, "Director Name", 120);
        Assert.assertEquals(120, call(video, "getMinutes"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void GameConstructor1() throws Exception
    {
        String category = "GameConstructor";
        recordTotal(category);
        Object game = buildGame("Game A", 1.98, 0, "GameBoy", 1);
        assertGame(game, "Game A", 1.98, 0, "GameBoy", 1);
        recordPass(category);
    }

    @Test(timeout = 250)
    public void GameGetPlatform1() throws Exception
    {
        String category = "GameGetPlatform";
        recordTotal(category);
        Object game = buildGame("Game A", 1.98, 0, "GameBoy", 1);
        Assert.assertEquals("GameBoy", call(game, "getPlatform"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void GameGetNumberOfPlayers1() throws Exception
    {
        String category = "GameGetNumberOfPlayers";
        recordTotal(category);
        Object game = buildGame("Game A", 1.98, 0, "GameBoy", 1);
        Assert.assertEquals(1, call(game, "getNumberOfPlayers"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void GameConstructor2() throws Exception
    {
        String category = "GameConstructor";
        recordTotal(category);
        Object game = buildGame("Fall 13", 14.94, 1, "XBox", 4);
        assertGame(game, "Fall 13", 14.94, 1, "XBox", 4);
        recordPass(category);
    }

    @Test(timeout = 250)
    public void GameGetPlatform2() throws Exception
    {
        String category = "GameGetPlatform";
        recordTotal(category);
        Object game = buildGame("Fall 13", 14.94, 1, "XBox", 4);
        Assert.assertEquals("XBox", call(game, "getPlatform"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void GameGetNumberOfPlayers2() throws Exception
    {
        String category = "GameGetNumberOfPlayers";
        recordTotal(category);
        Object game = buildGame("Fall 13", 14.94, 1, "XBox", 4);
        Assert.assertEquals(4, call(game, "getNumberOfPlayers"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void GameConstructor3() throws Exception
    {
        String category = "GameConstructor";
        recordTotal(category);
        Object game = buildGame("Fall 13", 11.11, 1, "Rectangle", 4);
        assertGame(game, "Fall 13", 11.11, 1, "Rectangle", 4);
        recordPass(category);
    }

    @Test(timeout = 250)
    public void GameGetPlatform3() throws Exception
    {
        String category = "GameGetPlatform";
        recordTotal(category);
        Object game = buildGame("Fall 13", 11.11, 1, "Rectangle", 4);
        Assert.assertEquals("Rectangle", call(game, "getPlatform"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void GameGetNumberOfPlayers3() throws Exception
    {
        String category = "GameGetNumberOfPlayers";
        recordTotal(category);
        Object game = buildGame("Fall 13", 11.11, 1, "Rectangle", 4);
        Assert.assertEquals(4, call(game, "getNumberOfPlayers"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void GameConstructor4() throws Exception
    {
        String category = "GameConstructor";
        recordTotal(category);
        Object game = buildGame("Fall 17", 17.11, 2, "XBox", 2);
        assertGame(game, "Fall 17", 17.11, 2, "XBox", 2);
        recordPass(category);
    }

    @Test(timeout = 250)
    public void GameGetPlatform4() throws Exception
    {
        String category = "GameGetPlatform";
        recordTotal(category);
        Object game = buildGame("Fall 17", 17.11, 2, "XBox", 2);
        Assert.assertEquals("XBox", call(game, "getPlatform"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void GameGetNumberOfPlayers4() throws Exception
    {
        String category = "GameGetNumberOfPlayers";
        recordTotal(category);
        Object game = buildGame("Fall 17", 17.11, 2, "XBox", 2);
        Assert.assertEquals(2, call(game, "getNumberOfPlayers"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void GameConstructor5() throws Exception
    {
        String category = "GameConstructor";
        recordTotal(category);
        Object game = buildGame("Game B", 8.75, 3, "Switch", 8);
        assertGame(game, "Game B", 8.75, 3, "Switch", 8);
        recordPass(category);
    }

    @Test(timeout = 250)
    public void GameGetPlatform5() throws Exception
    {
        String category = "GameGetPlatform";
        recordTotal(category);
        Object game = buildGame("Game B", 8.75, 3, "Switch", 8);
        Assert.assertEquals("Switch", call(game, "getPlatform"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void GameGetNumberOfPlayers5() throws Exception
    {
        String category = "GameGetNumberOfPlayers";
        recordTotal(category);
        Object game = buildGame("Game B", 8.75, 3, "Switch", 8);
        Assert.assertEquals(8, call(game, "getNumberOfPlayers"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoStoreConstructor1() throws Exception
    {
        String category = "VideoStoreConstructor";
        recordTotal(category);
        Object store = buildStore();
        Assert.assertEquals(3, sizeOfList(call(store, "getGames")));
        Assert.assertEquals(3, sizeOfList(call(store, "getVideos")));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoStoreConstructor2() throws Exception
    {
        String category = "VideoStoreConstructor";
        recordTotal(category);
        Object store = buildStore();
        Assert.assertEquals(3, sizeOfList(call(store, "getGames")));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoStoreConstructor3() throws Exception
    {
        String category = "VideoStoreConstructor";
        recordTotal(category);
        Object store = buildStore();
        Assert.assertEquals(3, sizeOfList(call(store, "getVideos")));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoStoreConstructor4() throws Exception
    {
        String category = "VideoStoreConstructor";
        recordTotal(category);
        Object store = buildStore();
        Assert.assertEquals(0, sizeOfList(call(store, "rented")));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoStoreConstructor5() throws Exception
    {
        String category = "VideoStoreConstructor";
        recordTotal(category);
        Object store = buildStore();
        Assert.assertEquals(3, sizeOfList(call(store, "gamesInStock")));
        Assert.assertEquals(3, sizeOfList(call(store, "videosInStock")));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoStoreGetGames1() throws Exception
    {
        String category = "VideoStoreGetGames";
        recordTotal(category);
        Object store = buildStore();
        Assert.assertEquals(3, sizeOfList(call(store, "getGames")));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoStoreGetVideos1() throws Exception
    {
        String category = "VideoStoreGetVideos";
        recordTotal(category);
        Object store = buildStore();
        Assert.assertEquals(3, sizeOfList(call(store, "getVideos")));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoStoreGamesInStock1() throws Exception
    {
        String category = "VideoStoreGamesInStock";
        recordTotal(category);
        Object store = buildStore();
        Assert.assertEquals(3, sizeOfList(call(store, "gamesInStock")));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoStoreVideosInStock1() throws Exception
    {
        String category = "VideoStoreVideosInStock";
        recordTotal(category);
        Object store = buildStore();
        Assert.assertEquals(3, sizeOfList(call(store, "videosInStock")));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoStoreRented1() throws Exception
    {
        String category = "VideoStoreRented";
        recordTotal(category);
        Object store = buildStore();
        Assert.assertEquals(0, sizeOfList(call(store, "rented")));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoStoreGetGames2() throws Exception
    {
        String category = "VideoStoreGetGames";
        recordTotal(category);
        Object store = buildStore();
        ArrayList<?> games = (ArrayList<?>) call(store, "getGames");
        callSetRented(games.get(0), true);
        Assert.assertEquals(3, sizeOfList(call(store, "getGames")));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoStoreGetVideos2() throws Exception
    {
        String category = "VideoStoreGetVideos";
        recordTotal(category);
        Object store = buildStore();
        ArrayList<?> games = (ArrayList<?>) call(store, "getGames");
        callSetRented(games.get(0), true);
        Assert.assertEquals(3, sizeOfList(call(store, "getVideos")));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoStoreGamesInStock2() throws Exception
    {
        String category = "VideoStoreGamesInStock";
        recordTotal(category);
        Object store = buildStore();
        ArrayList<?> games = (ArrayList<?>) call(store, "getGames");
        callSetRented(games.get(0), true);
        Assert.assertEquals(2, sizeOfList(call(store, "gamesInStock")));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoStoreVideosInStock2() throws Exception
    {
        String category = "VideoStoreVideosInStock";
        recordTotal(category);
        Object store = buildStore();
        ArrayList<?> games = (ArrayList<?>) call(store, "getGames");
        callSetRented(games.get(0), true);
        Assert.assertEquals(3, sizeOfList(call(store, "videosInStock")));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoStoreRented2() throws Exception
    {
        String category = "VideoStoreRented";
        recordTotal(category);
        Object store = buildStore();
        ArrayList<?> games = (ArrayList<?>) call(store, "getGames");
        callSetRented(games.get(0), true);
        Assert.assertEquals(1, sizeOfList(call(store, "rented")));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoStoreGetGames3() throws Exception
    {
        String category = "VideoStoreGetGames";
        recordTotal(category);
        Object store = buildStore();
        ArrayList<?> videos = (ArrayList<?>) call(store, "getVideos");
        callSetRented(videos.get(0), true);
        Assert.assertEquals(3, sizeOfList(call(store, "getGames")));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoStoreGetVideos3() throws Exception
    {
        String category = "VideoStoreGetVideos";
        recordTotal(category);
        Object store = buildStore();
        ArrayList<?> videos = (ArrayList<?>) call(store, "getVideos");
        callSetRented(videos.get(0), true);
        Assert.assertEquals(3, sizeOfList(call(store, "getVideos")));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoStoreGamesInStock3() throws Exception
    {
        String category = "VideoStoreGamesInStock";
        recordTotal(category);
        Object store = buildStore();
        ArrayList<?> videos = (ArrayList<?>) call(store, "getVideos");
        callSetRented(videos.get(0), true);
        Assert.assertEquals(3, sizeOfList(call(store, "gamesInStock")));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoStoreVideosInStock3() throws Exception
    {
        String category = "VideoStoreVideosInStock";
        recordTotal(category);
        Object store = buildStore();
        ArrayList<?> videos = (ArrayList<?>) call(store, "getVideos");
        callSetRented(videos.get(0), true);
        Assert.assertEquals(2, sizeOfList(call(store, "videosInStock")));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoStoreRented3() throws Exception
    {
        String category = "VideoStoreRented";
        recordTotal(category);
        Object store = buildStore();
        ArrayList<?> videos = (ArrayList<?>) call(store, "getVideos");
        callSetRented(videos.get(0), true);
        Assert.assertEquals(1, sizeOfList(call(store, "rented")));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoStoreGetGames4() throws Exception
    {
        String category = "VideoStoreGetGames";
        recordTotal(category);
        Object store = buildStore();
        ArrayList<?> games = (ArrayList<?>) call(store, "getGames");
        ArrayList<?> videos = (ArrayList<?>) call(store, "getVideos");
        callSetRented(games.get(0), true);
        callSetRented(videos.get(1), true);
        Assert.assertEquals(3, sizeOfList(call(store, "getGames")));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoStoreGetVideos4() throws Exception
    {
        String category = "VideoStoreGetVideos";
        recordTotal(category);
        Object store = buildStore();
        ArrayList<?> games = (ArrayList<?>) call(store, "getGames");
        ArrayList<?> videos = (ArrayList<?>) call(store, "getVideos");
        callSetRented(games.get(0), true);
        callSetRented(videos.get(1), true);
        Assert.assertEquals(3, sizeOfList(call(store, "getVideos")));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoStoreGamesInStock4() throws Exception
    {
        String category = "VideoStoreGamesInStock";
        recordTotal(category);
        Object store = buildStore();
        ArrayList<?> games = (ArrayList<?>) call(store, "getGames");
        ArrayList<?> videos = (ArrayList<?>) call(store, "getVideos");
        callSetRented(games.get(0), true);
        callSetRented(videos.get(1), true);
        Assert.assertEquals(2, sizeOfList(call(store, "gamesInStock")));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoStoreVideosInStock4() throws Exception
    {
        String category = "VideoStoreVideosInStock";
        recordTotal(category);
        Object store = buildStore();
        ArrayList<?> games = (ArrayList<?>) call(store, "getGames");
        ArrayList<?> videos = (ArrayList<?>) call(store, "getVideos");
        callSetRented(games.get(0), true);
        callSetRented(videos.get(1), true);
        Assert.assertEquals(2, sizeOfList(call(store, "videosInStock")));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoStoreRented4() throws Exception
    {
        String category = "VideoStoreRented";
        recordTotal(category);
        Object store = buildStore();
        ArrayList<?> games = (ArrayList<?>) call(store, "getGames");
        ArrayList<?> videos = (ArrayList<?>) call(store, "getVideos");
        callSetRented(games.get(0), true);
        callSetRented(videos.get(1), true);
        Assert.assertEquals(2, sizeOfList(call(store, "rented")));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoStoreGetGames5() throws Exception
    {
        String category = "VideoStoreGetGames";
        recordTotal(category);
        Object store = buildStore();
        ArrayList<?> games = (ArrayList<?>) call(store, "getGames");
        ArrayList<?> videos = (ArrayList<?>) call(store, "getVideos");
        callSetRented(games.get(0), true);
        callSetRented(games.get(1), true);
        callSetRented(games.get(2), true);
        callSetRented(videos.get(0), true);
        callSetRented(videos.get(1), true);
        callSetRented(videos.get(2), true);
        Assert.assertEquals(3, sizeOfList(call(store, "getGames")));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoStoreGetVideos5() throws Exception
    {
        String category = "VideoStoreGetVideos";
        recordTotal(category);
        Object store = buildStore();
        ArrayList<?> games = (ArrayList<?>) call(store, "getGames");
        ArrayList<?> videos = (ArrayList<?>) call(store, "getVideos");
        callSetRented(games.get(0), true);
        callSetRented(games.get(1), true);
        callSetRented(games.get(2), true);
        callSetRented(videos.get(0), true);
        callSetRented(videos.get(1), true);
        callSetRented(videos.get(2), true);
        Assert.assertEquals(3, sizeOfList(call(store, "getVideos")));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoStoreGamesInStock5() throws Exception
    {
        String category = "VideoStoreGamesInStock";
        recordTotal(category);
        Object store = buildStore();
        ArrayList<?> games = (ArrayList<?>) call(store, "getGames");
        ArrayList<?> videos = (ArrayList<?>) call(store, "getVideos");
        callSetRented(games.get(0), true);
        callSetRented(games.get(1), true);
        callSetRented(games.get(2), true);
        callSetRented(videos.get(0), true);
        callSetRented(videos.get(1), true);
        callSetRented(videos.get(2), true);
        Assert.assertEquals(0, sizeOfList(call(store, "gamesInStock")));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoStoreVideosInStock5() throws Exception
    {
        String category = "VideoStoreVideosInStock";
        recordTotal(category);
        Object store = buildStore();
        ArrayList<?> games = (ArrayList<?>) call(store, "getGames");
        ArrayList<?> videos = (ArrayList<?>) call(store, "getVideos");
        callSetRented(games.get(0), true);
        callSetRented(games.get(1), true);
        callSetRented(games.get(2), true);
        callSetRented(videos.get(0), true);
        callSetRented(videos.get(1), true);
        callSetRented(videos.get(2), true);
        Assert.assertEquals(0, sizeOfList(call(store, "videosInStock")));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void VideoStoreRented5() throws Exception
    {
        String category = "VideoStoreRented";
        recordTotal(category);
        Object store = buildStore();
        ArrayList<?> games = (ArrayList<?>) call(store, "getGames");
        ArrayList<?> videos = (ArrayList<?>) call(store, "getVideos");
        callSetRented(games.get(0), true);
        callSetRented(games.get(1), true);
        callSetRented(games.get(2), true);
        callSetRented(videos.get(0), true);
        callSetRented(videos.get(1), true);
        callSetRented(videos.get(2), true);
        Assert.assertEquals(6, sizeOfList(call(store, "rented")));
        recordPass(category);
    }

    @AfterClass
    public static void tearDownClass()
    {
        double earned = 0.0;

        System.out.println();
        System.out.println("Category                Passed/Total    Points");
        System.out.println("----------------------------------------------");

        for(Map.Entry<String, double[]> entry : Scores.grading.entrySet())
        {
            String category = entry.getKey();
            double[] values = entry.getValue();
            double categoryPossible = values[0];
            double passed = values[1];
            double total = values[2];
            double categoryEarned = total == 0.0 ? 0.0 : categoryPossible * (passed / total);

            earned += categoryEarned;

            System.out.printf("%-23s %4.0f/%-7.0f %6.2f / %.2f%n", category, passed, total, categoryEarned, categoryPossible);
        }

        System.out.println("----------------------------------------------");
        System.out.printf("Grade = %.2f%n", earned);
    }
}