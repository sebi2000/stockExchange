import Entities.Client;
import Entities.StockExchange;
import Entities.Transaction;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String args[]) {
        StockExchange se = StockExchange.getInstance();

        Client vali = new Client("vali");
        Client rares = new Client("rares");
        Client mihai = new Client("mihai");

        Transaction t1 = new Transaction("mere", -4, vali);
        Transaction t2 = new Transaction("cartofi", -7, mihai);
        Transaction t3 = new Transaction("mere", -3, vali);
        Transaction t4 = new Transaction("cartofi", 10, rares);
        Transaction t5 = new Transaction("mere", 6, rares);

        ArrayList<Transaction> transactions = new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5));

        Thread t = new Thread(() -> {
            for(Transaction transaction: transactions){
                se.processTransaction(transaction);
            }
        });
        t.start();

    }
}