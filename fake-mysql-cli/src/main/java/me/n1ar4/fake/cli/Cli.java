package me.n1ar4.fake.cli;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import me.n1ar4.fake.proto.GadgetResolver;
import me.n1ar4.fake.proto.MySQLServer;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Cli {
    @Parameter(names = {"-p", "--port"}, description = "port")
    private int port;

    @Parameter(names = {"-f", "--file"}, description = "gadget file")
    private String customGadget;

    public static void main(String[] args) {
        Cli main = new Cli();
        System.setProperty("sun.stdout.encoding", "utf-8");
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(args);
        main.run();
    }

    private void run() {
        if (port == 0) {
            port = 3308;
        }
        MySQLServer.setIp("0.0.0.0");
        MySQLServer.setPort(port);
        PrintUtil.print();

        if (customGadget != null && !customGadget.isEmpty()) {
            try {
                GadgetResolver.setCustomGadget(new String(Files.readAllBytes(Paths.get(customGadget))));
                System.out.println("set custom gadget finish");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        MySQLServer.StartServer();
    }
}
