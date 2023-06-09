package me.n1ar4.fake.proto.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class LenUtil {
    public static byte[] write(long data) {
        if (data < 0) {
            throw new IllegalArgumentException();
        } else if (data < 251) {
            return new byte[]{(byte) data};
        } else if (data < Math.pow(2, 16)) {
            ByteBuffer buffer = ByteBuffer.allocate(3).order(ByteOrder.LITTLE_ENDIAN);
            buffer.put((byte) 0xFC);
            buffer.putShort((short) data);
            return buffer.array();
        } else if (data < Math.pow(2, 24)) {
            ByteBuffer buffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN);
            buffer.put((byte) 0xFD);
            buffer.putShort((short) data);
            buffer.put((byte) (data >> 16));
            return buffer.array();
        } else if (data < Math.pow(2, 64)) {
            ByteBuffer buffer = ByteBuffer.allocate(9).order(ByteOrder.LITTLE_ENDIAN);
            buffer.put((byte) 0xFE);
            buffer.putLong(data);
            return buffer.array();
        } else {
            throw new IllegalArgumentException();
        }
    }
}