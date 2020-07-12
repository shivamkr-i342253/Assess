import java.util.regex.Pattern;

public class StringCalculator {
    public StringBuffer negatives = new StringBuffer("");

    public int Add(String s) {

        if (s.length() != 0) {

            StringBuffer d = new StringBuffer("");
            StringBuffer newStr = new StringBuffer("");
//            Support different delimiters with including reserved regex characters like '$', '*', '.', '+'
            boolean spCh_flag = false;

//            support multiple delimiters
            StringBuffer multDelims = new StringBuffer("");
            StringBuffer currDel = new StringBuffer("");
            boolean multiDelim_flag = false;

            if (s.contains("//")) {

                d.replace(0, d.length(), s.substring(2,3));
                String c = d.toString();

                if (c.equals("[")) {

                    multiDelim_flag = true;
                    //get the index just after first '[' in provided s
                    int ind = s.indexOf('[') + 1;

                    while (s.charAt(ind) != '\n') {

                        if (s.charAt(ind) == ']') {

                            if ((multDelims.length() == 0)) {

                                multDelims.append("["+currDel+"]");

                            }
                            else {

                                multDelims.append("|[" + currDel+"]");

                            }
                            currDel.replace(0, currDel.length(), "");

                            ind++;
                            if (s.charAt(ind) == '\n') break;

                        }
                        else {

                            currDel.append(s.charAt(ind));

                        }
                        ind++;

                    }
                    d.replace(0, d.length(), s.substring(s.indexOf('[') + 1, s.indexOf(']')));

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
            String delim = (multiDelim_flag) ? multDelims.toString() : ((spCh_flag) ? "["+del+"]" : del);
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

                displayNegatives();

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

    private void displayNegatives() {
        try {
            if (negatives.length() > 0) {

                throw new NegativesNotAllowedException("negatives not allowed "+negatives);

            }
        }
        catch (NegativesNotAllowedException e) {

            System.out.println(e.getMessage());

        }
    }


    public static void main(String args[]) {

        StringCalculator stringCalculator = new StringCalculator();
//        stringCalculator.Add("//[^^^]\n1^^^2^^^3");
    }

}
