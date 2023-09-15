package me.n1ar4.fake.proto;

import me.n1ar4.fake.proto.constant.Resp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class ReadFileResolver implements Resolver {
    private static final Logger log = LogManager.getLogger(ReadFileResolver.class);
    private final OutputStream outputStream;
    private final InputStream inputStream;
    private final String username;

    public ReadFileResolver(InputStream inputStream,
                            OutputStream outputStream,
                            String username) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.username = username;
    }

    @Override
    public void resolve() {
        try {
            if (!username.startsWith("fileread_")) {
                return;
            }
            String[] sps = username.split("_");
            if (sps.length < 2) {
                return;
            }

            StringBuilder builder = new StringBuilder();
            for (int i = 1; i < sps.length; i++) {
                builder.append(sps[i]);
                if (i != sps.length - 1) {
                    builder.append("_");
                }
            }
            String filename = builder.toString();

            File file = new File(filename);
            String shortName = file.getName();

            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            bao.write((byte) 0xfb);
            log.info("read file: {}", filename);
            bao.write(filename.getBytes());
            outputStream.write(Objects.requireNonNull(
                    PacketHelper.buildPacket(1, bao.toByteArray())));
            outputStream.flush();

            OutputStream fos = null;
            try {
                String current = String.valueOf(System.currentTimeMillis());
                Path dir = Paths.get("fake-server-files");
                Files.createDirectories(dir);
                Path finalDir = Paths.get(dir.toFile().getAbsolutePath(), current);
                Files.createDirectories(finalDir);
                Path finalFile = Paths.get(dir.toFile().getAbsolutePath(), current, shortName);
                Files.createFile(finalFile);
                fos = Files.newOutputStream(finalFile);
            } catch (Exception ex) {
                log.warn(ex.getMessage());
            }
            if (fos == null) {
                return;
            }
            byte[] buffer = new byte[4];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                if (bytesRead != 4) {
                    log.warn("read header error");
                    continue;
                }
                ByteBuffer bb = ByteBuffer.wrap(buffer);
                bb.order(ByteOrder.LITTLE_ENDIAN);
                short l1 = bb.getShort();
                byte l2 = bb.get();
                int l = l1 & 0xFFFF | (l2 & 0xFF) << 16;

                if (l == 0) {
                    log.warn("file is null");
                    break;
                }

                byte[] inner = new byte[l];
                int innerRead = inputStream.read(inner, 0, l);
                fos.write(inner, 0, innerRead);
                fos.flush();
                log.info("write success: {}", l);
            }
            log.info("read file finish");
            fos.close();

            outputStream.write(Objects.requireNonNull(
                    PacketHelper.buildPacket(2, Resp.OK)));
            outputStream.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
