import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
public class Student_Tests
{
    public static class Scores
    {
        public static LinkedHashMap<String, double[]> grading = new
                LinkedHashMap<String, double[]>();
        static
        {
            grading.put("constructorAttributes", new double[]{15.0, 0.0, 0.0});
            grading.put("getID", new double[]{10.0, 0.0, 0.0});
            grading.put("toString", new double[]{10.0, 0.0, 0.0});
            grading.put("equals", new double[]{15.0, 0.0, 0.0});
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
    private Object buildStudent(String firstName, String lastName, int id) throws
            Exception
    {
        Class<?> classRef = Class.forName(generateClassName("Student"));
        Object student = classRef.getConstructor(String.class, String.class,
                int.class).newInstance(firstName, lastName, id);
        return student;
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
    private boolean callEquals(Object left, Object right) throws Exception
    {
        Method method = left.getClass().getMethod("equals", Object.class);
        try
        {
            return (Boolean) method.invoke(left, right);
        }
        catch(InvocationTargetException e)
        {
            throw (Exception) e.getCause();
        }
    }
    private void assertStudentAttributes(Object student, String expectedFirstName,
                                         String expectedLastName, int expectedID) throws Exception
    {
        Assert.assertTrue(student instanceof Person);
        Assert.assertEquals(expectedFirstName, call(student, "getFirstName"));
        Assert.assertEquals(expectedLastName, call(student, "getLastName"));
        Assert.assertEquals(expectedID, call(student, "getID"));
    }
    @Test(timeout = 250)
    public void constructorAttributes1() throws Exception
    {
        String category = "constructorAttributes";
        recordTotal(category);
        Object student = buildStudent("Billy", "Smith", 5);
        assertStudentAttributes(student, "Billy", "Smith", 5);
        recordPass(category);
    }
    @Test(timeout = 250)
    public void constructorAttributes2() throws Exception
    {
        String category = "constructorAttributes";
        recordTotal(category);
        Object student = buildStudent("Tina", "Fey", 1);
        assertStudentAttributes(student, "Tina", "Fey", 1);
        recordPass(category);
    }
    @Test(timeout = 250)
    public void constructorAttributes3() throws Exception
    {
        String category = "constructorAttributes";
        recordTotal(category);
        Object student = buildStudent("Jane", "Adams", 42);
        assertStudentAttributes(student, "Jane", "Adams", 42);
        recordPass(category);
    }
    @Test(timeout = 250)
    public void constructorAttributes4() throws Exception
    {
        String category = "constructorAttributes";
        recordTotal(category);
        Object student = buildStudent("Alex", "Rivera", 100);
        assertStudentAttributes(student, "Alex", "Rivera", 100);
        recordPass(category);
    }
    @Test(timeout = 250)
    public void constructorAttributes5() throws Exception
    {
        String category = "constructorAttributes";
        recordTotal(category);
        Object student = buildStudent("Sam", "Lee", 0);
        assertStudentAttributes(student, "Sam", "Lee", 0);
        recordPass(category);
    }
    @Test(timeout = 250)
    public void getID1() throws Exception
    {
        String category = "getID";
        recordTotal(category);
        Object student = buildStudent("Billy", "Smith", 5);
        Assert.assertEquals(5, call(student, "getID"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void getID2() throws Exception
    {
        String category = "getID";
        recordTotal(category);
        Object student = buildStudent("Tina", "Fey", 1);
        Assert.assertEquals(1, call(student, "getID"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void getID3() throws Exception
    {
        String category = "getID";
        recordTotal(category);
        Object student = buildStudent("Jane", "Adams", 42);
        Assert.assertEquals(42, call(student, "getID"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void getID4() throws Exception
    {
        String category = "getID";
        recordTotal(category);
        Object student = buildStudent("Alex", "Rivera", 100);
        Assert.assertEquals(100, call(student, "getID"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void getID5() throws Exception
    {
        String category = "getID";
        recordTotal(category);
        Object student = buildStudent("Sam", "Lee", 0);
        Assert.assertEquals(0, call(student, "getID"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void toString1() throws Exception
    {
        String category = "toString";
        recordTotal(category);
        Object student = buildStudent("Billy", "Smith", 5);
        Assert.assertEquals("5 - Smith, Billy", call(student, "toString"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void toString2() throws Exception
    {
        String category = "toString";
        recordTotal(category);
        Object student = buildStudent("Tina", "Fey", 1);
        Assert.assertEquals("1 - Fey, Tina", call(student, "toString"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void toString3() throws Exception
    {
        String category = "toString";
        recordTotal(category);
        Object student = buildStudent("Jane", "Adams", 42);
        Assert.assertEquals("42 - Adams, Jane", call(student, "toString"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void toString4() throws Exception
    {
        String category = "toString";
        recordTotal(category);
        Object student = buildStudent("Alex", "Rivera", 100);
        Assert.assertEquals("100 - Rivera, Alex", call(student, "toString"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void toString5() throws Exception
    {
        String category = "toString";
        recordTotal(category);
        Object student = buildStudent("Sam", "Lee", 0);
        Assert.assertEquals("0 - Lee, Sam", call(student, "toString"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void equals1() throws Exception
    {
        String category = "equals";
        recordTotal(category);
        Object first = buildStudent("Billy", "Smith", 5);
        Object second = buildStudent("Billy", "Smith", 5);
        Assert.assertEquals(true, callEquals(first, second));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void equals2() throws Exception
    {
        String category = "equals";
        recordTotal(category);
        Object first = buildStudent("Billy", "Smith", 5);
        Object second = buildStudent("Billy", "Smith", 6);
        Assert.assertEquals(false, callEquals(first, second));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void equals3() throws Exception
    {
        String category = "equals";
        recordTotal(category);
        Object first = buildStudent("Billy", "Smith", 5);
        Object second = buildStudent("Jane", "Smith", 5);
        Assert.assertEquals(false, callEquals(first, second));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void equals4() throws Exception
    {
        String category = "equals";
        recordTotal(category);
        Object first = buildStudent("Billy", "Smith", 5);
        Object second = buildStudent("Billy", "Jones", 5);
        Assert.assertEquals(false, callEquals(first, second));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void equals5() throws Exception
    {
        String category = "equals";
        recordTotal(category);
        Object first = buildStudent("Billy", "Smith", 5);
        String second = "Billy Smith";
        Assert.assertEquals(false, callEquals(first, second));
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
