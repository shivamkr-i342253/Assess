import org.junit.Test;
import org.junit.Assert;

public class StringCalculatorTest {
    @Test
    public void task1AndTask2() {
        StringCalculator stringCalculator = new StringCalculator();
        int charDigitSum = stringCalculator.Add("");
        Assert.assertEquals(0, charDigitSum);
        Assert.assertEquals(1, stringCalculator.Add("1"));
        Assert.assertEquals(0, stringCalculator.Add(","));
        Assert.assertEquals(1, stringCalculator.Add("1,"));
        Assert.assertEquals(2, stringCalculator.Add(",2"));
        Assert.assertEquals(3, stringCalculator.Add(",1,2,"));
        Assert.assertEquals(3, stringCalculator.Add(",,,1,,,,2,,,,"));
    }
    @Test
    public void task3() {
        StringCalculator stringCalculator = new StringCalculator();
        Assert.assertEquals(-1, stringCalculator.Add("\n"));
        Assert.assertEquals(4, stringCalculator.Add("4\n"));
        Assert.assertEquals(-1, stringCalculator.Add("4,\n"));
        Assert.assertEquals(5, stringCalculator.Add(",3,2\n"));
        Assert.assertEquals(27, stringCalculator.Add(",,,,,\n1\n2\n,,,,\n7\n5,,3\n9\n,,,"));
    }

    @Test
    public void task4() {
        StringCalculator stringCalculator = new StringCalculator();
        Assert.assertEquals(-3, stringCalculator.Add("//;\n1;2"));
    }
}