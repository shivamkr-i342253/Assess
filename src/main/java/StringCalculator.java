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

                if (c.equals("[")) {

                    int ind = s.indexOf('\n');
                    d.replace(0, d.length(), s.substring(3, ind-1));

                    if (checkForSpecialChars(d.toString())) {

                        spCh_flag = true;
                    }
                    newStr.replace(0, newStr.length(), s.substring(ind, s.length()));
                }
                else {

                    newStr.replace(0, newStr.length(), s.substring(2, s.length()));
                    if (checkForSpecialChars(c)) {

                        spCh_flag = true;
                    }
                }

            }
            String del = (d.length() == 0) ? "," : d.toString();
//            System.out.println(del);
//            System.out.println(spCh_flag);
            String delim = (spCh_flag) ? "["+del+"]" : del;
            String newS = (newStr.length() == 0) ? s : newStr.toString();
//            System.out.println(newStr.length());
            // check for delimeter
            if (newS.contains(del)) {
                // split the given string by delimeter
                System.out.println(delim);
                String[] nos = newS.split(delim);
//
                int res = 0;
                for (String z : nos) {
//                    System.out.println(z);
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

    private boolean checkForSpecialChars(String spChars) {
        if (spChars.length() > 1) {
            if (spChars.contains("+") || spChars.contains("^") || spChars.contains("*") || spChars.contains(".") || spChars.contains("$"))  {

                return true;
            }
        }
        else {
            if (spChars.equals("+") || spChars.equals("^") || spChars.equals("*") || spChars.equals(".") || spChars.equals("$"))  {

                return true;
            }
        }
        return false;
    }


    static class NegativesNotAllowedException extends Exception {
        public NegativesNotAllowedException(String ex) {
            super(ex);
        }
    }

    public static void main(String args[]) {
        StringCalculator stringCalculator = new StringCalculator();
        stringCalculator.Add("//[!!!]\n4553\n5\n!!!6\n\n!!!\n4!!!3");
//        stringCalculator.Add("//[^^^]\n1^^^2^^^3");
    }

}
