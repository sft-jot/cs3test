
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
public class Thief_Tests
{
    public static class Scores
    {
        public static LinkedHashMap<String, double[]> grading = new
                LinkedHashMap<String, double[]>();
        static
        {
            grading.put("constructorAttributes", new double[]{10.0, 0.0, 0.0});
            grading.put("getStealthPoints", new double[]{10.0, 0.0, 0.0});
            grading.put("getInventoryWeight", new double[]{10.0, 0.0, 0.0});
            grading.put("stealthAbility", new double[]{10.0, 0.0, 0.0});
            grading.put("takeDamage", new double[]{10.0, 0.0, 0.0});
        }
    }
    public String generateClassName(String name)
    {
        if(getClass().toString().contains("."))
        {
            return getClass().toString().substring(6,
                    getClass().toString().lastIndexOf(".") + 1) + name;
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
    private Object buildThief(String name, int hitPoints, int stealthPoints, int
            inventoryWeight) throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("Thief"));
        Object thief = classRef.getConstructor(String.class, int.class, int.class,
                int.class).newInstance(name, hitPoints, stealthPoints, inventoryWeight);
        return thief;
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
    private void callVoid(Object object, String methodName, int value) throws
            Exception
    {
        Method method = object.getClass().getMethod(methodName, int.class);
        try
        {
            method.invoke(object, value);
        }
        catch(InvocationTargetException e)
        {
            throw (Exception) e.getCause();
        }
    }
    private void assertThiefAttributes(Object thief, String expectedName, int
            expectedHitPoints, int expectedStealthPoints, int expectedInventoryWeight, int
                                               expectedStealthAbility) throws Exception
    {
        Assert.assertEquals(expectedName, call(thief, "getCharacterName"));
        Assert.assertEquals(expectedHitPoints, call(thief, "getHitPoints"));
        Assert.assertEquals(0, call(thief, "getMagicPoints"));
        Assert.assertEquals(expectedStealthPoints, call(thief,
                "getStealthPoints"));
        Assert.assertEquals(expectedInventoryWeight, call(thief,
                "getInventoryWeight"));
        Assert.assertEquals(expectedStealthAbility, call(thief, "stealthAbility"));
    }
    @Test(timeout = 250)
    public void constructorAttributes1() throws Exception
    {
        String category = "constructorAttributes";
        recordTotal(category);
        Object thief = buildThief("Jane", 15, 8, 2);
        assertThiefAttributes(thief, "Jane", 15, 8, 2, 7);
        recordPass(category);
    }
    @Test(timeout = 250)
    public void constructorAttributes2() throws Exception
    {
        String category = "constructorAttributes";
        recordTotal(category);
        Object thief = buildThief("Bilbo", 12, 10, 5);
        assertThiefAttributes(thief, "Bilbo", 12, 10, 5, 8);
        recordPass(category);
    }
    @Test(timeout = 250)
    public void constructorAttributes3() throws Exception
    {
        String category = "constructorAttributes";
        recordTotal(category);
        Object thief = buildThief("James", 10, 6, 12);
        assertThiefAttributes(thief, "James", 10, 6, 12, 0);
        recordPass(category);
    }
    @Test(timeout = 250)
    public void constructorAttributes4() throws Exception
    {
        String category = "constructorAttributes";
        recordTotal(category);
        Object thief = buildThief("Tina", 15, 6, 1);
        assertThiefAttributes(thief, "Tina", 15, 6, 1, 6);
        recordPass(category);
    }
    @Test(timeout = 250)
    public void constructorAttributes5() throws Exception
    {
        String category = "constructorAttributes";
        recordTotal(category);
        Object thief = buildThief("Robin", 20, 0, 0);
        assertThiefAttributes(thief, "Robin", 20, 0, 0, 0);
        recordPass(category);
    }
    @Test(timeout = 250)
    public void getStealthPoints1() throws Exception
    {
        String category = "getStealthPoints";
        recordTotal(category);
        Object thief = buildThief("Jane", 15, 8, 2);
        Assert.assertEquals(8, call(thief, "getStealthPoints"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void getStealthPoints2() throws Exception
    {
        String category = "getStealthPoints";
        recordTotal(category);
        Object thief = buildThief("Bilbo", 12, 10, 5);
        Assert.assertEquals(10, call(thief, "getStealthPoints"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void getStealthPoints3() throws Exception
    {
        String category = "getStealthPoints";
        recordTotal(category);
        Object thief = buildThief("James", 10, 6, 12);
        Assert.assertEquals(6, call(thief, "getStealthPoints"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void getStealthPoints4() throws Exception
    {
        String category = "getStealthPoints";
        recordTotal(category);
        Object thief = buildThief("Tina", 15, 6, 1);
        Assert.assertEquals(6, call(thief, "getStealthPoints"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void getStealthPoints5() throws Exception
    {
        String category = "getStealthPoints";
        recordTotal(category);
        Object thief = buildThief("Robin", 20, 0, 0);
        Assert.assertEquals(0, call(thief, "getStealthPoints"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void getInventoryWeight1() throws Exception
    {
        String category = "getInventoryWeight";
        recordTotal(category);
        Object thief = buildThief("Jane", 15, 8, 2);
        Assert.assertEquals(2, call(thief, "getInventoryWeight"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void getInventoryWeight2() throws Exception
    {
        String category = "getInventoryWeight";
        recordTotal(category);
        Object thief = buildThief("Bilbo", 12, 10, 5);
        Assert.assertEquals(5, call(thief, "getInventoryWeight"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void getInventoryWeight3() throws Exception
    {
        String category = "getInventoryWeight";
        recordTotal(category);
        Object thief = buildThief("James", 10, 6, 12);
        Assert.assertEquals(12, call(thief, "getInventoryWeight"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void getInventoryWeight4() throws Exception
    {
        String category = "getInventoryWeight";
        recordTotal(category);
        Object thief = buildThief("Tina", 15, 6, 1);
        Assert.assertEquals(1, call(thief, "getInventoryWeight"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void getInventoryWeight5() throws Exception
    {
        String category = "getInventoryWeight";
        recordTotal(category);
        Object thief = buildThief("Robin", 20, 0, 0);
        Assert.assertEquals(0, call(thief, "getInventoryWeight"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void stealthAbility1() throws Exception
    {
        String category = "stealthAbility";
        recordTotal(category);
        Object thief = buildThief("Jane", 15, 8, 2);
        Assert.assertEquals(7, call(thief, "stealthAbility"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void stealthAbility2() throws Exception
    {
        String category = "stealthAbility";
        recordTotal(category);
        Object thief = buildThief("Bilbo", 12, 10, 5);
        Assert.assertEquals(8, call(thief, "stealthAbility"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void stealthAbility3() throws Exception
    {
        String category = "stealthAbility";
        recordTotal(category);
        Object thief = buildThief("James", 10, 6, 12);
        Assert.assertEquals(0, call(thief, "stealthAbility"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void stealthAbility4() throws Exception
    {
        String category = "stealthAbility";
        recordTotal(category);
        Object thief = buildThief("Tina", 15, 6, 1);
        Assert.assertEquals(6, call(thief, "stealthAbility"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void stealthAbility5() throws Exception
    {
        String category = "stealthAbility";
        recordTotal(category);
        Object thief = buildThief("Robin", 20, 0, 20);
        Assert.assertEquals(0, call(thief, "stealthAbility"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void takeDamage1() throws Exception
    {
        String category = "takeDamage";
        recordTotal(category);
        Object thief = buildThief("Bilbo", 12, 10, 5);
        callVoid(thief, "takeDamage", 10);
        Assert.assertEquals(10, call(thief, "getHitPoints"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void takeDamage2() throws Exception
    {
        String category = "takeDamage";
        recordTotal(category);
        Object thief = buildThief("James", 10, 6, 12);
        callVoid(thief, "takeDamage", 8);
        Assert.assertEquals(2, call(thief, "getHitPoints"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void takeDamage3() throws Exception
    {
        String category = "takeDamage";
        recordTotal(category);
        Object thief = buildThief("Tina", 15, 6, 1);
        callVoid(thief, "takeDamage", 1);
        Assert.assertEquals(15, call(thief, "getHitPoints"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void takeDamage4() throws Exception
    {
        String category = "takeDamage";
        recordTotal(category);
        Object thief = buildThief("Bilbo", 12, 10, 0);
        callVoid(thief, "takeDamage", 3);
        Assert.assertEquals(12, call(thief, "getHitPoints"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void takeDamage5() throws Exception
    {
        String category = "takeDamage";
        recordTotal(category);
        Object thief = buildThief("Robin", 30, 4, 2);
        callVoid(thief, "takeDamage", 10);
        callVoid(thief, "takeDamage", 4);
        Assert.assertEquals(22, call(thief, "getHitPoints"));
        recordPass(category);
    }
    @AfterClass
    public static void tearDownClass()
    {
        double earned = 0.0;
        System.out.println();
        System.out.println("Category Passed/Total Points");
        System.out.println("----------------------------------------------");
        for(Map.Entry<String, double[]> entry : Scores.grading.entrySet())
        {
            String category = entry.getKey();
            double[] values = entry.getValue();
            double categoryPossible = values[0];
            double passed = values[1];
            double total = values[2];
            double categoryEarned = total == 0.0 ? 0.0 : categoryPossible * (passed
                    / total);
            earned += categoryEarned;
            System.out.printf("%-23s %4.0f/%-7.0f %6.2f / %.2f%n", category,
                    passed, total, categoryEarned, categoryPossible);
        }
        System.out.println("----------------------------------------------");
        System.out.printf("Grade = %.2f%n", earned);
    }
}
