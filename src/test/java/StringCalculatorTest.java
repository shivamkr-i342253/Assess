import org.junit.Test;
import org.junit.Assert;

public class StringCalculatorTest {
    @Test
    public void name() {
        StringCalculator stringCalculator = new StringCalculator();
        int charDigitSum = stringCalculator.Add("");
        Assert.assertEquals(0, charDigitSum);
    }
}