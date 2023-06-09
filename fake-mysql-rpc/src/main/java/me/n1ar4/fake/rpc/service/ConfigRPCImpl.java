package me.n1ar4.fake.rpc.service;


import io.grpc.stub.StreamObserver;
import me.n1ar4.fake.proto.MySQLServer;
import me.n1ar4.fake.rpc.RPCNull;
import me.n1ar4.fake.rpc.RPCPort;
import me.n1ar4.fake.rpc.RPCResp;
import me.n1ar4.fake.rpc.RPCStartGrpc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConfigRPCImpl extends RPCStartGrpc.RPCStartImplBase {
    public static int getFreePort() {
        try (ServerSocket serverSocket = new ServerSocket(0)) {
            return serverSocket.getLocalPort();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void start(RPCNull none, StreamObserver<RPCResp> responseObserver) {
        int port = getFreePort();
        MySQLServer.setPort(port);
        new Thread(MySQLServer::StartServer).start();
        RPCResp resp = RPCResp.newBuilder().setStatus("ok").setPort(port).build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }

    @Override
    public void stop(RPCPort request, StreamObserver<RPCResp> responseObserver) {
        int port = request.getPort();
        if (port == MySQLServer.getPort()) {
            MySQLServer.stop();
            try {
                // 触发新循环
                Socket socket = new Socket("127.0.0.1", MySQLServer.getPort());
                socket.getOutputStream().write("test".getBytes());
                socket.close();
            } catch (Exception ignored) {
            }
            RPCResp resp = RPCResp.newBuilder().setStatus("ok").setPort(port).build();
            responseObserver.onNext(resp);
            responseObserver.onCompleted();
        } else {
            RPCResp resp = RPCResp.newBuilder().setStatus("error").setPort(
                    MySQLServer.getPort()).build();
            responseObserver.onNext(resp);
            responseObserver.onCompleted();
        }
    }
}
