package me.n1ar4.fake.proto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class RequestDecoder {
    private static final Logger log = LogManager.getLogger(RequestDecoder.class);
    private int command;
    private String statement;
    private byte[] data;
    public int getCommand() {
        return command;
    }
    public String getStatement() {
        return statement;
    }

    public RequestDecoder(byte[] data){
        if(data.length<5){
            log.warn("request too short");
            return;
        }
        this.data = data;
        this.command = this.data[4];
        this.statement = new String(Arrays.copyOfRange(this.data,5,this.data.length));
    }
}
