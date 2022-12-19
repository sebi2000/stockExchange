package org.stockExchange;

import java.util.ArrayList;
import java.util.List;

public class StockExchange {

    private static StockExchange instance = null;

    private List<Transaction> transactions = new ArrayList<>();

    private StockExchange(){

    }

    public synchronized void processTransaction(Transaction transaction) {
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

    public static void main(String args[]) {
        StockExchange se = StockExchange.getInstance();

        Operator vali = new Operator("vali");
        Operator rares = new Operator("rares");
        Operator mihai = new Operator("mihai");

        se.processTransaction(vali.sell("mere", 4));
        se.processTransaction(mihai.buy("cartofi", 7));
        se.processTransaction(vali.sell("mere", 3));
        se.processTransaction(rares.sell("cartofi", 5));
        se.processTransaction(rares.buy("mere", 6));

        se.printTransactions();
    }

}
