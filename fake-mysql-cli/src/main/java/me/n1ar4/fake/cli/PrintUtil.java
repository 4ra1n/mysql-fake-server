package me.n1ar4.fake.cli;

import me.n1ar4.fake.proto.Version;

public class PrintUtil {
    public static void print() {
        System.out.println("\u001B[33;1m　　 へ　　　　　／|\n　　/＼7　　　 ∠＿/\n　 /　" +
                "│　　 ／　／\n　│　Z ＿,＜　／　　 /`ヽ\n　│　　　　　ヽ　　 /　　" +
                "〉\n　 Y　　　　　`　 /　　/\n　?●　?　●　　??〈　　/\n　()　 へ" +
                "　　　　|　＼〈\n　　>? ?_　 ィ　 │ ／／\n　 / へ　　 /　?＜| ＼＼" +
                "\n　 ヽ_?　　(_／　 │／／\n　　7　　　　　　　|／\n　　＞―r￣￣~∠--|");
        System.out.println("\u001B[32;1m Fake MySQL Server Cli \u001B[0m");
        System.out.println("\u001B[32;1m Version: " + Version.version + " \u001B[0m");

        System.out.println("\u001B[32;1m############################# USAGE #############################\u001B[0m");
        System.out.println("Deserialization user: deser_[gadget]_[params]");
        System.out.println("\tGadget: CB|CC31|CC44|ROME|7U21|8U20|C3P0|URLDNS");
        System.out.println("\t\tExample 1: deser_CB_calc.exe");
        System.out.println("\t\tExample 2: deser_URLDNS_http://your-dns-log");

        System.out.println();
        System.out.println("Read file user: fileread_[name]");
        System.out.println("\t\tExample 1: fileread_/etc/passwd");
        System.out.println("\t\tExample 2: fileread_C:\\Program Files\\src.zip");

        System.out.println();
        System.out.println("Support raw string and base64 (start with base64)");
        System.out.println("\t\tExample 1: user=deser_CB_calc.exe");
        System.out.println("\t\tExample 2: user=base64ZGVzZXJfQ0JfY2FsYy5leGU=");

        System.out.println();
        System.out.println("Support custom gadget (use file)");
        System.out.println("\t\tExample: save gadget to test.txt");
        System.out.println("\t\tUse: java -jar cli.jar -f test.txt");
        System.out.println("\t\tUse: deser_CUSTOM");
        System.out.println("\u001B[32;1m#################################################################\u001B[0m");
    }
}
