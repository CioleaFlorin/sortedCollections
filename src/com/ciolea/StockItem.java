package com.ciolea;

public class StockItem implements Comparable<StockItem> {
    private final String name;
    private double price;
    private int quantityStock = 0; //can be intialized later
    private int reserved = 0;

    public StockItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantityStock = 0;  //or here (but not both)
    }

    public StockItem(String name, double price, int quantityStock) {
        this.name = name;
        this.price = price;
        this.quantityStock = quantityStock;
    }


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int quantityInStock() {
        return quantityStock;
    }

    public int getReserved() {
        return reserved;
    }



    public void unreserve(int itemsToUnreserve) {
        if (itemsToUnreserve <= this.reserved) {
            this.reserved -= itemsToUnreserve;
        } else
            System.out.println("You dont have that many items in the basket");
    }

    public void clearResearved() {
        this.reserved = 0;
    }

    public void reserveItem(int numberToReserve) {
        if (numberToReserve > 0 && numberToReserve <= quantityStock) {
            this.reserved += numberToReserve;
        }
    }

    public int availableItems() {
        return (this.quantityStock - this.reserved);
    }

    public void setPrice(double price) {
        if (price > 0.0) {
            this.price = price;
        }
    }

    public void adjustStock(int quantityStock) {
        int newQuantity = this.quantityStock + quantityStock;
        if (newQuantity >= 0) {
            this.quantityStock = newQuantity;
        }
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("Entering StockItem.equals");
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        String objName = ((StockItem) obj).getName();
        return this.name.equals(objName);
    }

    //because StockItem uses name when overriding equals and HashCode we can use name as a key to a Map

    @Override
    public int hashCode() {
        return this.name.hashCode() + 31;
    }

    @Override
    public int compareTo(StockItem o) {
        System.out.println("Entering StockItem.compareTo");
        if (this == o) {  //we can compare null variables
            return 0; //equal
        }
        if (o != null) {
            return this.name.compareTo(o.getName());   //using in-built functionality for String
        }
        throw new NullPointerException(); //using compareTo we assume that we dont compare something that is null
    }

    @Override
    public String toString() {
        return this.name + ": price " + this.price;

    }
}
