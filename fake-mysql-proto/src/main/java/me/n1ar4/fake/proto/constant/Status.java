package me.n1ar4.fake.proto.constant;

@SuppressWarnings("all")
public interface Status {
    int STATUS_IN_TRANS             = 0x0001;
    int STATUS_AUTOCOMMIT           = 0x0002;
    int MORE_RESULTS_EXISTS         = 0x0008;
    int STATUS_NO_GOOD_INDEX_USED   = 0x0010;
    int STATUS_NO_INDEX_USED        = 0x0020;
    int STATUS_CURSOR_EXISTS        = 0x0040;
    int STATUS_LAST_ROW_SENT        = 0x0080;
    int STATUS_DB_DROPPED           = 0x0100;
    int STATUS_NO_BACKSLASH_ESCAPES = 0x0200;
    int STATUS_METADATA_CHANGED     = 0x0400;
    int QUERY_WAS_SLOW              = 0x0800;
    int PS_OUT_PARAMS               = 0x1000;
    int STATUS_IN_TRANS_READONLY    = 0x2000;
    int  SESSION_STATE_CHANGED       = 0x4000;
}
