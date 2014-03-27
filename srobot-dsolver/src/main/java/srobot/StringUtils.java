package srobot;

public class StringUtils {
    private StringUtils() {}

    public static String padRight(String s, int n) {
         return s + padding(' ', n < 0 ? 0 : n);
    }

    public static String padLeft(String s, int n) {
        return  padding(' ', n < 0 ? 0 : n) + s;
    }

    private static String padding(char c, int n) {
        StringBuilder sb = new StringBuilder(n);
        for(int i=0; i < n; ++i){
            sb.append(c);
        }
        return sb.toString();
    }


}
