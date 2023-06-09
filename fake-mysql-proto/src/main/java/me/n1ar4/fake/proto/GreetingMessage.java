package me.n1ar4.fake.proto;

import me.n1ar4.fake.proto.constant.Capability;
import me.n1ar4.fake.proto.constant.Charset;
import me.n1ar4.fake.proto.constant.Status;
import me.n1ar4.fake.proto.utils.ByteUtil;
import org.apache.commons.codec.binary.Hex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;

public class GreetingMessage {
    private static final Logger log = LogManager.getLogger(GreetingMessage.class);
    private byte[] ProtocolVersion; // 1
    private byte[] VersionString; // max:31
    private byte[] ServerThreadID; // 4
    private byte[] Random; // 8
    private byte[] Padding; // 1
    private byte[] CaLow; // 2
    private byte[] Encode; // 1
    private byte[] ServerStatus; // 2
    private byte[] CaHigh; // 2
    private byte[] CL; // 1
    private byte[] OtherPadding; // 10
    private byte[] SECURE_CONNECTION; // 13
    private byte[] PLUGIN_AUTH; // plugin
    private byte[] End; // 1

    public GreetingMessage() {
        try {
            this.ProtocolVersion = new byte[]{(byte) 0x0a};
            log.debug("protocol version: {}", Hex.encodeHexString(this.ProtocolVersion));

            this.VersionString = "5.0.2".getBytes();
            log.debug("version string: {}", new String(this.VersionString));

            this.ServerThreadID = new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00};
            log.debug("server thread id: {}", Hex.encodeHexString(this.ServerThreadID));

            this.Random = new byte[]{(byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01,
                    (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01};
            log.debug("random: {}", Hex.encodeHexString(this.Random));

            this.Padding = new byte[]{(byte) 0x00};
            log.debug("padding: {}", Hex.encodeHexString(this.Padding));

            int finalCapability = Capability.LONG_PASSWORD + Capability.LONG_FLAG +
                    Capability.CONNECT_WITH_DB + Capability.PROTOCOL_41 + Capability.TRANSACTIONS +
                    Capability.SECURE_CONNECTION + Capability.PLUGIN_AUTH;
            int low = finalCapability & 0xFFFF;
            int high = (finalCapability >> 16) & 0xFFFF;

            this.CaLow = ByteUtil.int16ToByteArray((short) low);
            log.debug("capability low hex: {}", Hex.encodeHexString(this.CaLow));

            this.Encode = new byte[]{Charset.UTF8};
            log.debug("encode: {}", Hex.encodeHexString(this.Encode));

            this.ServerStatus = ByteUtil.int16ToByteArray((short) Status.STATUS_AUTOCOMMIT);
            log.debug("capability low hex: {}", Hex.encodeHexString(this.ServerStatus));

            this.CaHigh = ByteUtil.int16ToByteArray((short) high);
            log.debug("capability high hex: {}", Hex.encodeHexString(this.CaHigh));

            this.CL = new byte[]{0x00};
            log.debug("cl: {}", Hex.encodeHexString(this.CL));

            this.OtherPadding = new byte[]{(byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01,
                    (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01};
            log.debug("other padding: {}", Hex.encodeHexString(this.OtherPadding));

            this.SECURE_CONNECTION = new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00};
            log.debug("SECURE_CONNECTION: {}", Hex.encodeHexString(this.SECURE_CONNECTION));

            this.PLUGIN_AUTH = "mysql_clear_password".getBytes();
            log.debug("PLUGIN_AUTH: {}", Hex.encodeHexString(this.PLUGIN_AUTH));

            this.End = new byte[]{(byte) 0x00};
            log.debug("end: {}", Hex.encodeHexString(this.End));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public byte[] getBytes() {
        ByteArrayOutputStream out;
        try {
            out = new ByteArrayOutputStream();
            out.write(this.ProtocolVersion);
            out.write(this.VersionString);
            // string end
            out.write(new byte[]{(byte) 0x00});
            out.write(this.ServerThreadID);
            out.write(this.Random);
            out.write(this.Padding);
            out.write(this.CaLow);
            out.write(this.Encode);
            out.write(this.ServerStatus);
            out.write(this.CaHigh);
            out.write(this.CL);
            out.write(this.OtherPadding);
            out.write(this.SECURE_CONNECTION);
            out.write(this.PLUGIN_AUTH);
            out.write(this.End);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return out.toByteArray();
    }
}
