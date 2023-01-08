import Entities.Client;
import Entities.Offer;
import Entities.StockExchange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    public static void main(String args[]) {
        StockExchange se = StockExchange.getInstance();

        Client vali = new Client("vali");
        Client rares = new Client("rares");
        Client mihai = new Client("mihai");

        Offer offer1 = new Offer("mere", -4, vali);
        Offer offer2 = new Offer("cartofi", -7, mihai);
        Offer offer3 = new Offer("cartofi", -3, vali);
        Offer offer4 = new Offer("cartofi", 10, rares);
        Offer offer5 = new Offer("mere", 6, rares);

        List<Offer> initialOffers = new ArrayList<>();
        initialOffers.add(offer1);
        initialOffers.add(offer2);
        initialOffers.add(offer3);
        initialOffers.add(offer4);
        initialOffers.add(offer5);

        for (Offer offer: initialOffers) {
            Thread t = new Thread(() -> {
                se.addOffer(offer);
            });
            t.start();
        }

        Thread t = new Thread(() -> {
//            while(true){
//                se.processTransactions();
//            }
            se.processTransactions();
        });
        t.start();

    }
}