package me.n1ar4.fake.gui;

import com.formdev.flatlaf.FlatLightLaf;
import me.n1ar4.fake.gui.form.FakeServer;

import javax.swing.*;

public class Application {
    public static void main(String[] args) {
        FlatLightLaf.setup();
        SwingUtilities.invokeLater(Application::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        FakeServer.start();
    }
}