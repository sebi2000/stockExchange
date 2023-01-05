package Entities;

public class Client {
    public String identifier = "dummy";

    public Client(String identifier){
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
