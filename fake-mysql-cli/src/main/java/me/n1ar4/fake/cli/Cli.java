package me.n1ar4.fake.cli;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import me.n1ar4.fake.proto.MySQLServer;

public class Cli {
    @Parameter(names = {"-p", "--port"}, description = "port")
    private int port;

    public static void main(String[] args) {
        Cli main = new Cli();
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(args);

        main.run();
    }

    private void run() {
        if(port == 0) {
            port = 3308;
        }
        MySQLServer.setIp("0.0.0.0");
        MySQLServer.setPort(port);
        PrintUtil.print();
        MySQLServer.StartServer();
    }
}
