package Entities;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;


public class StockExchange {

    private final static String QUEUE_NAME = "completed";

    private static StockExchange instance = null;
    private static Thread thread = new Thread(() -> {
        try {
            startMarket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    });

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

    public static void checkMatch(Offer currentOffer, Offer targetOffer) throws Exception {
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

    private static void recordTransfer(Offer currentOffer, Offer targetOffer) throws Exception {

        String message;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();

        try {
            Channel channel = connection.createChannel();

            try {
                channel.queueDeclare("completed", false, false, false, (Map)null);
                // buying
                if (currentOffer.count < 0) {
                    message ="Match: " + currentOffer.op +" buys "+ -currentOffer.count +" "+ currentOffer.item +" from "+ targetOffer.op;
                }
                else {
                    message ="Match: " + targetOffer.op +" buys "+ targetOffer.count +" "+ targetOffer.item +" from "+ currentOffer.op;
                }
                //String message = "Hello World!";
                channel.basicPublish("", "completed", (AMQP.BasicProperties)null, message.getBytes(StandardCharsets.UTF_8));
                //System.out.println(" [x] Sent '" + message + "'");
            } catch (Throwable var8) {
                if (channel != null) {
                    try {
                        channel.close();
                    } catch (Throwable var7) {
                        var8.addSuppressed(var7);
                    }
                }

                throw var8;
            }

            if (channel != null) {
                channel.close();
            }
        } catch (Throwable var9) {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Throwable var6) {
                    var9.addSuppressed(var6);
                }
            }

            throw var9;
        }

        if (connection != null) {
            connection.close();
        }


    }

    public static void start() {
        thread.start();
    }

    public static void startMarket() throws Exception {
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
