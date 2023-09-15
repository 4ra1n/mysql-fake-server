package me.n1ar4.fake.proto.utils;

public class ByteUtil {
    public static byte[] int16ToByteArray(short value) {
        byte[] byteArray = new byte[2];
        byteArray[0] = (byte) (value & 0xFF);
        byteArray[1] = (byte) ((value >> 8) & 0xFF);
        return byteArray;
    }

    public static byte[] int3ToBytes(int value) {
        byte[] bytes = new byte[3];
        bytes[0] = (byte) ((value >> 16) & 0xFF);
        bytes[1] = (byte) ((value >> 8) & 0xFF);
        bytes[2] = (byte) (value & 0xFF);
        return bytes;
    }

    public static byte[] ReverseBytes(byte[] byteArray) {
        int left = 0;
        int right = byteArray.length - 1;
        while (left < right) {
            byte temp = byteArray[left];
            byteArray[left] = byteArray[right];
            byteArray[right] = temp;
            left++;
            right--;
        }
        return byteArray;
    }
}
