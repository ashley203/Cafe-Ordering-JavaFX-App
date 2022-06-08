package com.example.rutgerscafe;
import java.util.ArrayList;

/**
 This is the StoreOrders class that creates the StoreOrders object.
 @author Ashley Mathai, Shehneel Ashraf
 */
public class StoreOrders implements Customizable{
    private ArrayList<Order> ordersDatabase = new ArrayList<Order>();

    /**
     * Adds order to the current database of all placed orders
     * @param obj object to add
     * @return boolean - successfully added or not
     */
    public boolean add(Object obj){
        if (!(obj instanceof Order)){
            return false;
        }
        ordersDatabase.add((Order) obj);
        return true;
    }

    /**
     * Removes order from the current database of all placed orders
     * @param obj object to remove
     * @return boolean - successfully removed or not
     */
    public boolean remove(Object obj){
        if (!(obj instanceof Order)){
            return false;
        }
        if(this.find((Order) obj)){
            ordersDatabase.remove((Order) obj);
            return true;
        }
        return false;
    }

    /**
     * Returns the database of all already placed orders.
     * @return ArrayList
     */
    public ArrayList getOrdersDatabase() {
        return ordersDatabase;
    }

    /**
     * Retrieves the order based off its order number
     * @param orderNumber int - number of order placed
     * @return Order
     */
    public Order getOrder(int orderNumber){
        for (int i = 0; i < ordersDatabase.size(); i++){
            if (orderNumber == ordersDatabase.get(i).getOrderNumber()){
                return ordersDatabase.get(i);
            }
        }
        return null;
    }

    /**
     * Prints the String formatted version of each order.
     * @return String
     */
    public String print(){
        String output = "";
        for(int i = 0; i < ordersDatabase.size(); i++){
            Order o = (Order) ordersDatabase.get(i);
            output += "Order Number " + o.getOrderNumber() + ":\n" + o.print();
        }
        return output;
    }

    /**
     * Determines if an order exists in the database.
     * @param item Order searching for
     * @return boolean - exists or not
     */
    private boolean find(Order item){
        return ordersDatabase.contains(item);
    }
}
