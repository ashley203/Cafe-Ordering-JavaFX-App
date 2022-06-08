package com.example.rutgerscafe;
/**
 This is the Donut class that create the Donut object with a quantity and donutFlavor.
 @author Ashley Mathai, Shehneel Ashraf
 */
public class Donut extends MenuItem{
    private int quantity;
    private DonutFlavor donutFlavor;

    /**
     * Creates a Donut object.
     * @param quantity number of donuts
     * @param donutFlavor flavor of donut
     */
    public Donut(int quantity, DonutFlavor donutFlavor){
        this.quantity = quantity;
        this.donutFlavor = donutFlavor;
    }

    /**
     * Returns the String formatted version of the donut flavor and quantity.
     * @return String flavor and quantity
     */
    @Override
    public String toString(){
        return donutFlavor.toString() + "(" + quantity + ")";
    }

    /**
     * Returns the price of the donut based on the quantity.
     * @return double price
     */
    @Override
    public double itemPrice() {
        return donutFlavor.getDonutType().getPrice() * quantity;
    }
}