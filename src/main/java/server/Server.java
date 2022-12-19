package server;

import utils.Helpers;

import java.io.IOException;
import java.util.UUID;

public class Server extends Thread {
    private String operation;
    private int amount;
    private UUID userId;

    public void run() {
        try {
            if (operation.equals("sell")) {
                System.out.println(userId + ": " + this.sellStock(amount));
            } else {
                System.out.println(userId + ": " + this.buyStock(amount));
            }
        } catch (Exception e) {
            System.out.println(userId + ": " + "Ex" + e);
        }
    }

    public void getOperation(String operation) {
        this.operation = operation;
    }

    public void getAmount(int amount) {
        this.amount = amount;
    }

    public String sellStock(int amount) {
        String toWrite = this.userId + " sold " + amount;
        try {
            Helpers.writeInHistory(toWrite);
            return "You have successfully sold " + amount;
        } catch (IOException e) {
            System.out.println(userId + ": " + "There was a problem when selling the stock");
            return e.toString();
        }
    }

    public String buyStock(int amount) {
        String toWrite = this.userId + " bought " + amount;
        try {
            Helpers.writeInHistory(toWrite);
            return "You have successfully bought " + amount;
        } catch (IOException e) {
            System.out.println(userId + ": " + "There was a problem when selling the stock");
            return e.toString();
        }

    }

    public void getUserId(UUID userId) {
        this.userId = userId;
    }

    public static void main(String[] args){
        //run();
    }
}

