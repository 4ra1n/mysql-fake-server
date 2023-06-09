package me.n1ar4.fake.proto;

import me.n1ar4.fake.log.LogUtil;
import me.n1ar4.fake.proto.constant.Resp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Objects;

public class TaskStarter {
    private static final Logger log = LogManager.getLogger(TaskStarter.class);

    public void run(Socket clientSocket) throws Exception {
        InputStream inputStream = clientSocket.getInputStream();
        OutputStream outputStream = clientSocket.getOutputStream();

        byte[] greet = new GreetingMessage().getBytes();
        byte[] finalPacket = PacketHelper.buildPacket(0, greet);
        log.info("send greeting from server");
        LogUtil.log("send greeting from server");
        outputStream.write(Objects.requireNonNull(finalPacket));
        outputStream.flush();

        byte[] data = PacketHelper.readData(inputStream);
        FirstRespMessage frf = new FirstRespMessage();
        frf.setData(data);
        log.info("username: {}", frf.getUsername());
        LogUtil.log("username: " + frf.getUsername());

        data = PacketHelper.buildPacket(2, Resp.OK);
        outputStream.write(Objects.requireNonNull(data));
        outputStream.flush();

        String mysqlVersion = null;
        while (true) {
            try {
                data = PacketHelper.readData(inputStream);
                if (data.length == 0) {
                    break;
                }
                RequestDecoder r = new RequestDecoder(data);
                if (r.getCommand() == 0) {
                    break;
                }

                if (frf.getUsername().startsWith("fileread_")) {
                    log.info("mode: file read");
                    LogUtil.log("mode: file read");
                    ReadFileResolver rResolver = new ReadFileResolver(
                            inputStream, outputStream, frf.getUsername());
                    rResolver.resolve();
                    break;
                }

                if (r.getCommand() == 3) {
                    if (r.getStatement().toUpperCase().contains("SHOW VARIABLES")) {
                        log.info("show variables");
                        LogUtil.log("show variables");
                        if (r.getStatement().contains("mysql-connector-java")) {
                            mysqlVersion = r.getStatement().split(
                                    "mysql-connector-java-")[1].split(" ")[0].trim();
                            log.info("mysql connector version: {}", mysqlVersion);
                            LogUtil.log("mysql connector version: " + mysqlVersion);
                        }
                        VariablesResolver resolver = new VariablesResolver(outputStream);
                        resolver.resolve();
                    } else {
                        if (mysqlVersion != null && mysqlVersion.startsWith("8")) {
                            if (r.getStatement().toUpperCase().contains("SHOW SESSION STATUS")) {
                                GadgetResolver resolver = new GadgetResolver(outputStream, frf.getUsername());
                                resolver.resolve();
                            } else {
                                outputStream.write(Objects.requireNonNull(
                                        PacketHelper.buildPacket(0, Resp.OK)));
                                outputStream.flush();
                            }
                        } else {
                            GadgetResolver resolver = new GadgetResolver(outputStream, frf.getUsername());
                            resolver.resolve();
                        }

                    }
                } else {
                    outputStream.write(
                            Objects.requireNonNull(PacketHelper.buildPacket(1, Resp.OK)));
                    outputStream.flush();
                }
            } catch (Exception ignored) {
                break;
            }
        }
    }
}
