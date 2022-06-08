package com.example.rutgerscafe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 This is the Order class that creates an Order object with all the items.
 @author Ashley Mathai, Shehneel Ashraf
 */
public class Order implements Customizable{
    private ArrayList<MenuItem> items = new ArrayList<MenuItem>();
    private static int orderCounter;
    private int orderNumber;
    private ObservableList<String> menuItems;

    /**
     * Creates the Order object.
     * @param items list of items in the order
     */
    public Order(ArrayList<MenuItem> items){
        this.items = items;
        orderCounter++;
        this.orderNumber = orderCounter;
    }

    /**
     * Returns the order number.
     * @return int order number
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Adds the MenuItem to an Order
     * @param obj object to add
     * @return boolean - successfully added or not
     */
    @Override
    public boolean add(Object obj) {
        if (!(obj instanceof MenuItem)){
            return false;
        }
        items.add((MenuItem) obj);
        return true;
    }

    /**
     * Removes the MenuItem from an Order
     * @param obj object to remove
     * @return boolean - successfully removed or not
     */
    @Override
    public boolean remove(Object obj) {
        if (!(obj instanceof MenuItem)){
            return false;
        }
        if(this.find((MenuItem) obj)){
            items.remove((MenuItem) obj);
            return true;
        }
        return false;
    }

    /**
     * Checks to see if an item exists within an order.
     * @param item MenuItem that is being searched
     * @return boolean - exists within an order or not
     */
    private boolean find(MenuItem item){
        return items.contains(item);
    }

    /**
     * Prints the toString() of each item in the Order.
     * @return String version of the Order
     */
    public String print(){
        String output = "";
        for(int i = 0; i < items.size(); i++){
            output += items.get(i).toString() + "\n";
        }
        return output;
    }

    /**
     * Converts the MenuItems list into an ObservableList of Strings.
     * @return ObservableList<String> of items in the Order
     */
    public ObservableList<String> returnMenuItems(){
        ObservableList<String> newOrderList = FXCollections.observableArrayList();
        for (MenuItem m: items){
            newOrderList.add(m.toString());
        }
        return newOrderList;
    }

    /**
     * Returns the items in the order
     * @return ArrayList<MenuItem> items in the Order
     */
    public ArrayList<MenuItem> getItems(){
        return items;
    }
}