package org.stockExchange;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class StockServer {

    public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(1234);
        do {
            Socket client = listener.accept();
            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            try {
                String line = null;
                do {
                    line = reader.readLine();
                    System.out.println(line);
                }
                while (!line.trim().equals(""));

                OutputStream out = client.getOutputStream();
                out.write("HTTP 200 OK\n\nWorks".getBytes());

                client.close();
            }
            catch (IOException e) {
                System.out.println("Client disconnected");
            }
        }
        while (true);
    }
}
