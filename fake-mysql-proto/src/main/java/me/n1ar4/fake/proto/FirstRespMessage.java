package me.n1ar4.fake.proto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;

public class FirstRespMessage {
    private static final Logger log = LogManager.getLogger(FirstRespMessage.class);
    private byte[] data;

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getUsername() {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        if (data.length < 40) {
            log.error("data is too short");
            return null;
        }
        for (int i = 36; ; i++) {
            if (data[i] == 0) {
                break;
            }
            bao.write(data[i]);
        }
        return bao.toString();
    }
}
