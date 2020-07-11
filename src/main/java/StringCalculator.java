import java.util.regex.Pattern;

public class StringCalculator {

    public int Add(String s) {

        if (s.length() != 0) {
            // check for delimeter
            if (s.contains(",")) {
                // split the given string by delimeter
                String[] nos = s.split(",");

                int res = 0;
                for (String z : nos) {

                    if (z.length() != 0) {
                        // check invalid input expecting atleast a digit
                        if (checkValidString(z)) {

                            res += splitAndAdd(z.replaceAll("\n", "-"));

                        }
                        else return -1;
                    }
                    res += 0;
                }
                return res;
            }
            else if (checkValidString(s)) {

                return splitAndAdd(s.replaceAll("\n", "-"));

            }
            else return -1;
        }
        // null string
        return 0;
    }

    private int splitAndAdd(String str) {

        String[] nos = str.split("-");

        int res = 0;
        for (String z : nos) {

            if (z.length() != 0) {

                res += Integer.parseInt(z);

            }
            res += 0;

        }
        return res;
    }

    private boolean checkValidString(String digz) {

        return Pattern.compile( "[0-9]" ).matcher( digz ).find();

    }
}
