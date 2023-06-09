package me.n1ar4.fake.proto.constant;

@SuppressWarnings("all")
public interface Capability {
    int LONG_PASSWORD = 0x00000001;
    int FOUND_ROWS = 0x00000002;
    int LONG_FLAG = 0x00000004;
    int CONNECT_WITH_DB = 0x00000008;
    int NO_SCHEMA = 0x00000010;
    int PROTOCOL_41 = 0x00000200;
    int TRANSACTIONS = 0x00002000;
    int SECURE_CONNECTION = 0x00008000;
    int PLUGIN_AUTH = 0x00080000;
    int CONNECT_ATTRS = 0x00100000;
    int PLUGIN_AUTH_LENENC_CLIENT_DATA = 0x00200000;
    int SESSION_TRACK = 0x00800000;
    int DEPRECATE_EOF = 0x01000000;
}
