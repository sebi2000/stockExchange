package Entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StockExchange {

    private static StockExchange instance = null;

    private static volatile List<Transaction> transactions = new ArrayList<>();

    private StockExchange() {

    }

     public static synchronized void processTransaction(Transaction transaction) {
        ArrayList<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            t.merge(transaction);
            if (!t.isSettled()) {
                result.add(t);
            }
        }
        if (!transaction.isSettled()) {
            result.add(transaction);
        }
        transactions = result;
        System.out.println(transactions);
    }

    public void printTransactions() {
        for (Transaction t: transactions) {
            System.out.println(t);
        }
    }

    public static synchronized StockExchange getInstance(){
        if(instance == null){
            instance = new StockExchange();
        }
        return  instance;
    }

}
