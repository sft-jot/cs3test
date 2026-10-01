import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

public class Mage_Tests
{
    public static class Scores
    {
        public static LinkedHashMap<String, double[]> grading = new LinkedHashMap<String, double[]>();

        static
        {
            grading.put("constructorAttributes", new double[]{10.0, 0.0, 0.0});
            grading.put("getSpellShieldCost", new double[]{10.0, 0.0, 0.0});
            grading.put("getSpellShieldAbsorbAmount", new double[]{10.0, 0.0, 0.0});
            grading.put("drinkManaPotion", new double[]{10.0, 0.0, 0.0});
            grading.put("takeDamage", new double[]{10.0, 0.0, 0.0});
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

    private Object buildMage(String name, int hitPoints, int magicPoints, int shieldCost, int shieldAbsorb) throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("Mage"));
        Object mage = classRef.getConstructor(String.class, int.class, int.class, int.class, int.class).newInstance(name, hitPoints, magicPoints, shieldCost, shieldAbsorb);

        return mage;
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

    private void callVoid(Object object, String methodName, int value) throws Exception
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

    private void assertMageAttributes(Object mage, String expectedName, int expectedHitPoints, int expectedMagicPoints, int expectedShieldCost, int expectedShieldAbsorb) throws Exception
    {
        Assert.assertEquals(expectedName, call(mage, "getCharacterName"));
        Assert.assertEquals(expectedHitPoints, call(mage, "getHitPoints"));
        Assert.assertEquals(expectedMagicPoints, call(mage, "getMagicPoints"));
        Assert.assertEquals(expectedShieldCost, call(mage, "getSpellShieldCost"));
        Assert.assertEquals(expectedShieldAbsorb, call(mage, "getSpellShieldAbsorbAmount"));
    }

    @Test(timeout = 250)
    public void constructorAttributes1() throws Exception
    {
        String category = "constructorAttributes";
        recordTotal(category);
        Object mage = buildMage("Jane", 15, 10, 3, 2);
        assertMageAttributes(mage, "Jane", 15, 10, 3, 2);
        recordPass(category);
    }

    @Test(timeout = 250)
    public void constructorAttributes2() throws Exception
    {
        String category = "constructorAttributes";
        recordTotal(category);
        Object mage = buildMage("Gandalf", 100, 100, 25, 5);
        assertMageAttributes(mage, "Gandalf", 100, 100, 25, 5);
        recordPass(category);
    }

    @Test(timeout = 250)
    public void constructorAttributes3() throws Exception
    {
        String category = "constructorAttributes";
        recordTotal(category);
        Object mage = buildMage("Mira", 40, 12, 1, 9);
        assertMageAttributes(mage, "Mira", 40, 12, 1, 9);
        recordPass(category);
    }

    @Test(timeout = 250)
    public void constructorAttributes4() throws Exception
    {
        String category = "constructorAttributes";
        recordTotal(category);
        Object mage = buildMage("Orin", 60, 0, 10, 4);
        assertMageAttributes(mage, "Orin", 60, 0, 10, 4);
        recordPass(category);
    }

    @Test(timeout = 250)
    public void constructorAttributes5() throws Exception
    {
        String category = "constructorAttributes";
        recordTotal(category);
        Object mage = buildMage("Luna", 25, 99, 30, 12);
        assertMageAttributes(mage, "Luna", 25, 99, 30, 12);
        recordPass(category);
    }

