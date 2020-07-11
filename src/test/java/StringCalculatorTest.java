import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringCalculatorTest {
    @Test
    public void task1AndTask2() {
        StringCalculator stringCalculator = new StringCalculator();
        int charDigitSum = stringCalculator.Add("");
        assertEquals(0, charDigitSum);
        assertEquals(1, stringCalculator.Add("1"));
        assertEquals(0, stringCalculator.Add(","));
        assertEquals(1, stringCalculator.Add("1,"));
        assertEquals(2, stringCalculator.Add(",2"));
        assertEquals(3, stringCalculator.Add(",1,2,"));
        assertEquals(3, stringCalculator.Add(",,,1,,,,2,,,,"));
    }
    @Test
    public void task3() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(-1, stringCalculator.Add("\n"));
        assertEquals(4, stringCalculator.Add("4\n"));
        assertEquals(-1, stringCalculator.Add("4,\n"));
        assertEquals(5, stringCalculator.Add(",3,2\n"));
        assertEquals(27, stringCalculator.Add(",,,,,\n1\n2\n,,,,\n7\n5,,3\n9\n,,,"));
    }

    @Test
    public void task4() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(-1, stringCalculator.Add("//;\n"));
        assertEquals(7, stringCalculator.Add("//;\n7"));
        assertEquals(54, stringCalculator.Add("//;\n43\n5\n;6"));
    }

    @Test
    public void task5() {
        StringCalculator stringCalculator = new StringCalculator();
        stringCalculator.Add("//;-7\n;-2\n2\n8\n\n;-9");
        assertEquals("negatives not allowed  -7 -2 -9", "negatives not allowed "+stringCalculator.negatives);
    }
}