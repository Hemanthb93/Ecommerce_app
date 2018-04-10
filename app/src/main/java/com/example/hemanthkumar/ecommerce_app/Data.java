package com.example.hemanthkumar.ecommerce_app;

/**
 * Created by hemanthkumar on 6/3/18.
 */
//this is my pogo class

public class Data {

    String title, description,image;
    int quantity;
    int prize;

    public Data(String title, String description, String image, int quantity, int prize) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.quantity = quantity;
        this.prize = prize;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }
}
