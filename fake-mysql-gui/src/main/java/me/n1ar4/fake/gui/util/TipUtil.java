package me.n1ar4.fake.gui.util;

import javax.swing.*;

public class TipUtil {
    public static void info(String msg) {
        JOptionPane.showMessageDialog(null, msg, "INFOMATION",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void error(String msg) {
        JOptionPane.showMessageDialog(null, msg, "ERROR",
                JOptionPane.ERROR_MESSAGE);
    }
}
