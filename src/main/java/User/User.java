package User;

import Offer.Offer;
import Stock.Stock;

import java.lang.reflect.Array;

public class User {
    public String user;
    public Stock[] stocks;

    public User(String user,Stock[] stocks){
        this.user=user;
        this.stocks=stocks;

    }


}
