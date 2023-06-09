package me.n1ar4.fake.rpc.server;

import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;
import me.n1ar4.fake.rpc.service.ConfigRPCImpl;

import java.net.InetSocketAddress;

public class GRPCServer {
    private static final int port = 9999;

    public static void main(String[] args) throws Exception {
        InetSocketAddress address = new InetSocketAddress("0.0.0.0", port);
        Server server = NettyServerBuilder.forAddress(address).addService(new ConfigRPCImpl()).build().start();
        System.out.printf("start grpc server: %d %n", port);
        server.awaitTermination();
    }
}