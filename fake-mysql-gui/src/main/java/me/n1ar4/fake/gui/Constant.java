package me.n1ar4.fake.gui;

public interface Constant {
    String STOP = "STOP";
    String RUNNING = "RUNNING";
    String FakeServer = "FakeServer";
    String MySQLReadFile56Temp = "jdbc:mysql://%s/test?" +
            "allowLoadLocalInfile=true&" +
            "allowUrlInLocalInfile=true&" +
            "allowLoadLocalInfileInPath=/&" +
            "maxAllowedPacket=65536&user=%s";
    String MySQLReadFile8Temp = "jdbc:mysql://%s/test?user=%s";
    String MySQL5119T5128DTemp = "jdbc:mysql://%s/test?autoDeserialize=true&user=%s";
    String MySQL5129T5148DTemp = "jdbc:mysql://%s/test?detectCustomCollations=true&autoDeserialize=true&user=%s";
    String MySQL6XDTemp = "jdbc:mysql://%s/test?detectCustomCollations=true&autoDeserialize=true&user=%s";
    String MySQL510T5XSTemp = "jdbc:mysql://%s/test?autoDeserialize=true&statementInterceptors=" +
            "com.mysql.jdbc.interceptors.ServerStatusDiffInterceptor&user=%s";
    String MySQL6XSTemp = "jdbc:mysql://%s/test?autoDeserialize=true&statementInterceptors=" +
            "com.mysql.cj.jdbc.interceptors.ServerStatusDiffInterceptor&user=%s";
    String MySQLT8020STemp = "jdbc:mysql://%s/test?autoDeserialize=true&queryInterceptors=" +
            "com.mysql.cj.jdbc.interceptors.ServerStatusDiffInterceptor&user=%s";
}
