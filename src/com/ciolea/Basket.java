package com.ciolea;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Basket {
    private final String name;
    private final Map<StockItem, Integer> list;

    public Basket(String name) {
        this.name = name;
        //everytime we are entering a stockItem its using the compareTo method to check
        //this.list = new TreeMap<>();
        this.list = new HashMap<>();
    }

    public int addToBasket(StockItem item, int quantity) {
        if (item != null && quantity > 0 && quantity <= item.availableItems()) {
            int inBasket = list.getOrDefault(item, 0);
            list.put(item, inBasket + quantity);
            item.reserveItem(quantity);

            return inBasket;
        }
        return 0;
    }

    public Map<StockItem, Integer> Items() {
        return Collections.unmodifiableMap(list);
    }

    public void clearBasket() {
        list.clear();
        System.out.println("Basket cleared");
    }

    public void unreserveItemFromBasket(StockItem item, int quantity) {
        if(quantity < list.get(item)) {
            list.put(item, list.get(item) - quantity);
            System.out.println("Item unreserved from basket");

        } else System.out.println("You dont have that many items reserved");
    }




    @Override
    public String toString() {

        String s = "\nShopping basket " + name + " contains " + list.size() + ((list.size() == 1) ? " item" : " items") + "\n";
        double totalCost = 0.0;
        for (Map.Entry<StockItem, Integer> item : list.entrySet()) {
            s = s + item.getKey() + ". " + item.getValue() + " reserved\n";
            totalCost += item.getKey().getPrice() * item.getValue();
        }
        return s + "Total cost " + totalCost;
    }

}
