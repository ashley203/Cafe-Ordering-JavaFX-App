package com.example.rutgerscafe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 This is the OrderingCoffeeController class that handles the Ordering Coffee commands & GUI.
 @author Ashley Mathai, Shehneel Ashraf
 */
public class OrderingCoffeeController {
    @FXML
    private ComboBox<String> comboCoffeeSizes;

    @FXML
    private ComboBox<Integer> comboCoffeeQuantity;

    @FXML
    private TextArea subtotalText;

    @FXML
    private CheckBox creamCheckBox, syrupCheckBox, milkCheckBox, caramelCheckBox, whippedCreamCheckBox;

    private final double ADD_ON_PRICE = 0.4;

    ArrayList<CheckBox> checkBoxes = new ArrayList<CheckBox>();

    private MainController mainController;

    /**
     * Sets the controller.
     * @param controller controller
     */
    public void setMainController(MainController controller)
    {
        mainController = controller;
    }

    /**
     * Sets the coffee sizes, quantities, and adds the checkboxes
     */
    @FXML
    private void initialize(){
        ObservableList<String> coffeeSizes = FXCollections.observableArrayList("Short", "Tall", "Grande", "Venti");
        comboCoffeeSizes.setItems(coffeeSizes);

        ObservableList<Integer> coffeeQuantity = FXCollections.observableArrayList(1, 2, 3, 4, 5);
        comboCoffeeQuantity.setItems(coffeeQuantity);
        resetScreen();
        calculateTotal();
        checkBoxes.add(creamCheckBox);
        checkBoxes.add(syrupCheckBox);
        checkBoxes.add(milkCheckBox);
        checkBoxes.add(caramelCheckBox);
        checkBoxes.add(whippedCreamCheckBox);

    }

    /**
     * Calls the calculateTotal() method.
     * @param event button clicked
     */
    @FXML
    void selection(ActionEvent event) {
        calculateTotal();
    }

    /**
     * Calculates the total of the coffee order.
     */
    private void calculateTotal(){
        String size = comboCoffeeSizes.getSelectionModel().getSelectedItem();
        int quantity = comboCoffeeQuantity.getSelectionModel().getSelectedItem();
        double sizePrice = getSizePrice(size);
        double price = quantity * sizePrice + getAddOnsPrice();
        DecimalFormat df = new DecimalFormat("0.00");
        subtotalText.setText("$" + df.format(price));
    }

    /**
     * Calls the calculateTotal() method.
     * @param event checkbox selected
     */
    @FXML
    void checkBoxSelection(ActionEvent event) {
        calculateTotal();
    }

    /**
     * Calculates the price of the add-ons.
     * @return double price
     */
    private double getAddOnsPrice(){
        double price = 0;
        if(creamCheckBox.isSelected()){
            price += ADD_ON_PRICE;
        }
        if(syrupCheckBox.isSelected()){
            price += ADD_ON_PRICE;
        }
        if(milkCheckBox.isSelected()){
            price += ADD_ON_PRICE;
        }
        if(caramelCheckBox.isSelected()){
            price += ADD_ON_PRICE;
        }
        if(whippedCreamCheckBox.isSelected()){
            price += ADD_ON_PRICE;
        }
        return price;
    }

    /**
     * Returns the price of the coffee based on its size.
     * @param size Size
     * @return double price based on size
     */
    private double getSizePrice(String size){
        if(size.equals(CoffeeSize.SHORT.toString())){
            return CoffeeSize.SHORT.getPrice();
        }
        else if(size.equals(CoffeeSize.TALL.toString())){
            return CoffeeSize.TALL.getPrice();
        }
        else if(size.equals(CoffeeSize.GRANDE.toString())){
            return CoffeeSize.GRANDE.getPrice();
        }
        else if(size.equals(CoffeeSize.VENTI.toString())){
            return CoffeeSize.VENTI.getPrice();
        }
        else{
            return 0;
        }
    }

    /**
     * Adds coffee to the order database.
     * @param event "add to order" button clicked
     */
    @FXML
    void addToOrder(ActionEvent event) {
        int quantity = comboCoffeeQuantity.getSelectionModel().getSelectedItem();
        String size = comboCoffeeSizes.getSelectionModel().getSelectedItem();
        CoffeeSize coffeeSize = CoffeeSize.SHORT;
        for(CoffeeSize c : CoffeeSize.values()){
            if(c.toString().equals(size)){
                coffeeSize = c;
            }
        }
        ArrayList addOns = new ArrayList<AddOn>();
        Coffee c = new Coffee(quantity, coffeeSize, addOns);
        createAddOnsList(c);
        mainController.addCoffee(c);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Alert");
        alert.setContentText("Coffee order successfully added!");
        alert.showAndWait();

        resetScreen();
    }

    /**
     * Creates an ArrayList of all the selected add-ons.
     * @return ArrayList<AddOn>
     */
    private ArrayList<AddOn> createAddOnsList(Coffee c){
        ArrayList addOns = new ArrayList<AddOn>();
        if(creamCheckBox.isSelected()){
            c.add(AddOn.CREAM);
        }
        if(syrupCheckBox.isSelected()){
             c.add(AddOn.SYRUP);
        }
        if(milkCheckBox.isSelected()){
            c.add(AddOn.MILK);
        }
        if(caramelCheckBox.isSelected()){
            c.add(AddOn.CARAMEL);
        }
        if(whippedCreamCheckBox.isSelected()){
            c.add(AddOn.WHIPPEDCREAM);
        }
        return addOns;
    }

    /**
     * Resets to the default values.
     */
    private void resetScreen(){
        comboCoffeeSizes.setValue("Short");
        comboCoffeeQuantity.setValue(1);
        subtotalText.setText("$0.00");
        for(CheckBox c : checkBoxes){
            c.setSelected(false);
        }

    }
}