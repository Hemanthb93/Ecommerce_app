package com.example.hemanthkumar.ecommerce_app;

/**
 * Created by hemanthkumar on 9/3/18.
 */

public class DataTwo {
    String title_two;
    int quantity_two;
    int amount_two;

    public DataTwo(String title_two, int quantity_two, int amount_two) {
        this.title_two = title_two;
        this.quantity_two = quantity_two;
        this.amount_two = amount_two;
    }


    public String getTitle_two() {
        return title_two;
    }

    public void setTitle_two(String title_two) {
        this.title_two = title_two;
    }

    public int getQuantity_two() {
        return quantity_two;
    }

    public void setQuantity_two(int quantity_two) {
        this.quantity_two = quantity_two;
    }

    public int getAmount_two() {
        return amount_two;
    }

    public void setAmount_two(int amount_two) {
        this.amount_two = amount_two;
    }
}
