import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
public class ZombieDice_Class_Tests
{
    public static class Scores
    {
        public static LinkedHashMap<String, double[]> grading = new
                LinkedHashMap<String, double[]>();
        static
        {
            grading.put("ZombieDieConstructor", new double[]{2.0, 0.0, 0.0});
            grading.put("RedZombieDieConstructor", new double[]{2.0, 0.0, 0.0});
            grading.put("YellowZombieDieConstructor", new double[]{2.0, 0.0, 0.0});
            grading.put("GreenZombieDieConstructor", new double[]{2.0, 0.0, 0.0});
            grading.put("ZombieDiceBucketConstructor", new double[]{2.0, 0.0,
                    0.0});
            grading.put("getValue", new double[]{1.0, 0.0, 0.0});
            grading.put("getDieColor", new double[]{1.0, 0.0, 0.0});
            grading.put("setValue", new double[]{3.0, 0.0, 0.0});
            grading.put("toString", new double[]{3.0, 0.0, 0.0});
            grading.put("RedZombieDieRoll", new double[]{2.0, 0.0, 0.0});
            grading.put("YellowZombieDieRoll", new double[]{2.0, 0.0, 0.0});
            grading.put("GreenZombieDieRoll", new double[]{2.0, 0.0, 0.0});
            grading.put("loadBucket", new double[]{3.0, 0.0, 0.0});
            grading.put("draw", new double[]{3.0, 0.0, 0.0});
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
    private Object buildZombieDie(int color) throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("ZombieDie"));
        Constructor<?> constructor = classRef.getConstructor(int.class);
        Object die = constructor.newInstance(color);
        return die;
    }
    private Object buildColorDie(String className) throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName(className));
        Object die = classRef.getConstructor().newInstance();
        return die;
    }
    private Object buildBucket() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("ZombieDiceBucket"));
        Object bucket = classRef.getConstructor().newInstance();
        return bucket;
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
    private void callVoid(Object object, String methodName) throws Exception
    {
        Method method = object.getClass().getMethod(methodName);
        try
        {
            method.invoke(object);
        }
        catch(InvocationTargetException e)
        {
            throw (Exception) e.getCause();
        }
    }
    private void setValue(Object object, int value) throws Exception
    {
        Method method = object.getClass().getMethod("setValue", int.class);
        try
        {
            method.invoke(object, value);
        }
        catch(InvocationTargetException e)
        {
            throw (Exception) e.getCause();
        }
    }
    private Object draw(Object bucket) throws Exception
    {
        Method method = bucket.getClass().getMethod("draw");
        try
        {
            return method.invoke(bucket);
        }
        catch(InvocationTargetException e)
        {
            throw (Exception) e.getCause();
        }
    }
    private void loadBucket(Object bucket) throws Exception
    {
        Method method = bucket.getClass().getMethod("loadBucket");
        try
        {
            method.invoke(bucket);
        }
        catch(InvocationTargetException e)
        {
            throw (Exception) e.getCause();
        }
    }
    private void assertValueInRangeAfterRoll(Object die) throws Exception
    {
        for(int i = 0; i < 25; i++)
        {
            callVoid(die, "roll");
            int value = (Integer) call(die, "getValue");
            Assert.assertTrue(value >= 1);
            Assert.assertTrue(value <= 3);
        }
    }
    private int[] drawAllDiceColorCounts(Object bucket) throws Exception
    {
        int[] counts = new int[4];
        for(int i = 0; i < 13; i++)
        {
            Object die = draw(bucket);
            Assert.assertNotNull(die);
            int color = (Integer) call(die, "getDieColor");
            counts[color]++;
        }
        Object extra = draw(bucket);
        Assert.assertNull(extra);
        return counts;
    }
    @Test(timeout = 250)
    public void ZombieDieConstructor1() throws Exception
    {
        String category = "ZombieDieConstructor";
        recordTotal(category);
        Object die = buildZombieDie(1);
        Assert.assertEquals(1, call(die, "getDieColor"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void ZombieDieConstructor2() throws Exception
    {
        String category = "ZombieDieConstructor";
        recordTotal(category);
        Object die = buildZombieDie(2);
        Assert.assertEquals(2, call(die, "getDieColor"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void ZombieDieConstructor3() throws Exception
    {
        String category = "ZombieDieConstructor";
        recordTotal(category);
        Object die = buildZombieDie(3);
        Assert.assertEquals(3, call(die, "getDieColor"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void ZombieDieConstructor4() throws Exception
    {
        String category = "ZombieDieConstructor";
        recordTotal(category);
        Object die = buildZombieDie(1);
        Assert.assertEquals(0, call(die, "getValue"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void ZombieDieConstructor5() throws Exception
    {
        String category = "ZombieDieConstructor";
        recordTotal(category);
        Object die = buildZombieDie(2);
        Assert.assertEquals("Green", call(die, "toString"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void RedZombieDieConstructor1() throws Exception
    {
        String category = "RedZombieDieConstructor";
        recordTotal(category);
        Object die = buildColorDie("RedZombieDie");
        Assert.assertEquals(1, call(die, "getDieColor"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void RedZombieDieConstructor2() throws Exception
    {
        String category = "RedZombieDieConstructor";
        recordTotal(category);
        Object die = buildColorDie("RedZombieDie");
        Assert.assertEquals("Red", call(die, "toString"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void RedZombieDieConstructor3() throws Exception
    {
        String category = "RedZombieDieConstructor";
        recordTotal(category);
        Object die = buildColorDie("RedZombieDie");
        Assert.assertTrue(die instanceof ZombieDie);
        recordPass(category);
    }
    @Test(timeout = 250)
    public void RedZombieDieConstructor4() throws Exception
    {
        String category = "RedZombieDieConstructor";
        recordTotal(category);
        Object die = buildColorDie("RedZombieDie");
        Assert.assertEquals(0, call(die, "getValue"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void RedZombieDieConstructor5() throws Exception
    {
        String category = "RedZombieDieConstructor";
        recordTotal(category);
        Object die = buildColorDie("RedZombieDie");
        setValue(die, 2);
        Assert.assertEquals("Red-Brain", call(die, "toString"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void YellowZombieDieConstructor1() throws Exception
    {
        String category = "YellowZombieDieConstructor";
        recordTotal(category);
        Object die = buildColorDie("YellowZombieDie");
        Assert.assertEquals(3, call(die, "getDieColor"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void YellowZombieDieConstructor2() throws Exception
    {
        String category = "YellowZombieDieConstructor";
        recordTotal(category);
        Object die = buildColorDie("YellowZombieDie");
        Assert.assertEquals("Yellow", call(die, "toString"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void YellowZombieDieConstructor3() throws Exception
    {
        String category = "YellowZombieDieConstructor";
        recordTotal(category);
        Object die = buildColorDie("YellowZombieDie");
        Assert.assertTrue(die instanceof ZombieDie);
        recordPass(category);
    }
    @Test(timeout = 250)
    public void YellowZombieDieConstructor4() throws Exception
    {
        String category = "YellowZombieDieConstructor";
        recordTotal(category);
        Object die = buildColorDie("YellowZombieDie");
        Assert.assertEquals(0, call(die, "getValue"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void YellowZombieDieConstructor5() throws Exception
    {
        String category = "YellowZombieDieConstructor";
        recordTotal(category);
        Object die = buildColorDie("YellowZombieDie");
        setValue(die, 3);
        Assert.assertEquals("Yellow-Shot", call(die, "toString"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void GreenZombieDieConstructor1() throws Exception
    {
        String category = "GreenZombieDieConstructor";
        recordTotal(category);
        Object die = buildColorDie("GreenZombieDie");
        Assert.assertEquals(2, call(die, "getDieColor"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void GreenZombieDieConstructor2() throws Exception
    {
        String category = "GreenZombieDieConstructor";
        recordTotal(category);
        Object die = buildColorDie("GreenZombieDie");
        Assert.assertEquals("Green", call(die, "toString"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void GreenZombieDieConstructor3() throws Exception
    {
        String category = "GreenZombieDieConstructor";
        recordTotal(category);
        Object die = buildColorDie("GreenZombieDie");
        Assert.assertTrue(die instanceof ZombieDie);
        recordPass(category);
    }
    @Test(timeout = 250)
    public void GreenZombieDieConstructor4() throws Exception
    {
        String category = "GreenZombieDieConstructor";
        recordTotal(category);
        Object die = buildColorDie("GreenZombieDie");
        Assert.assertEquals(0, call(die, "getValue"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void GreenZombieDieConstructor5() throws Exception
    {
        String category = "GreenZombieDieConstructor";
        recordTotal(category);
        Object die = buildColorDie("GreenZombieDie");
        setValue(die, 1);
        Assert.assertEquals("Green-Runner", call(die, "toString"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void ZombieDiceBucketConstructor1() throws Exception
    {
        String category = "ZombieDiceBucketConstructor";
        recordTotal(category);
        Object bucket = buildBucket();
        int[] counts = drawAllDiceColorCounts(bucket);
        Assert.assertEquals(3, counts[1]);
        Assert.assertEquals(6, counts[2]);
        Assert.assertEquals(4, counts[3]);
        recordPass(category);
    }
    @Test(timeout = 250)
    public void ZombieDiceBucketConstructor2() throws Exception
    {
        String category = "ZombieDiceBucketConstructor";
        recordTotal(category);
        Object bucket = buildBucket();
        Object die = draw(bucket);
        Assert.assertNotNull(die);
        recordPass(category);
    }
    @Test(timeout = 250)
    public void ZombieDiceBucketConstructor3() throws Exception
    {
        String category = "ZombieDiceBucketConstructor";
        recordTotal(category);
        Object bucket = buildBucket();
        for(int i = 0; i < 13; i++)
        {
            draw(bucket);
        }
        Assert.assertNull(draw(bucket));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void ZombieDiceBucketConstructor4() throws Exception
    {
        String category = "ZombieDiceBucketConstructor";
        recordTotal(category);
        Object bucket = buildBucket();
        int drawn = 0;
        while(draw(bucket) != null)
        {
            drawn++;
        }
        Assert.assertEquals(13, drawn);
        recordPass(category);
    }
    @Test(timeout = 250)
    public void ZombieDiceBucketConstructor5() throws Exception
    {
        String category = "ZombieDiceBucketConstructor";
        recordTotal(category);
        Object bucket = buildBucket();
        int[] counts = drawAllDiceColorCounts(bucket);
        Assert.assertEquals(13, counts[1] + counts[2] + counts[3]);
        recordPass(category);
    }
    @Test(timeout = 250)
    public void getValue1() throws Exception
    {
        String category = "getValue";
        recordTotal(category);
        Object die = buildZombieDie(1);
        setValue(die, 0);
        Assert.assertEquals(0, call(die, "getValue"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void getValue2() throws Exception
    {
        String category = "getValue";
        recordTotal(category);
        Object die = buildZombieDie(1);
        setValue(die, 1);
        Assert.assertEquals(1, call(die, "getValue"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void getValue3() throws Exception
    {
        String category = "getValue";
        recordTotal(category);
        Object die = buildZombieDie(1);
        setValue(die, 2);
        Assert.assertEquals(2, call(die, "getValue"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void getValue4() throws Exception
    {
        String category = "getValue";
        recordTotal(category);
        Object die = buildZombieDie(1);
        setValue(die, 3);
        Assert.assertEquals(3, call(die, "getValue"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void getValue5() throws Exception
    {
        String category = "getValue";
        recordTotal(category);
        Object die = buildZombieDie(2);
        Assert.assertEquals(0, call(die, "getValue"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void getDieColor1() throws Exception
    {
        String category = "getDieColor";
        recordTotal(category);
        Object die = buildZombieDie(1);
        Assert.assertEquals(1, call(die, "getDieColor"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void getDieColor2() throws Exception
    {
        String category = "getDieColor";
        recordTotal(category);
        Object die = buildZombieDie(2);
        Assert.assertEquals(2, call(die, "getDieColor"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void getDieColor3() throws Exception
    {
        String category = "getDieColor";
        recordTotal(category);
        Object die = buildZombieDie(3);
        Assert.assertEquals(3, call(die, "getDieColor"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void getDieColor4() throws Exception
    {
        String category = "getDieColor";
        recordTotal(category);
        Object die = buildColorDie("RedZombieDie");
        Assert.assertEquals(1, call(die, "getDieColor"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void getDieColor5() throws Exception
    {
        String category = "getDieColor";
        recordTotal(category);
        Object die = buildColorDie("GreenZombieDie");
        Assert.assertEquals(2, call(die, "getDieColor"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void setValue1() throws Exception
    {
        String category = "setValue";
        recordTotal(category);
        Object die = buildZombieDie(1);
        setValue(die, 0);
        Assert.assertEquals(0, call(die, "getValue"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void setValue2() throws Exception
    {
        String category = "setValue";
        recordTotal(category);
        Object die = buildZombieDie(1);
        setValue(die, 1);
        Assert.assertEquals(1, call(die, "getValue"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void setValue3() throws Exception
    {
        String category = "setValue";
        recordTotal(category);
        Object die = buildZombieDie(1);
        setValue(die, 2);
        Assert.assertEquals(2, call(die, "getValue"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void setValue4() throws Exception
    {
        String category = "setValue";
        recordTotal(category);
        Object die = buildZombieDie(1);
        setValue(die, 3);
        Assert.assertEquals(3, call(die, "getValue"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void setValue5() throws Exception
    {
        String category = "setValue";
        recordTotal(category);
        Object die = buildZombieDie(2);
        setValue(die, 2);
        Assert.assertEquals("Green-Brain", call(die, "toString"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void toString1() throws Exception
    {
        String category = "toString";
        recordTotal(category);
        Object die = buildZombieDie(1);
        Assert.assertEquals("Red", call(die, "toString"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void toString2() throws Exception
    {
        String category = "toString";
        recordTotal(category);
        Object die = buildZombieDie(2);
        Assert.assertEquals("Green", call(die, "toString"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void toString3() throws Exception
    {
        String category = "toString";
        recordTotal(category);
        Object die = buildZombieDie(3);
        Assert.assertEquals("Yellow", call(die, "toString"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void toString4() throws Exception
    {
        String category = "toString";
        recordTotal(category);
        Object die = buildZombieDie(1);
        setValue(die, 1);
        Assert.assertEquals("Red-Runner", call(die, "toString"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void toString5() throws Exception
    {
        String category = "toString";
        recordTotal(category);
        Object die = buildZombieDie(3);
        setValue(die, 3);
        Assert.assertEquals("Yellow-Shot", call(die, "toString"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void RedZombieDieRoll1() throws Exception
    {
        String category = "RedZombieDieRoll";
        recordTotal(category);
        Object die = buildColorDie("RedZombieDie");
        assertValueInRangeAfterRoll(die);
        recordPass(category);
    }
    @Test(timeout = 250)
    public void RedZombieDieRoll2() throws Exception
    {
        String category = "RedZombieDieRoll";
        recordTotal(category);
        Object die = buildColorDie("RedZombieDie");
        callVoid(die, "roll");
        int value = (Integer) call(die, "getValue");
        Assert.assertTrue(value >= 1 && value <= 3);
        recordPass(category);
    }
    @Test(timeout = 250)
    public void RedZombieDieRoll3() throws Exception
    {
        String category = "RedZombieDieRoll";
        recordTotal(category);
        Object die = buildColorDie("RedZombieDie");
        assertValueInRangeAfterRoll(die);
        Assert.assertEquals(1, call(die, "getDieColor"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void RedZombieDieRoll4() throws Exception
    {
        String category = "RedZombieDieRoll";
        recordTotal(category);
        Object die = buildColorDie("RedZombieDie");
        callVoid(die, "roll");
        String text = (String) call(die, "toString");
        Assert.assertTrue(text.startsWith("Red-"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void RedZombieDieRoll5() throws Exception
    {
        String category = "RedZombieDieRoll";
        recordTotal(category);
        Object die = buildColorDie("RedZombieDie");
        assertValueInRangeAfterRoll(die);
        String text = (String) call(die, "toString");
        Assert.assertTrue(text.equals("Red-Runner") || text.equals("Red-Brain") ||
                text.equals("Red-Shot"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void YellowZombieDieRoll1() throws Exception
    {
        String category = "YellowZombieDieRoll";
        recordTotal(category);
        Object die = buildColorDie("YellowZombieDie");
        assertValueInRangeAfterRoll(die);
        recordPass(category);
    }
    @Test(timeout = 250)
    public void YellowZombieDieRoll2() throws Exception
    {
        String category = "YellowZombieDieRoll";
        recordTotal(category);
        Object die = buildColorDie("YellowZombieDie");
        callVoid(die, "roll");
        int value = (Integer) call(die, "getValue");
        Assert.assertTrue(value >= 1 && value <= 3);
        recordPass(category);
    }
    @Test(timeout = 250)
    public void YellowZombieDieRoll3() throws Exception
    {
        String category = "YellowZombieDieRoll";
        recordTotal(category);
        Object die = buildColorDie("YellowZombieDie");
        assertValueInRangeAfterRoll(die);
        Assert.assertEquals(3, call(die, "getDieColor"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void YellowZombieDieRoll4() throws Exception
    {
        String category = "YellowZombieDieRoll";
        recordTotal(category);
        Object die = buildColorDie("YellowZombieDie");
        callVoid(die, "roll");
        String text = (String) call(die, "toString");
        Assert.assertTrue(text.startsWith("Yellow-"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void YellowZombieDieRoll5() throws Exception
    {
        String category = "YellowZombieDieRoll";
        recordTotal(category);
        Object die = buildColorDie("YellowZombieDie");
        assertValueInRangeAfterRoll(die);
        String text = (String) call(die, "toString");
        Assert.assertTrue(text.equals("Yellow-Runner") || text.equals("Yellow- Brain") || text.equals("Yellow-Shot"));
                recordPass(category);
    }
    @Test(timeout = 250)
    public void GreenZombieDieRoll1() throws Exception
    {
        String category = "GreenZombieDieRoll";
        recordTotal(category);
        Object die = buildColorDie("GreenZombieDie");
        assertValueInRangeAfterRoll(die);
        recordPass(category);
    }
    @Test(timeout = 250)
    public void GreenZombieDieRoll2() throws Exception
    {
        String category = "GreenZombieDieRoll";
        recordTotal(category);
        Object die = buildColorDie("GreenZombieDie");
        callVoid(die, "roll");
        int value = (Integer) call(die, "getValue");
        Assert.assertTrue(value >= 1 && value <= 3);
        recordPass(category);
    }
    @Test(timeout = 250)
    public void GreenZombieDieRoll3() throws Exception
    {
        String category = "GreenZombieDieRoll";
        recordTotal(category);
        Object die = buildColorDie("GreenZombieDie");
        assertValueInRangeAfterRoll(die);
        Assert.assertEquals(2, call(die, "getDieColor"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void GreenZombieDieRoll4() throws Exception
    {
        String category = "GreenZombieDieRoll";
        recordTotal(category);
        Object die = buildColorDie("GreenZombieDie");
        callVoid(die, "roll");
        String text = (String) call(die, "toString");
        Assert.assertTrue(text.startsWith("Green-"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void GreenZombieDieRoll5() throws Exception
    {
        String category = "GreenZombieDieRoll";
        recordTotal(category);
        Object die = buildColorDie("GreenZombieDie");
        assertValueInRangeAfterRoll(die);
        String text = (String) call(die, "toString");
        Assert.assertTrue(text.equals("Green-Runner") || text.equals("Green-Brain")
                || text.equals("Green-Shot"));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void loadBucket1() throws Exception
    {
        String category = "loadBucket";
        recordTotal(category);
        Object bucket = buildBucket();
        loadBucket(bucket);
        int[] counts = drawAllDiceColorCounts(bucket);
        Assert.assertEquals(3, counts[1]);
        Assert.assertEquals(6, counts[2]);
        Assert.assertEquals(4, counts[3]);
        recordPass(category);
    }
    @Test(timeout = 250)
    public void loadBucket2() throws Exception
    {
        String category = "loadBucket";
        recordTotal(category);
        Object bucket = buildBucket();
        for(int i = 0; i < 13; i++)
        {
            draw(bucket);
        }
        loadBucket(bucket);
        int[] counts = drawAllDiceColorCounts(bucket);
        Assert.assertEquals(13, counts[1] + counts[2] + counts[3]);
        recordPass(category);
    }
    @Test(timeout = 250)
    public void loadBucket3() throws Exception
    {
        String category = "loadBucket";
        recordTotal(category);
        Object bucket = buildBucket();
        draw(bucket);
        draw(bucket);
        loadBucket(bucket);
        int drawn = 0;
        while(draw(bucket) != null)
        {
            drawn++;
        }
        Assert.assertEquals(13, drawn);
        recordPass(category);
    }
    @Test(timeout = 250)
    public void loadBucket4() throws Exception
    {
        String category = "loadBucket";
        recordTotal(category);
        Object bucket = buildBucket();
        loadBucket(bucket);
        Object first = draw(bucket);
        Assert.assertNotNull(first);
        recordPass(category);
    }
    @Test(timeout = 250)
    public void loadBucket5() throws Exception
    {
        String category = "loadBucket";
        recordTotal(category);
        Object bucket = buildBucket();
        for(int i = 0; i < 13; i++)
        {
            draw(bucket);
        }
        Assert.assertNull(draw(bucket));
        loadBucket(bucket);
        Assert.assertNotNull(draw(bucket));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void draw1() throws Exception
    {
        String category = "draw";
        recordTotal(category);
        Object bucket = buildBucket();
        Object die = draw(bucket);
        Assert.assertNotNull(die);
        recordPass(category);
    }
    @Test(timeout = 250)
    public void draw2() throws Exception
    {
        String category = "draw";
        recordTotal(category);
        Object bucket = buildBucket();
        int drawn = 0;
        while(draw(bucket) != null)
        {
            drawn++;
        }
        Assert.assertEquals(13, drawn);
        recordPass(category);
    }
    @Test(timeout = 250)
    public void draw3() throws Exception
    {
        String category = "draw";
        recordTotal(category);
        Object bucket = buildBucket();
        for(int i = 0; i < 13; i++)
        {
            draw(bucket);
        }
        Assert.assertNull(draw(bucket));
        recordPass(category);
    }
    @Test(timeout = 250)
    public void draw4() throws Exception
    {
        String category = "draw";
        recordTotal(category);
        Object bucket = buildBucket();
        int[] counts = drawAllDiceColorCounts(bucket);
        Assert.assertEquals(3, counts[1]);
        Assert.assertEquals(6, counts[2]);
        Assert.assertEquals(4, counts[3]);
        recordPass(category);
    }
    @Test(timeout = 250)
    public void draw5() throws Exception
    {
        String category = "draw";
        recordTotal(category);
        Object bucket = buildBucket();
        Object die = draw(bucket);
        int color = (Integer) call(die, "getDieColor");
        Assert.assertTrue(color == 1 || color == 2 || color == 3);
        recordPass(category);
    }
    @AfterClass
    public static void tearDownClass()
    {
        double earned = 0.0;
        System.out.println();
        System.out.println("Category Passed/Total Points");
        System.out.println("---------------------------------------------------");
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
            System.out.printf("%-28s %4.0f/%-7.0f %6.2f / %.2f%n", category,
                    passed, total, categoryEarned, categoryPossible);
        }
        System.out.println("---------------------------------------------------");
        System.out.printf("Grade = %.2f%n", earned);
    }
}