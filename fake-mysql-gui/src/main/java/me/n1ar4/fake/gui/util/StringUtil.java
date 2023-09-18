package me.n1ar4.fake.gui.util;

public class StringUtil {
    public static boolean isNull(String s) {
        if (s == null) {
            return true;
        }
        s = s.trim();
        return s.isEmpty();
    }
}
