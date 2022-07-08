package com.example.uts_mpr.Domain;

import java.io.Serializable;

public class OrderDomain implements Serializable {
    private String title;
    private String pic;
    private int fee;
    private int numberInCart;

    public OrderDomain(String title, String pic, int fee) {
        this.title = title;
        this.pic = pic;
        this.fee = fee;
    }

    public OrderDomain(String title, String pic, int fee, int numberInCart) {
        this.title = title;
        this.pic = pic;
        this.fee = fee;
        this.numberInCart = numberInCart;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
