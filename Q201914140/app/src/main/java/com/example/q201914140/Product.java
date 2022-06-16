package com.example.q201914140;

import java.io.Serializable;

public class Product implements Serializable {
    String title;
    int price;
    int count;

    public Product() {
    }

    public Product(String title, int price, int count) {
        this.count = count;
        this.price = price;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getContent() {
        int result = this.price * this.count;
        return (this.price + " * " + this.count + " = " + result);
    }


}
