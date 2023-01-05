package Entities;

import java.util.ArrayList;
import java.util.List;

public class StockExchange {

    private static StockExchange instance = null;

    private static volatile List<Offer> transactions = new ArrayList<>();

    private StockExchange() {

    }

     public static synchronized void processTransaction(Offer offer) {
        ArrayList<Offer> result = new ArrayList<>();
        for (Offer t : transactions) {
            t.merge(offer);
            if (!t.isSettled()) {
                result.add(t);
            }
        }
        if (!offer.isSettled()) {
            result.add(offer);
        }
        transactions = result;
         System.out.print(Thread.currentThread().threadId());
         System.out.println(transactions);
    }

    public void printTransactions() {
        for (Offer t: transactions) {
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
