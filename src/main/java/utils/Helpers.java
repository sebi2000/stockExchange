package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Helpers {
   static String historyPath = "src/main/java/database/history.txt";

    public static boolean isNumeric(String s){
        try {
            Integer.parseInt(s);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static void writeInHistory(String text)
            throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(historyPath, true));


        writer.append(text);
        writer.append('\n');

        writer.close();
    }
}
