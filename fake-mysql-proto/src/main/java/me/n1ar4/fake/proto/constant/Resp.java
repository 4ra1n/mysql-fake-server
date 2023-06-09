package me.n1ar4.fake.proto.constant;

@SuppressWarnings("all")
public interface Resp {
    byte[] OK = new byte[]{
            (byte) 0x00,
            (byte) 0x00,
            (byte) 0x00,
            (byte) 0x02,
            (byte) 0x00,
            (byte) 0x00,
            (byte) 0x00,
    };
    byte[] EOF = new byte[]{
            (byte) 0xfe,
            (byte) 0x00,
            (byte) 0x00,
            (byte) 0x02,
            (byte) 0x00,
    };
}
