import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringCalculatorTest {
    @Test
//    Create a simple String calculator
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
//    handle new lines between numbers (instead of commas) - ok: “1\n2,3” ; NOT ok: “1,\n”
    public void task3() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(-1, stringCalculator.Add("\n"));
        assertEquals(4, stringCalculator.Add("4\n"));
        assertEquals(-1, stringCalculator.Add("4,\n"));
        assertEquals(5, stringCalculator.Add(",3,2\n"));
        assertEquals(27, stringCalculator.Add(",,,,,\n1\n2\n,,,,\n7\n5,,3\n9\n,,,"));
    }

    @Test
//    Support different delimiters
    public void task4() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(-1, stringCalculator.Add("//;\n"));
        assertEquals(7, stringCalculator.Add("//;\n7"));
        assertEquals(54, stringCalculator.Add("//;\n43\n5\n;6"));
        assertEquals(-1, stringCalculator.Add("//%\n"));
        assertEquals(-1, stringCalculator.Add("//#\n"));
        assertEquals(54, stringCalculator.Add("//#\n43\n5\n#6"));
        assertEquals(36, stringCalculator.Add("//%\n4%%%\n17%1%3\n5\n6%%"));
//        assertEquals(35, stringCalculator.Add("//^\n4^^^\n17^1001^3\n5\n6^^"));
        assertEquals(35, stringCalculator.Add("//&\n4&&&\n17&1001&3\n5\n6&&"));
        assertEquals(35, stringCalculator.Add("//$\n4$$$\n17$1001$3\n5\n6$$"));
        assertEquals(35, stringCalculator.Add("//*\n4***\n17*1001*3\n5\n6**"));
        assertEquals(35, stringCalculator.Add("//+\n4+++\n17+1001+3\n5\n6++"));
        assertEquals(4, stringCalculator.Add("//.\n4"));
    }

    @Test
//    throw an exception “negatives not allowed” - and the negative that was passed.
    public void task5() {
        try {
            StringCalculator stringCalculator = new StringCalculator();
            stringCalculator.Add("//;-7\n");
            throw new StringCalculator.NegativesNotAllowedException("negatives not allowed "+stringCalculator.negatives);
        } catch (StringCalculator.NegativesNotAllowedException e) {
            assertEquals("negatives not allowed  -7", e.getMessage());
        }
        try {
            StringCalculator stringCalculator = new StringCalculator();
            stringCalculator.Add("//;-7\n;-2\n2\n8\n\n;-9");
            throw new StringCalculator.NegativesNotAllowedException("negatives not allowed "+stringCalculator.negatives);
        } catch (StringCalculator.NegativesNotAllowedException e) {
            assertEquals("negatives not allowed  -7 -2 -9", e.getMessage());
        }
        try {
            StringCalculator stringCalculator = new StringCalculator();
            stringCalculator.Add("//;-7\n;-2\n2\n8\n\n;-9;;;\n\n-53\n-1\n");
            throw new StringCalculator.NegativesNotAllowedException("negatives not allowed "+stringCalculator.negatives);
        } catch (StringCalculator.NegativesNotAllowedException e) {
            assertEquals("negatives not allowed  -7 -2 -9 -53 -1", e.getMessage());
        }
    }

    @Test
//    Numbers bigger than 1000 should be ignored
    public void task6() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(-1, stringCalculator.Add("//;\n"));
        assertEquals(0, stringCalculator.Add("//@\n1001"));
        assertEquals(11, stringCalculator.Add("//!\n4553\n5\n!6"));
        assertEquals(1035, stringCalculator.Add("//%\n4%%%\n17%1000%3\n5\n6%%"));
//        assertEquals(35, stringCalculator.Add("//^\n4^^^\n17^1001^3\n5\n6^^"));
        assertEquals(35, stringCalculator.Add("//&\n4&&&\n17&1001&3\n5\n6&&"));
        assertEquals(35, stringCalculator.Add("//$\n4$$$\n17$1001$3\n5\n6$$"));
        assertEquals(35, stringCalculator.Add("//*\n4***\n17*1001*3\n5\n6**"));
        assertEquals(35, stringCalculator.Add("//+\n4+++\n17+1001+3\n5\n6++"));
        assertEquals(4, stringCalculator.Add("//.\n4"));
    }

    @Test
//    Delimiters can be of any length “//[***]\n1***2***3”
    public void task7() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(-1, stringCalculator.Add("//***\n"));
        assertEquals(6, stringCalculator.Add("//[***]\n1***2***3"));
        assertEquals(35, stringCalculator.Add("//[$$]\n4$$\n17$$1001$$3\n5\n6$$"));
//        assertEquals(6, stringCalculator.Add("//[^^^]\n1^^^2^^^3"));
        assertEquals(18, stringCalculator.Add("//[!!!]\n4553\n5\n!!!6\n\n!!!\n4!!!3"));
    }

    @Test
//    multiple delimiters “//[*][%]\n1*2%3”
    public void task8() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(24, stringCalculator.Add("//[;]\n1;23"));
        assertEquals(6, stringCalculator.Add("//[;][%]\n1;2%3"));
        assertEquals(19, stringCalculator.Add("//[;][%][&][*]\n1;2%3&\n\n6\n*\n7*&%;"));
        assertEquals(23, stringCalculator.Add("//[:][.][!][@][#]\n1:2.3!\n\n6\n@4\n7#."));
    }

    @Test
//    multiple delimiters with length longer than one char
    public void task9() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(6, stringCalculator.Add("//[;;;][%%]\n1;;;2%%3"));
        assertEquals(21, stringCalculator.Add("//[***][-][@@][++++]\n1***2\n4\n@@-3++++7\n\n4\n-"));
        assertEquals(39, stringCalculator.Add("//[&][----][$$$][##][......]\n1----2\n4\n##&3$$$7\n\n4\n&......4\n\n9......\n5"));
    }
}