package me.n1ar4.fake.pgsql;

import com.sun.net.httpserver.HttpServer;
import me.n1ar4.fake.log.LogUtil;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class XmlServer {
    private static HttpServer server;
    private static volatile boolean stopFlag = false;

    public static void stop() {
        stopFlag = true;
    }

    @SuppressWarnings("all")
    public static void start(String host, int port, String targetOS, String cmd, String path) throws IOException {
        stopFlag = false;
        InetAddress inetAddress = InetAddress.getByName(host);
        server = HttpServer.create(new InetSocketAddress(inetAddress, port), 0);
        server.createContext(path, new XmlHandler(targetOS, cmd));
        server.setExecutor(null);
        new Thread(() -> {
            while (true) {
                if (stopFlag) {
                    server.stop(0);
                    return;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        LogUtil.log("server started on port " + port);
        server.start();
    }
}