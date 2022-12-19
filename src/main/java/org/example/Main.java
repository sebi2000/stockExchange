package org.example;

import Offer.Offer;
import Stock.Stock;
import User.User;

public class Main {
    public static void main(String[] args) {

        Stock stock1=new Stock("amazon",5);
        Stock stock2=new Stock("nokia",3);
        Stock[] stockuriAndrei=new Stock[]{stock1,stock2};
        Offer offer1=new Offer(1,10,"amazon",2,"George","BUY");
        Offer offer2=new Offer(1,10,"nokia",3,"Andrei","SELL");
        User user1=new User("Andrei",stockuriAndrei);

        System.out.println("Hello world!");

    }
}