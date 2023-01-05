import Entities.Client;
import Entities.Offer;
import Entities.StockExchange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    public static void main(String args[]) {
        StockExchange se = StockExchange.getInstance();

        Client vali = new Client("vali");
        Client rares = new Client("rares");
        Client mihai = new Client("mihai");

        CopyOnWriteArrayList<Offer> offers = new CopyOnWriteArrayList<>();

        Thread t1 = new Thread(() -> {
            Offer offer1 = new Offer("mere", -4, vali);
            offers.add(offer1);
        });
        t1.start();


        Thread t2 = new Thread(() -> {
            Offer offer2 = new Offer("cartofi", -7, mihai);
            offers.add(offer2);
        });
        t2.start();

        Thread t3 = new Thread(() -> {
            Offer offer3 = new Offer("mere", -3, vali);
            offers.add(offer3);
        });
        t3.start();

        Thread t4 = new Thread(() -> {
            Offer offer4 = new Offer("cartofi", 10, rares);
            offers.add(offer4);
        });
        t4.start();

        Thread t5 = new Thread(() -> {
            Offer offer5 = new Offer("mere", 6, rares);
            offers.add(offer5);
        });
        t5.start();

        /*Thread t6 = new Thread(() -> {
            Offer offer6 = new Offer("cartofi", -3, rares);
            offers.add(offer6);
        });

        Thread t7 = new Thread(() -> {
            Offer offer7 = new Offer("mere", -3, vali);
            offers.add(offer7);
        });

        Thread t8 = new Thread(() -> {
            Offer offer8 = new Offer("mere", -4, vali);
            offers.add(offer8);
        });

        Thread t9 = new Thread(() -> {
            Offer offer9 = new Offer("cartofi", +7, mihai);
            offers.add(offer9);
        });

        Thread t10 = new Thread(() -> {
            Offer offer10 = new Offer("mere", -1, vali);
            offers.add(offer10);
        });

        Thread t11 = new Thread(() -> {
            Offer offer11 = new Offer("cartofi", -2, rares);
            offers.add(offer11);
        });

        Thread t12 = new Thread(() -> {
            Offer offer12 = new Offer("mere", 6, rares);
            offers.add(offer12);
        });*/

        /*t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
        t11.start();
        t12.start();*/

        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(() -> {
                for (Offer transaction : offers) {
                    se.processTransaction(transaction);
                }
            });
            t.start();
        }
    }
}