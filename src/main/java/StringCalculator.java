public class StringCalculator {
    public int Add(String s) {
        if (s.length() != 0) {
            // check for delimeter
            if (s.contains(",")) {
                // split the given string by delimeter
                String[] nos = s.split(",");
                int res = 0;
                for (String z : nos) {
                    if (z.length() != 0) res += Integer.parseInt(z);
                    res += 0;
                }
                return res;
            }
            return Integer.parseInt(s);
        }
        // null string
        return 0;
    }
}
