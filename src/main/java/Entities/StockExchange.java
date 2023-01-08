package Entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StockExchange {

    private static StockExchange instance = null;

    private static CopyOnWriteArrayList<Offer> offers = new CopyOnWriteArrayList<>();

    private StockExchange() {

    }

    public void addOffer(Offer offer) {
        offers.add(offer);
    }

    public static void checkMatch(Offer currentOffer, Offer targetOffer) {
        if (!(currentOffer.item.equals(targetOffer.item))) {
            return;
        }
        if (currentOffer.count * targetOffer.count >= 0) {
            return;
        }
        if (Math.abs(currentOffer.count) < Math.abs(targetOffer.count)) {
            recordTransfer(currentOffer, targetOffer);
            targetOffer.count += currentOffer.count;
            currentOffer.count = 0;
        } else {
            recordTransfer(currentOffer, targetOffer);
            currentOffer.count += targetOffer.count;
            targetOffer.count = 0;
        }
    }

    private static void recordTransfer(Offer currentOffer, Offer targetOffer) {
        // buying
        if (currentOffer.count < 0) {
            System.out.printf("%s buys %d %s from %s\n", currentOffer.op, -currentOffer.count, currentOffer.item, targetOffer.op);
        }
        // selling
        else {
            System.out.printf("%s buys %d %s from %s\n", targetOffer.op, targetOffer.count, targetOffer.item, currentOffer.op);
        }
    }

    public static void processTransactions() {
        System.out.println(offers);
        for (Offer offer : offers) {
            //if(offer.lock.tryLock()) {
                for (Offer targetOffer : offers) {
//                    if(targetOffer.lock.tryLock()) {
                        checkMatch(offer, targetOffer);
                        if (offer.isSettled()) {
                            offers.remove(offer);
                        } else if (targetOffer.isSettled()) {
                            offers.remove(targetOffer);
                        }
                        //targetOffer.lock.unlock();
                    //}
                }
                //offer.lock.unlock();
            //}
        }
        System.out.println(offers);
        //System.out.print(Thread.currentThread().getName());

    }

    public void printTransactions() {
        for (Offer t: offers) {
            System.out.println(t);
        }
    }

    public static StockExchange getInstance(){
        if(instance == null){
            instance = new StockExchange();
        }
        return  instance;
    }

}
