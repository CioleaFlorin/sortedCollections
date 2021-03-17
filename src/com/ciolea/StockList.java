package com.ciolea;

import java.util.*;

public class StockList {
    private final Map<String, StockItem> list;

    public StockList() {
        this.list = new LinkedHashMap<>();
    }

    public int addStock(StockItem item) {
        if (item != null) {
            //will get the item if it already exists in the map and if it doesnt its goong to use this item that we passed
            //check if already have quantity of this item
            StockItem inStock = list.getOrDefault(item.getName(), item);
            //if there are already stocks of this item, adjust the quantity \\ if not add the stock that we passed
            if (inStock != item) {
                item.adjustStock(inStock.quantityInStock());
            }
            list.put(item.getName(), item);
            return item.quantityInStock();
        }
        return 0;
    }

    public int sellStock(String item, int quantity) {
        StockItem inStock = list.getOrDefault(item, null);

        if ((inStock != null) && (inStock.quantityInStock() >= quantity) && (quantity > 0)) {
            inStock.adjustStock(-quantity);
            list.get(item).clearResearved();
            return quantity;
        }
        return 0;
    }

    public StockItem get(String key) {
        return list.get(key);
    }

    public Map<String,Double> PriceList() {
        Map<String, Double> prices = new LinkedHashMap<>();
        for (Map.Entry<String, StockItem> item : list.entrySet()) {
            prices.put(item.getKey(), item.getValue().getPrice());
        }
        return Collections.unmodifiableMap(prices);
    }

    public void checkout(Basket basket) {
        for (Map.Entry<StockItem, Integer> itemInBasket : basket.Items().entrySet()) {
            sellStock(itemInBasket.getKey().getName(), itemInBasket.getValue());
        }
        System.out.println(basket + "\n CHECKED OUT");
        basket.clearBasket();
    }

    public int availableItems(String name) {
        StockItem item = list.get(name);
        if (item == null) {
            return 0;
        }
        return item.availableItems();
    }

    public void unreserve(String name, int quantity) {
        list.get(name).unreserve(quantity);
    }

    //providing a map of all the items in the stock
    //unmodifiable collections are just a view into the underlying collection
    //BUT we can change individual items .Items().get("car").adjustStock(200)
    public Map<String,StockItem> Items () {
        return Collections.unmodifiableMap(list);
    }

     @Override
    public String toString() {
        String s = "\nStock List\n";
        double totalCost = 0.0;
        for (Map.Entry<String, StockItem> item : list.entrySet()) {  //iterating through entire map
            StockItem stockItem = item.getValue();

            double itemValue = stockItem.getPrice() * stockItem.quantityInStock();

            s = s + stockItem + ". There are " + stockItem.quantityInStock() + " in stock. Value of items: ";
            s = s + String.format("%.2f", itemValue) + "\n";;
            totalCost += itemValue;
        }
        //%.2f to print with just 2 deciamals
         return s + " Total stock value " + String.format("%.2f", totalCost);
    }
}
