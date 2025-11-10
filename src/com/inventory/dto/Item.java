package com.inventory.dto;

public class Item {

    private String name;
    private int quantity;
    private double price;

    public Item(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalValue() {
        return quantity * price;
    }

    @Override
    public String toString() {
        return String.format("%s - Qty: %d, Price: $%.2f, Total: $%.2f",
                name, quantity, price, getTotalValue());
    }

    public String toCSV() {
        return name + "," + quantity + "," + price;
    }

}