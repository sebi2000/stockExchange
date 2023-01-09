package Entities;
import java.util.concurrent.CopyOnWriteArrayList;


public class StockExchange {

    private static StockExchange instance = null;
    private static Thread thread = new Thread(() -> {startMarket();});

    private static CopyOnWriteArrayList<Offer> offers = new CopyOnWriteArrayList<>();

    private StockExchange() {

    }

    public void addOffer(Offer offer) {
        Thread t = new Thread(() -> {
            System.out.println("Adding offer to the market...");
            offers.add(offer);
        });
        t.start();
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
            System.out.printf("Match: %s buys %d %s from %s\n", currentOffer.op, -currentOffer.count, currentOffer.item, targetOffer.op);
        }
        // selling
        else {
            System.out.printf("Match: %s buys %d %s from %s\n", targetOffer.op, targetOffer.count, targetOffer.item, currentOffer.op);
        }
    }

    public static void start() {
        thread.start();
    }

    public static void startMarket(){
            System.out.println("Starting the market...");
            while(true){
                    if (Thread.interrupted()) {
                        break;
                    }
                    for (Offer offer : offers) {
                        for (Offer targetOffer : offers) {
                            checkMatch(offer, targetOffer);
                            if (offer.isSettled()) {
                                offers.remove(offer);
                            } else if (targetOffer.isSettled()) {
                                offers.remove(targetOffer);
                            }
                        }
                    }
            }
    }

    public static void stopExecution() {
        thread.interrupt();
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
