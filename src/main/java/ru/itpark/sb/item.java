package ru.itpark.sb;

public class item {
    private String product;
    private double price;
    private int qty;

    public item(String name, double price, int qty) {
        this.product = name;
        this.price = price;
        this.qty = qty;
    }

    public item() {

    }

    public double getPrice() {
        return price;
    }

    public void setSum(double sum) {
        this.price = sum;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getProduct() {
        return product;
    }
}
