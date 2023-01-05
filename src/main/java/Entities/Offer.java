package Entities;

public class Offer {

    private int count;
    private String item;
    private Client op;

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

    public void merge(Offer with) {
        if (!item.equals(with.item)) {
            return;
        }
        if (count * with.count >= 0) {
            return;
        }
        if (Math.abs(count) < Math.abs(with.count)) {
            recordTransfer(count, with.op);
            with.count += count;
            count = 0;
        }
        else {
            recordTransfer(-with.count, with.op);
            count += with.count;
            with.count = 0;
        }
    }

    private void recordTransfer(int value, Client destination) {
        // buying
        if (count < 0) {
            System.out.printf("%s buys %d %s from %s\n", op, -value, item, destination);
        }
        // selling
        else {
            System.out.printf("%s sells %d %s to %s\n", op, value, item, destination);
        }
    }
}
