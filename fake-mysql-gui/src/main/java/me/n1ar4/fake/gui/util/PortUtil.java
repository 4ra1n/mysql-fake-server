package me.n1ar4.fake.gui.util;

import java.io.IOException;
import java.net.ServerSocket;

public class PortUtil {
    public static String getFreePort() {
        try (ServerSocket serverSocket = new ServerSocket(0)) {
            return String.valueOf(serverSocket.getLocalPort());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
