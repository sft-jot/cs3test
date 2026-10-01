import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class VideoStore_Main_Tests
{
    private static final PrintStream ORIGINAL_OUT = System.out;
    private static final InputStream ORIGINAL_IN = System.in;

    private ByteArrayOutputStream outContent;

    public static class Scores
    {
        public static LinkedHashMap<String, double[]> grading = new LinkedHashMap<String, double[]>();

        static
        {
            grading.put("main", new double[]{10.0, 0.0, 0.0});
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

    @Before
    public void setup()
    {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanup()
    {
        System.setOut(ORIGINAL_OUT);
        System.setIn(ORIGINAL_IN);
    }

    public String unifyLineSeparators(String line)
    {
        return line.replaceAll("\\r\\n|\\n|\\r", System.getProperty("line.separator")).trim();
    }

    private String runMain(String input) throws Exception
    {
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        Class<?> classRef = Class.forName(generateClassName("VideoStore_Main"));
        Method main = classRef.getMethod("main", String[].class);

        try
        {
            main.invoke(null, (Object) new String[]{});
        }
        catch(InvocationTargetException e)
        {
            throw (Exception) e.getCause();
        }

        return unifyLineSeparators(outContent.toString());
    }

    private void assertFullOutput(String input, String expected) throws Exception
    {
        String actual = runMain(input);
        Assert.assertEquals(unifyLineSeparators(expected), actual);
    }

    @Test(timeout = 250)
    public void main1() throws Exception
    {
        String category = "main";
        recordTotal(category);

        String input = "1\n" +
                "1\n" +
                "1\n" +
                "1\n" +
                "2\n" +
                "1\n" +
                "2\n" +
                "1\n" +
                "1\n" +
                "3";

        String expected = "Renting Menu\n" +
                "1. Rent Video\n" +
                "2. Rent Game\n" +
                "3. Checkout\n" +
                "Enter selection:\n" +
                "\n" +
                "Video Menu\n" +
                "1. Iron Man 3\n" +
                "2. The Wolverine\n" +
                "3. The Avengers\n" +
                "0. Cancel\n" +
                "Enter selection:\n" +
                "\n" +
                "Title:                        Iron Man 3\n" +
                "Cost:                               3.99\n" +
                "Rating:                            PG-13\n" +
                "Director:                    Shawn Black\n" +
                "Runtime:                     130 minutes\n" +
                "\n" +
                "1 - Rent, 2 - Put Back\n" +
                "Enter selection:\n" +
                "\n" +
                "Renting Menu\n" +
                "1. Rent Video\n" +
                "2. Rent Game\n" +
                "3. Checkout\n" +
                "Enter selection:\n" +
                "\n" +
                "Video Menu\n" +
                "1. The Wolverine\n" +
                "2. The Avengers\n" +
                "0. Cancel\n" +
                "Enter selection:\n" +
                "\n" +
                "Title:                      The Avengers\n" +
                "Cost:                               1.99\n" +
                "Rating:                            PG-13\n" +
                "Director:                    Joss Whedon\n" +
                "Runtime:                     143 minutes\n" +
                "\n" +
                "1 - Rent, 2 - Put Back\n" +
                "Enter selection:\n" +
                "\n" +
                "Renting Menu\n" +
                "1. Rent Video\n" +
                "2. Rent Game\n" +
                "3. Checkout\n" +
                "Enter selection:\n" +
                "\n" +
                "Game Menu\n" +
                "1. Assassin's Creed\n" +
                "2. Frogger Advanced\n" +
                "3. Bomberman Land\n" +
                "0. Cancel\n" +
                "Enter selection:\n" +
                "\n" +
                "Title:                  Assassin's Creed\n" +
                "Cost:                               4.99\n" +
                "Rating:                           Mature\n" +
                "Platform:                  Playstation 3\n" +
                "# of players:                          1\n" +
                "\n" +
                "1 - Rent, 2 - Put Back\n" +
                "Enter selection:\n" +
                "\n" +
                "Renting Menu\n" +
                "1. Rent Video\n" +
                "2. Rent Game\n" +
                "3. Checkout\n" +
                "Enter selection:\n" +
                "\n" +
                "Receipt:\n" +
                "\n" +
                "Rented Videos:\n" +
                "    Iron Man 3                      3.99\n" +
                "    The Avengers                    1.99\n" +
                "\n" +
                "Rented Games:\n" +
                "    Assassin's Creed                4.99\n" +
                "\n" +
                "Subtotal:                          10.97\n" +
                "Tax:                                0.91\n" +
                "Total:                             11.88";

        assertFullOutput(input, expected);
        recordPass(category);
    }

    @Test(timeout = 250)
    public void main2() throws Exception
    {
        String category = "main";
        recordTotal(category);

        String input = "2\n" +
                "1\n" +
                "2\n" +
                "2\n" +
                "1\n" +
                "1\n" +
                "2\n" +
                "1\n" +
                "1\n" +
                "2\n" +
                "1\n" +
                "1\n" +
                "2\n" +
                "3";

        String expected = "Renting Menu\n" +
                "1. Rent Video\n" +
                "2. Rent Game\n" +
                "3. Checkout\n" +
                "Enter selection:\n" +
                "\n" +
                "Game Menu\n" +
                "1. Assassin's Creed\n" +
                "2. Frogger Advanced\n" +
                "3. Bomberman Land\n" +
                "0. Cancel\n" +
                "Enter selection:\n" +
                "\n" +
                "Title:                  Assassin's Creed\n" +
                "Cost:                               4.99\n" +
                "Rating:                           Mature\n" +
                "Platform:                  Playstation 3\n" +
                "# of players:                          1\n" +
                "\n" +
                "1 - Rent, 2 - Put Back\n" +
                "Enter selection:\n" +
                "\n" +
                "Renting Menu\n" +
                "1. Rent Video\n" +
                "2. Rent Game\n" +
                "3. Checkout\n" +
                "Enter selection:\n" +
                "\n" +
                "Game Menu\n" +
                "1. Assassin's Creed\n" +
                "2. Frogger Advanced\n" +
                "3. Bomberman Land\n" +
                "0. Cancel\n" +
                "Enter selection:\n" +
                "\n" +
                "Title:                  Assassin's Creed\n" +
                "Cost:                               4.99\n" +
                "Rating:                           Mature\n" +
                "Platform:                  Playstation 3\n" +
                "# of players:                          1\n" +
                "\n" +
                "1 - Rent, 2 - Put Back\n" +
                "Enter selection:\n" +
                "\n" +
                "Renting Menu\n" +
                "1. Rent Video\n" +
                "2. Rent Game\n" +
                "3. Checkout\n" +
                "Enter selection:\n" +
                "\n" +
                "Game Menu\n" +
                "1. Frogger Advanced\n" +
                "2. Bomberman Land\n" +
                "0. Cancel\n" +
                "Enter selection:\n" +
                "\n" +
                "Title:                  Frogger Advanced\n" +
                "Cost:                               2.99\n" +
                "Rating:                  Early Childhood\n" +
                "Platform:              Game Boy Advanced\n" +
                "# of players:                          1\n" +
                "\n" +
                "1 - Rent, 2 - Put Back\n" +
                "Enter selection:\n" +
                "\n" +
                "Renting Menu\n" +
                "1. Rent Video\n" +
                "2. Rent Game\n" +
                "3. Checkout\n" +
                "Enter selection:\n" +
                "\n" +
                "Game Menu\n" +
                "1. Bomberman Land\n" +
                "0. Cancel\n" +
                "Enter selection:\n" +
                "\n" +
                "Title:                    Bomberman Land\n" +
                "Cost:                               2.99\n" +
                "Rating:                             Teen\n" +
                "Platform:                      Super NES\n" +
                "# of players:                          4\n" +
                "\n" +
                "1 - Rent, 2 - Put Back\n" +
                "Enter selection:\n" +
                "\n" +
                "Renting Menu\n" +
                "1. Rent Video\n" +
                "2. Rent Game\n" +
                "3. Checkout\n" +
                "Enter selection:\n" +
                "\n" +
                "There are no more games in stock.\n" +
                "\n" +
                "Renting Menu\n" +
                "1. Rent Video\n" +
                "2. Rent Game\n" +
                "3. Checkout\n" +
                "Enter selection:\n" +
                "\n" +
                "Receipt:\n" +
                "\n" +
                "Rented Games:\n" +
                "    Assassin's Creed                4.99\n" +
                "    Frogger Advanced                2.99\n" +
                "    Bomberman Land                  2.99\n" +
                "\n" +
                "Subtotal:                          10.97\n" +
                "Tax:                                0.91\n" +
                "Total:                             11.88";

        assertFullOutput(input, expected);
        recordPass(category);
    }

    @Test(timeout = 250)
    public void main3() throws Exception
    {
        String category = "main";
        recordTotal(category);

        String input = "1\n" +
                "1\n" +
                "2\n" +
                "1\n" +
                "1\n" +
                "1\n" +
                "1\n" +
                "1\n" +
                "1\n" +
                "1\n" +
                "1\n" +
                "1\n" +
                "1\n" +
                "3";

        String expected = "Renting Menu\n" +
                "1. Rent Video\n" +
                "2. Rent Game\n" +
                "3. Checkout\n" +
                "Enter selection:\n" +
                "\n" +
                "Video Menu\n" +
                "1. Iron Man 3\n" +
                "2. The Wolverine\n" +
                "3. The Avengers\n" +
                "0. Cancel\n" +
                "Enter selection:\n" +
                "\n" +
                "Title:                        Iron Man 3\n" +
                "Cost:                               3.99\n" +
                "Rating:                            PG-13\n" +
                "Director:                    Shawn Black\n" +
                "Runtime:                     130 minutes\n" +
                "\n" +
                "1 - Rent, 2 - Put Back\n" +
                "Enter selection:\n" +
                "\n" +
                "Renting Menu\n" +
                "1. Rent Video\n" +
                "2. Rent Game\n" +
                "3. Checkout\n" +
                "Enter selection:\n" +
                "\n" +
                "Video Menu\n" +
                "1. Iron Man 3\n" +
                "2. The Wolverine\n" +
                "3. The Avengers\n" +
                "0. Cancel\n" +
                "Enter selection:\n" +
                "\n" +
                "Title:                        Iron Man 3\n" +
                "Cost:                               3.99\n" +
                "Rating:                            PG-13\n" +
                "Director:                    Shawn Black\n" +
                "Runtime:                     130 minutes\n" +
                "\n" +
                "1 - Rent, 2 - Put Back\n" +
                "Enter selection:\n" +
                "\n" +
                "Renting Menu\n" +
                "1. Rent Video\n" +
                "2. Rent Game\n" +
                "3. Checkout\n" +
                "Enter selection:\n" +
                "\n" +
                "Video Menu\n" +
                "1. The Wolverine\n" +
                "2. The Avengers\n" +
                "0. Cancel\n" +
                "Enter selection:\n" +
                "\n" +
                "Title:                     The Wolverine\n" +
                "Cost:                               2.79\n" +
                "Rating:                                R\n" +
                "Director:                  James Mangold\n" +
                "Runtime:                     126 minutes\n" +
                "\n" +
                "1 - Rent, 2 - Put Back\n" +
                "Enter selection:\n" +
                "\n" +
                "Renting Menu\n" +
                "1. Rent Video\n" +
                "2. Rent Game\n" +
                "3. Checkout\n" +
                "Enter selection:\n" +
                "\n" +
                "Video Menu\n" +
                "1. The Avengers\n" +
                "0. Cancel\n" +
                "Enter selection:\n" +
                "\n" +
                "Title:                      The Avengers\n" +
                "Cost:                               1.99\n" +
                "Rating:                            PG-13\n" +
                "Director:                    Joss Whedon\n" +
                "Runtime:                     143 minutes\n" +
                "\n" +
                "1 - Rent, 2 - Put Back\n" +
                "Enter selection:\n" +
                "\n" +
                "Renting Menu\n" +
                "1. Rent Video\n" +
                "2. Rent Game\n" +
                "3. Checkout\n" +
                "Enter selection:\n" +
                "\n" +
                "There are no more videos in stock.\n" +
                "\n" +
                "Renting Menu\n" +
                "1. Rent Video\n" +
                "2. Rent Game\n" +
                "3. Checkout\n" +
                "Enter selection:\n" +
                "\n" +
                "Receipt:\n" +
                "\n" +
                "Rented Videos:\n" +
                "    Iron Man 3                      3.99\n" +
                "    The Wolverine                   2.79\n" +
                "    The Avengers                    1.99\n" +
                "\n" +
                "Subtotal:                           8.77\n" +
                "Tax:                                0.72\n" +
                "Total:                              9.49";

        assertFullOutput(input, expected);
        recordPass(category);
    }

    @Test(timeout = 250)
    public void main4() throws Exception
    {
        String category = "main";
        recordTotal(category);

        String input = "1\n" +
                "0\n" +
                "2\n" +
                "0\n" +
                "3";

        String expected = "Renting Menu\n" +
                "1. Rent Video\n" +
                "2. Rent Game\n" +
                "3. Checkout\n" +
                "Enter selection:\n" +
                "\n" +
                "Video Menu\n" +
                "1. Iron Man 3\n" +
                "2. The Wolverine\n" +
                "3. The Avengers\n" +
                "0. Cancel\n" +
                "Enter selection:\n" +
                "\n" +
                "Renting Menu\n" +
                "1. Rent Video\n" +
                "2. Rent Game\n" +
                "3. Checkout\n" +
                "Enter selection:\n" +
                "\n" +
                "Game Menu\n" +
                "1. Assassin's Creed\n" +
                "2. Frogger Advanced\n" +
                "3. Bomberman Land\n" +
                "0. Cancel\n" +
                "Enter selection:\n" +
                "\n" +
                "Renting Menu\n" +
                "1. Rent Video\n" +
                "2. Rent Game\n" +
                "3. Checkout\n" +
                "Enter selection:\n" +
                "\n" +
                "You did not rent anything.\n" +
                "Have a good day!";

        assertFullOutput(input, expected);
        recordPass(category);
    }

    @Test(timeout = 250)
    public void main5() throws Exception
    {
        String category = "main";
        recordTotal(category);

        String input = "3";

        String expected = "Renting Menu\n" +
                "1. Rent Video\n" +
                "2. Rent Game\n" +
                "3. Checkout\n" +
                "Enter selection:\n" +
                "\n" +
                "You did not rent anything.\n" +
                "Have a good day!";

        assertFullOutput(input, expected);
        recordPass(category);
    }

    @AfterClass
    public static void tearDownClass()
    {
        ORIGINAL_OUT.println();
        ORIGINAL_OUT.println("Category                Passed/Total    Points");
        ORIGINAL_OUT.println("----------------------------------------------");

        double earned = 0.0;

        for(Map.Entry<String, double[]> entry : Scores.grading.entrySet())
        {
            String category = entry.getKey();
            double[] values = entry.getValue();
            double categoryPossible = values[0];
            double passed = values[1];
            double total = values[2];
            double categoryEarned = total == 0.0 ? 0.0 : categoryPossible * (passed / total);

            earned += categoryEarned;

            ORIGINAL_OUT.printf("%-23s %4.0f/%-7.0f %6.2f / %.2f%n", category, passed, total, categoryEarned, categoryPossible);
        }

        ORIGINAL_OUT.println("----------------------------------------------");
        ORIGINAL_OUT.printf("Grade = %.2f%n", earned);
    }
}
