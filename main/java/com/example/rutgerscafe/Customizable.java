package com.example.rutgerscafe;
/**
 This is the Customizable interface that contains the add and remove methods.
 @author Ashley Mathai, Shehneel Ashraf
 */
public interface Customizable {
    /**
     * Adds an object.
     * @param obj object to add
     * @return boolean - successfully added or not
     */
    boolean add(Object obj);

    /**
     * Removes an object.
     * @param obj object to remove
     * @return boolean - successfully removed or not
     */
    boolean remove(Object obj);
}
