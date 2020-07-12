import java.util.regex.Pattern;

public class StringCalculator {
    public StringBuffer negatives = new StringBuffer("");

    public int Add(String s) {

        if (s.length() != 0) {

            StringBuffer d = new StringBuffer("");
            StringBuffer newStr = new StringBuffer("");
            boolean spCh_flag = false;

            if (s.contains("//")) {

                d.replace(0, d.length(), s.substring(2,3));
                String c = d.toString();

                if (c.equals("+") || c.equals("^") || c.equals("*") || c.equals(".") || c.equals("$"))  {

                    spCh_flag = true;
                }
                newStr.replace(0, newStr.length(), s.substring(2, s.length()));

            }
            String del = (d.length() == 0) ? "," : d.toString();
            String delim = (spCh_flag) ? "\\"+del : del;
            String newS = (newStr.length() == 0) ? s : newStr.toString();

            // check for delimeter
            if (newS.contains(del)) {
                // split the given string by delimeter
                String[] nos = newS.split(delim);

                int res = 0;
                for (String z : nos) {

                    if (z.length() != 0) {
                        // check invalid input expecting atleast a digit
                        if (checkValidString(z)) {

                            res += splitAndAdd(z.replaceAll("\n", "`"));

                        }
                        else return -1;
                    }
                    res += 0;
                }

                try {
                    if (negatives.length() > 0) {

                        throw new NegativesNotAllowedException("negatives not allowed "+negatives);
                    }
                }
                catch (NegativesNotAllowedException e) {

                    System.out.println(e.getMessage());
                }

                return res;
            }
            else if (checkValidString(newS)) {

                return splitAndAdd(newS.replaceAll("\n", "`"));

            }
            else return -1;
        }
        // null string
        return 0;
    }


    private int splitAndAdd(String str) {

        String[] nos = str.split("`");

        int res = 0;
        for (String z : nos) {

            if (z.length() != 0) {

                if (Integer.parseInt(z) < 0) {

                    negatives.append(" "+z);
                }
                else if (Integer.parseInt(z) < 1001) {

                    res += Integer.parseInt(z);
                }
            }
            res += 0;

        }
        return res;
    }


    private boolean checkValidString(String digz) {

        return Pattern.compile( "[0-9]" ).matcher( digz ).find();

    }


    static class NegativesNotAllowedException extends Exception {
        public NegativesNotAllowedException(String ex) {
            super(ex);
        }
    }
}
