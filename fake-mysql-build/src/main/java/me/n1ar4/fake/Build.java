package me.n1ar4.fake;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Build {
    static String oldVersion = "0.0.2";
    static String newVersion = "0.0.3";

    public static void main(String[] args) throws Exception {
        Path rootPom = Paths.get("pom.xml");
        Path m5Pom = Paths.get("fake-mysql5-test/pom.xml");
        Path m6Pom = Paths.get("fake-mysql6-test/pom.xml");
        Path m8Pom = Paths.get("fake-mysql8-test/pom.xml");
        Path buildPom = Paths.get("fake-mysql-build/pom.xml");
        Path cliPom = Paths.get("fake-mysql-cli/pom.xml");
        Path gadgetPom = Paths.get("fake-mysql-gadget/pom.xml");
        Path guiPom = Paths.get("fake-mysql-gui/pom.xml");
        Path logPom = Paths.get("fake-mysql-log/pom.xml");
        Path protoPom = Paths.get("fake-mysql-proto/pom.xml");
        Path version = Paths.get("fake-mysql-proto/src/main/java/me/n1ar4/fake/proto/Version.java");

        replace(rootPom);
        replace(m5Pom);
        replace(m6Pom);
        replace(m8Pom);
        replace(buildPom);
        replace(cliPom);
        replace(gadgetPom);
        replace(guiPom);
        replace(logPom);
        replace(protoPom);
        replace(version);
    }

    private static void replace(Path path) throws Exception {
        byte[] data = Files.readAllBytes(path);
        String newData = new String(data).replace(oldVersion, newVersion);
        Files.write(path, newData.getBytes());
    }
}