    @Test(timeout = 250)
    public void getSpellShieldCost1() throws Exception
    {
        String category = "getSpellShieldCost";
        recordTotal(category);
        Object mage = buildMage("Jane", 15, 10, 3, 2);
        Assert.assertEquals(3, call(mage, "getSpellShieldCost"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void getSpellShieldCost2() throws Exception
    {
        String category = "getSpellShieldCost";
        recordTotal(category);
        Object mage = buildMage("Gandalf", 100, 100, 25, 5);
        Assert.assertEquals(25, call(mage, "getSpellShieldCost"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void getSpellShieldCost3() throws Exception
    {
        String category = "getSpellShieldCost";
        recordTotal(category);
        Object mage = buildMage("Mira", 40, 12, 1, 9);
        Assert.assertEquals(1, call(mage, "getSpellShieldCost"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void getSpellShieldCost4() throws Exception
    {
        String category = "getSpellShieldCost";
        recordTotal(category);
        Object mage = buildMage("Orin", 60, 0, 10, 4);
        Assert.assertEquals(10, call(mage, "getSpellShieldCost"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void getSpellShieldCost5() throws Exception
    {
        String category = "getSpellShieldCost";
        recordTotal(category);
        Object mage = buildMage("Luna", 25, 99, 30, 12);
        Assert.assertEquals(30, call(mage, "getSpellShieldCost"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void getSpellShieldAbsorbAmount1() throws Exception
    {
        String category = "getSpellShieldAbsorbAmount";
        recordTotal(category);
        Object mage = buildMage("Jane", 15, 10, 3, 2);
        Assert.assertEquals(2, call(mage, "getSpellShieldAbsorbAmount"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void getSpellShieldAbsorbAmount2() throws Exception
    {
        String category = "getSpellShieldAbsorbAmount";
        recordTotal(category);
        Object mage = buildMage("Gandalf", 100, 100, 25, 5);
        Assert.assertEquals(5, call(mage, "getSpellShieldAbsorbAmount"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void getSpellShieldAbsorbAmount3() throws Exception
    {
        String category = "getSpellShieldAbsorbAmount";
        recordTotal(category);
        Object mage = buildMage("Mira", 40, 12, 1, 9);
        Assert.assertEquals(9, call(mage, "getSpellShieldAbsorbAmount"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void getSpellShieldAbsorbAmount4() throws Exception
    {
        String category = "getSpellShieldAbsorbAmount";
        recordTotal(category);
        Object mage = buildMage("Orin", 60, 0, 10, 4);
        Assert.assertEquals(4, call(mage, "getSpellShieldAbsorbAmount"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void getSpellShieldAbsorbAmount5() throws Exception
    {
        String category = "getSpellShieldAbsorbAmount";
        recordTotal(category);
        Object mage = buildMage("Luna", 25, 99, 30, 12);
        Assert.assertEquals(12, call(mage, "getSpellShieldAbsorbAmount"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void drinkManaPotion1() throws Exception
    {
        String category = "drinkManaPotion";
        recordTotal(category);
        Object mage = buildMage("Billy", 10, 5, 2, 2);
        callVoid(mage, "drinkManaPotion", 3);
        Assert.assertEquals(8, call(mage, "getMagicPoints"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void drinkManaPotion2() throws Exception
    {
        String category = "drinkManaPotion";
        recordTotal(category);
        Object mage = buildMage("Jane", 30, 0, 4, 2);
        callVoid(mage, "drinkManaPotion", 10);
        Assert.assertEquals(10, call(mage, "getMagicPoints"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void drinkManaPotion3() throws Exception
    {
        String category = "drinkManaPotion";
        recordTotal(category);
        Object mage = buildMage("Mira", 40, 12, 1, 9);
        callVoid(mage, "drinkManaPotion", 1);
        Assert.assertEquals(13, call(mage, "getMagicPoints"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void drinkManaPotion4() throws Exception
    {
        String category = "drinkManaPotion";
        recordTotal(category);
        Object mage = buildMage("Orin", 60, 20, 10, 4);
        callVoid(mage, "drinkManaPotion", 25);
        Assert.assertEquals(45, call(mage, "getMagicPoints"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void drinkManaPotion5() throws Exception
    {
        String category = "drinkManaPotion";
        recordTotal(category);
        Object mage = buildMage("Luna", 25, 99, 30, 12);
        callVoid(mage, "drinkManaPotion", 0);
        Assert.assertEquals(99, call(mage, "getMagicPoints"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void takeDamage1() throws Exception
    {
        String category = "takeDamage";
        recordTotal(category);
        Object mage = buildMage("Billy", 10, 5, 3, 2);
        callVoid(mage, "takeDamage", 8);
        Assert.assertEquals(4, call(mage, "getHitPoints"));
        Assert.assertEquals(2, call(mage, "getMagicPoints"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void takeDamage2() throws Exception
    {
        String category = "takeDamage";
        recordTotal(category);
        Object mage = buildMage("Billy", 10, 5, 6, 2);
        callVoid(mage, "takeDamage", 8);
        Assert.assertEquals(2, call(mage, "getHitPoints"));
        Assert.assertEquals(5, call(mage, "getMagicPoints"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void takeDamage3() throws Exception
    {
        String category = "takeDamage";
        recordTotal(category);
        Object mage = buildMage("Ted", 10, 5, 2, 2);
        callVoid(mage, "takeDamage", 1);
        Assert.assertEquals(10, call(mage, "getHitPoints"));
        Assert.assertEquals(3, call(mage, "getMagicPoints"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void takeDamage4() throws Exception
    {
        String category = "takeDamage";
        recordTotal(category);
        Object mage = buildMage("Mira", 20, 10, 5, 4);
        callVoid(mage, "takeDamage", 4);
        Assert.assertEquals(20, call(mage, "getHitPoints"));
        Assert.assertEquals(5, call(mage, "getMagicPoints"));
        recordPass(category);
    }

    @Test(timeout = 250)
    public void takeDamage5() throws Exception
    {
        String category = "takeDamage";
        recordTotal(category);
        Object mage = buildMage("Orin", 50, 20, 5, 3);
        callVoid(mage, "takeDamage", 10);
        callVoid(mage, "takeDamage", 10);
        Assert.assertEquals(36, call(mage, "getHitPoints"));
        Assert.assertEquals(10, call(mage, "getMagicPoints"));
        recordPass(category);
    }

    @AfterClass
    public static void tearDownClass()
    {
        double earned = 0.0;

        System.out.println();
        System.out.println("Category                        Passed/Total    Points");
        System.out.println("---------------------------------------------------------------");

        for(Map.Entry<String, double[]> entry : Scores.grading.entrySet())
        {
            String category = entry.getKey();
            double[] values = entry.getValue();
            double categoryPossible = values[0];
            double passed = values[1];
            double total = values[2];
            double categoryEarned = total == 0.0 ? 0.0 : categoryPossible * (passed / total);

            earned += categoryEarned;

            System.out.printf("%-28s %4.0f/%-7.0f %6.2f / %.2f%n", category, passed, total, categoryEarned, categoryPossible);
        }

        System.out.println("---------------------------------------------------------------");
        System.out.printf("Grade = %.2f%n", earned);
    }
}
