package com.example.rutgerscafe;
/**
 This is the DonutType Enum class that contains information about the donut types and their prices.
 @author Ashley Mathai, Shehneel Ashraf
 */
public enum DonutType {
    YEASTDONUT(1.59),
    CAKEDONUT(1.79),
    DONUTHOLE(0.39);

    private final double price;

    /**
     * Creates a DonutType object.
     * @param price of donut
     */
    DonutType(double price){
        this.price = price;
    }

    /**
     * Returns the price of the donut.
     * @return double price
     */
    public double getPrice(){
        return this.price;
    }
}
