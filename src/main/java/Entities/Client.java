package Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Client {
    public String identifier = "dummy";

    public Client(String identifier){
        this.identifier = identifier;
    }

    public String toString() {
        return identifier;
    }

    public Offer sell(String item, int count) {
        return new Offer(item, count, this);
    }

    public Offer buy(String item, int count) {
        return new Offer(item, -count, this);
    }

    public void placeOffer(Offer offer) {

    }
}
