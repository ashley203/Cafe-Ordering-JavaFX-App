package com.example.rutgerscafe;
/**
 This is the DonutFlavor Enum class that contains information about the donut flavors and types.
 @author Ashley Mathai, Shehneel Ashraf
 */
public enum DonutFlavor {
    JELLY("Jelly", DonutType.YEASTDONUT),
    OLDFASHIONED("Old Fashioned", DonutType.YEASTDONUT),
    POWDERED("Powdered", DonutType.YEASTDONUT),
    SUGAR("Sugar", DonutType.CAKEDONUT),
    GLAZED("Glazed", DonutType.CAKEDONUT),
    STRAWBERRYFROSTED("Strawberry Frosted", DonutType.CAKEDONUT),
    CRULLER("Cruller", DonutType.DONUTHOLE),
    BOSTONKREME("Boston Kreme", DonutType.DONUTHOLE),
    CHOCOLATEGLAZED("Chocolate Glazed", DonutType.DONUTHOLE);

    private final String name;
    private DonutType donutType;

    /**
     * Creates the Donut object.
     * @param name String name of donut
     * @param donutType String type of donut
     */
    DonutFlavor(String name, DonutType donutType){
        this.name = name;
        this.donutType = donutType;
    }

    /**
     * Returns the flavor of the donut
     * @return String donut flavor
     */
    @Override
    public String toString(){
        return this.name;
    }

    /**
     * Returns the type of donut
     * @return donut type
     */
    public DonutType getDonutType() {
        return donutType;
    }

    /**
     * Returns the price of the donut
     * @return double price
     */
    public double getPrice(){
        if (this.donutType == DonutType.YEASTDONUT) {
            return DonutType.YEASTDONUT.getPrice();
        }
        else if (this.donutType == DonutType.CAKEDONUT) {
            return DonutType.CAKEDONUT.getPrice();
        }
        return DonutType.DONUTHOLE.getPrice();

    }
}