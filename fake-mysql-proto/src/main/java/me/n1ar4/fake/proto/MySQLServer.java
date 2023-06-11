package me.n1ar4.fake.proto;

import me.n1ar4.fake.log.LogUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MySQLServer {
    private final static Logger log = LogManager.getLogger(MySQLServer.class);
    private static int port = 3308;
    private static String ip = "0.0.0.0";
    private static volatile boolean isRunning = true;

    public static int getPort() {
        return port;
    }

    public static void stop() {
        isRunning = false;
    }

    public static void unsetStop() {
        isRunning = true;
    }

    public static void setIp(String ip) {
        MySQLServer.ip = ip;
    }

    public static void setPort(int port) {
        MySQLServer.port = port;
    }

    public static void main(String[] args) {
        StartServer();
    }

    public static void StartServer() {
        log.info("start fake mysql server: {}:{}", ip, port);
        LogUtil.log(String.format("start fake mysql server: %s:%s", ip, port));
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port, 0, InetAddress.getByName(ip));
            while (isRunning) {
                Socket clientSocket = serverSocket.accept();
                log.info("accept: {}", clientSocket.getInetAddress().getHostAddress());
                LogUtil.log("accept: " + clientSocket.getInetAddress().getHostAddress());
                new Thread(() -> {
                    try {
                        new TaskStarter().run(clientSocket);
                    } catch (Exception ignored) {
                    }
                }).start();
            }
            log.info("exit...");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
