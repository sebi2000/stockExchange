package client;

import server.Server;
import utils.Helpers;

import java.io.*;
import java.util.UUID;

class Client {
    public static void main(String args[]) throws Exception {
        UUID userId = UUID.randomUUID();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = "";
        while (!str.equals("stop")) {
            str = br.readLine();

            String[] input = str.split(" ");

            if (input.length > 1) {
                String operation = input[0].toLowerCase();
                String amount = input[1];

                if (operation.equals("buy") || operation.equals("sell")) {
                    if (!Helpers.isNumeric(amount)) {
                        System.out.println(userId + ": " + "Input needs to be 'sell'/'buy' <number>");
                    } else {
                        Server s = new Server();
                        s.getUserId(userId);
                        s.getOperation(operation);
                        s.getAmount(Integer.parseInt(amount));
                        s.start();
                        s.join();
                    }
                }
            }
        }
    }
}

