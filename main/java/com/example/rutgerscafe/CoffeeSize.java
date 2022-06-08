package com.example.rutgerscafe;
/**
 This is the CoffeeSize Enum class that contains information about the coffee sizes and prices.
 @author Ashley Mathai, Shehneel Ashraf
 */
public enum CoffeeSize {
    SHORT(1.69, "Short"),
    TALL(2.09, "Tall"),
    GRANDE(2.49, "Grande"),
    VENTI(2.89, "Venti");

    private final double price;
    private final String name;

    /**
     * Creates the CoffeeSize object.
     * @param price - price of coffee, name - name of Coffee
     */
    CoffeeSize(double price, String name){
        this.price = price;
        this.name = name;
    }

    /**
     * Returns the size of the coffee
     * @return String size
     */
    @Override
    public String toString(){
        return this.name;
    }

    /**
     * Returns the price of the coffee
     * @return double price
     */
    public double getPrice(){
        return this.price;
    }
}