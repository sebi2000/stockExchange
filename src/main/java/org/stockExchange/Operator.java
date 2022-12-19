package org.stockExchange;

public class Operator {
    public String identifier = "dummy";

    public Operator(String identifier){
        this.identifier = identifier;
    }

    public String toString() {
        return identifier;
    }

    public Transaction sell(String item, int count) {
        return new Transaction(item, count, this);
    }

    public Transaction buy(String item, int count) {
        return new Transaction(item, -count, this);
    }
}
