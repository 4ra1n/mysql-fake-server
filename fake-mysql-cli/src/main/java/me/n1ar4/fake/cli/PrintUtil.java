package me.n1ar4.fake.cli;

import me.n1ar4.fake.gui.Constant;

public class PrintUtil {
    public static void print(){
        System.out.println("\u001B[33;1m　　 へ　　　　　／|\n　　/＼7　　　 ∠＿/\n　 /　"+
                "│　　 ／　／\n　│　Z ＿,＜　／　　 /`ヽ\n　│　　　　　ヽ　　 /　　"+
                "〉\n　 Y　　　　　`　 /　　/\n　?●　?　●　　??〈　　/\n　()　 へ"+
                "　　　　|　＼〈\n　　>? ?_　 ィ　 │ ／／\n　 / へ　　 /　?＜| ＼＼"+
                "\n　 ヽ_?　　(_／　 │／／\n　　7　　　　　　　|／\n　　＞―r￣￣~∠--|");
        System.out.println("\u001B[32;1m Fake MySQL Server Cli \u001B[0m");
        System.out.println("\u001B[32;1m Version: "+ Constant.version+" \u001B[0m");
    }
}
