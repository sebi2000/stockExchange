package Entities;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Offer {

    int count;
    String item;
    Client op;
    public final Lock lock = new ReentrantLock();

    public Offer(String item, int count, Client op) {
        this.item = item;
        this.count = count;
        this.op = op;
    }

    public boolean isSettled() {
        return count == 0;
    }

    public String toString() {
        String type = count < 0 ? "buying" : "selling";
        return String.format("%s %s %d %s", op, type, Math.abs(count), item);
    }
}
