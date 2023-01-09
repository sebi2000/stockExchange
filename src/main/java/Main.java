import Entities.Client;
import Entities.Offer;
import Entities.StockExchange;
import java.util.Scanner;

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

        se.start();

        try {
            se.addOffer(offer1);
            Thread.sleep(1000);
            se.addOffer(offer2);
            Thread.sleep(1000);
            se.addOffer(offer3);
            Thread.sleep(1000);
            se.addOffer(offer4);
            Thread.sleep(1000);
            se.addOffer(offer5);
        } catch(Exception e) {
            System.out.println(e);
        }

        Scanner scan = new Scanner(System.in);
        String text = scan.nextLine();

        if(text.equals("stop")) {
            se.stopExecution();
        }
    }
}