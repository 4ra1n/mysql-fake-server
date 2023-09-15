package me.n1ar4.fake.proto;

import me.n1ar4.fake.proto.utils.LenUtil;

import java.util.ArrayList;
import java.util.List;

public class ColumnPacket {
    public static byte[] buildColumnValuesPacket(byte[][] values) {
        List<Byte> finalValues = new ArrayList<>();
        for (byte[] value : values) {
            byte[] encodedValue = strEncode(value);
            finalValues.addAll(bytesToList(encodedValue));
        }
        return listToBytes(finalValues);
    }

    public static byte[] buildColumnPacket(String column) {
        byte[] def = strEncode("def".getBytes());
        List<Byte> packet = new ArrayList<>(bytesToList(def));
        packet.add((byte) 0x00);
        byte[] table = strEncode("a".getBytes());
        packet.addAll(bytesToList(table));
        byte[] orgTable = strEncode("a".getBytes());
        packet.addAll(bytesToList(orgTable));
        byte[] name = strEncode(column.getBytes());
        packet.addAll(bytesToList(name));
        packet.addAll(bytesToList(name));
        packet.add((byte) 0x0c);
        packet.add((byte) 0x3f);
        packet.add((byte) 0x00);
        packet.add((byte) 0x1c);
        packet.add((byte) 0x00);
        packet.add((byte) 0x00);
        packet.add((byte) 0x00);
        packet.add((byte) 0xfc);
        packet.add((byte) 0xff);
        packet.add((byte) 0xff);
        packet.add((byte) 0x00);
        packet.add((byte) 0x00);
        packet.add((byte) 0x00);
        return listToBytes(packet);
    }

    public static byte[] strEncode(byte[] d) {
        byte[] l = LenUtil.write(d.length);
        String packetHex = bytesToHex(l) + bytesToHex(d);
        return hexToBytes(packetHex);
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    public static byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }

    public static List<Byte> bytesToList(byte[] bytes) {
        List<Byte> list = new ArrayList<>();
        for (byte b : bytes) {
            list.add(b);
        }
        return list;
    }

    public static byte[] listToBytes(List<Byte> list) {
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }
}
