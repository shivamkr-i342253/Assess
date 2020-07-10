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
}