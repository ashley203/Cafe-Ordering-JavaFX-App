package com.example.rutgerscafe;
import java.util.ArrayList;

/**
 This is the Coffee class that create the Coffee object with a quantity, size, and AddOn.
 @author Ashley Mathai, Shehneel Ashraf
 */
public class Coffee extends MenuItem implements Customizable{
    private int quantity;
    private CoffeeSize size;
    private ArrayList<AddOn> addOns;

    /**
     * Creates the Coffee object.
     * @param quantity how many cups
     * @param size size of coffee
     * @param addOns any add-ons
     */
    public Coffee(int quantity, CoffeeSize size, ArrayList<AddOn> addOns){
        this.size = size;
        this.quantity = quantity;
        this.addOns = addOns;
    }

    /**
     * Returns the String formatted version of the Coffee object.
     * @return String "Coffee(quantity)[add-ons]"
     */
    @Override
    public String toString(){
        String output = "Coffee(" + quantity + ") " + size.toString() + " [";
        for(int i = 0; i < addOns.size() - 1; i++){
            output += addOns.get(i).toString() + ", ";
        }
        if(addOns.size() != 0){
            output += addOns.get(addOns.size() - 1);
        }
        return output + "]";
    }

    /**
     * Adds Coffee Add-On object.
     * @param obj Coffee object
     * @return boolean
     */
    @Override
    public boolean add(Object obj) {
        if (!(obj instanceof AddOn)){
            return false;
        }
        addOns.add((AddOn) obj);
        return true;
    }

    /**
     * Removes Coffee Add-On object.
     * @param obj Coffee object
     * @return boolean
     */
    @Override
    public boolean remove(Object obj) {
        if (!(obj instanceof AddOn)){
            return false;
        }
        addOns.remove((AddOn) obj);
        return true;
    }

    /**
     * Returns the price of the Coffee object.
     * @return int price
     */
    public double itemPrice(){
        double price = size.getPrice() * quantity;
        for(AddOn a: addOns){
            price += a.getPrice();
        }
        return price;
    }
}