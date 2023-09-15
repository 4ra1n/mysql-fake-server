package me.n1ar4.fake.proto;

import me.n1ar4.fake.proto.constant.Resp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VariablesResolver implements Resolver {
    private static final Logger log = LogManager.getLogger(VariablesResolver.class);

    private final OutputStream outputStream;

    public VariablesResolver(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void resolve() {
        try {
            byte[] first = PacketHelper.buildPacket(1, new byte[]{(byte) 0x02});
            if (first == null) {
                log.error("build packet error");
                return;
            }
            outputStream.write(first);
            outputStream.flush();

            List<Byte> columns = new ArrayList<>();
            columns.addAll(ColumnPacket.bytesToList(
                    Objects.requireNonNull(PacketHelper.buildPacket(2,
                            ColumnPacket.buildColumnPacket("d")))));
            columns.addAll(ColumnPacket.bytesToList(
                    Objects.requireNonNull(PacketHelper.buildPacket(3,
                            ColumnPacket.buildColumnPacket("e")))));
            outputStream.write(ColumnPacket.listToBytes(columns));
            outputStream.flush();

            outputStream.write(Objects.requireNonNull(
                    PacketHelper.buildPacket(5, Resp.EOF)));
            outputStream.flush();

            outputStream.write(Objects.requireNonNull(PacketHelper.buildPacket(6,
                    ColumnPacket.buildColumnValuesPacket(
                            new byte[][]{"max_allowed_packet".getBytes(), "67108864".getBytes()}
                    ))));
            outputStream.flush();

            outputStream.write(Objects.requireNonNull(PacketHelper.buildPacket(7,
                    ColumnPacket.buildColumnValuesPacket(
                            new byte[][]{"system_time_zone".getBytes(), "UTC".getBytes()}
                    ))));
            outputStream.flush();

            outputStream.write(Objects.requireNonNull(PacketHelper.buildPacket(8,
                    ColumnPacket.buildColumnValuesPacket(
                            new byte[][]{"time_zone".getBytes(), "SYSTEM".getBytes()}
                    ))));
            outputStream.flush();

            outputStream.write(Objects.requireNonNull(PacketHelper.buildPacket(9,
                    ColumnPacket.buildColumnValuesPacket(
                            new byte[][]{"init_connect".getBytes(), "".getBytes()}
                    ))));
            outputStream.flush();

            outputStream.write(Objects.requireNonNull(PacketHelper.buildPacket(10,
                    ColumnPacket.buildColumnValuesPacket(
                            new byte[][]{"auto_increment_increment".getBytes(), "1".getBytes()}
                    ))));
            outputStream.flush();

            outputStream.write(Objects.requireNonNull(
                    PacketHelper.buildPacket(11, Resp.EOF)));
            outputStream.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
