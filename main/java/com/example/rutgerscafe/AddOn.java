package com.example.rutgerscafe;
/**
 This is the AddOn Enum class that contains information about the coffee add-ons.
 @author Ashley Mathai, Shehneel Ashraf
 */
public enum AddOn {
    CREAM("Cream"),
    SYRUP("Syrup"),
    MILK("Milk"),
    CARAMEL("Caramel"),
    WHIPPEDCREAM("Whipped Cream");

    private final String name;
    private final double price = 0.40;

    /**
     * Creates the AddOn object.
     * @param name String name of the AddOn
     */
    AddOn(String name){
        this.name = name;
    }

    /**
     * Returns the price of the add-on.
     * @return double price
     */
    public double getPrice(){
        return price;
    }

    /**
     * Returns the name of the add-on.
     * @return String name
     */
    public String toString(){
        return this.name;
    }
}