package com.ciolea;


import java.util.Objects;

public class MainChallenge {
    private static StockList stockList = new StockList();

    public static void main(String[] args) {

        StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.addStock(temp);

        temp = new StockItem("cake", 1.10, 7);
        stockList.addStock(temp);

        temp = new StockItem("car", 12.50, 2);
        stockList.addStock(temp);

        temp = new StockItem("chair", 62.0, 10);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.50, 200);
        stockList.addStock(temp);
        temp = new StockItem("cup", 0.45, 7);
        stockList.addStock(temp);

        temp = new StockItem("door", 72.95, 4);
        stockList.addStock(temp);

        temp = new StockItem("juice", 2.50, 36);
        stockList.addStock(temp);

        temp = new StockItem("phone", 96.99, 35);
        stockList.addStock(temp);

        temp = new StockItem("towel", 2.40, 80);
        stockList.addStock(temp);

        temp = new StockItem("vase", 8.76, 40);
        stockList.addStock(temp);

        System.out.println(stockList);

        Basket timsBasket = new Basket("Tim");
        reserveItem(timsBasket, "car", 1);
        System.out.println(timsBasket);

        reserveItem(timsBasket, "car", 1);
        System.out.println(timsBasket);
        unreserveItem(timsBasket,"car",5);


        reserveItem(timsBasket, "juice", 4);
        System.out.println(timsBasket);
        reserveItem(timsBasket, "cup", 12);
        reserveItem(timsBasket, "bread", 1);
        System.out.println(timsBasket);

        stockList.checkout(timsBasket);
        System.out.println(timsBasket);
        System.out.println(stockList);
    }

    public static int reserveItem(Basket basket, String item, int quantity) {
        //retrieve the item form stock list
        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {
            System.out.println("We dont sell " + item);
            return 0;
        }
//        if (stockList.sellStock(item, quantity) != 0) {
//            basket.addToBasket(stockItem, quantity);
//            return quantity;
//        }

        if (stockList.availableItems(item) > 0 && quantity <= stockList.availableItems(item)) {

            basket.addToBasket(stockItem, quantity);
        }

        return 0;
    }

    public static void unreserveItem(Basket basket, String name, int quantity) {
        basket.unreserveItemFromBasket(stockList.get(name), quantity);
        stockList.unreserve(name, quantity);



    }

}
