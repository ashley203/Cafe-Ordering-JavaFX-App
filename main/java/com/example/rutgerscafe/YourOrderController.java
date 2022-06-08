package com.example.rutgerscafe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

/**
 This is the YourOrderController class that handles Your Order commands & GUI.
 @author Ashley Mathai, Shehneel Ashraf
 */
public class YourOrderController {
     @FXML
     private TextField salesTaxText, subtotalText, totalText;

    @FXML
    private ListView<String> orderList;

    private MainController mainController;

    private final double STATE_TAX = 0.06625;

    /**
     * Sets the controller and calls the specified methods.
     * @param controller controller
     */
    public void setMainController(MainController controller) {
        mainController = controller;
        updateOrderList();
        calculatePrices();
    }

    /**
     * Adds an order to the order database, if the order is selected.
     * @param event button clicked
     */
    @FXML
    void placeOrder(ActionEvent event) {
        if(orderList.getItems().size() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert");
            alert.setContentText("You have to have items to make an order.");
            alert.showAndWait();
        }
        else {
            ArrayList<MenuItem> orderedItems = (ArrayList<MenuItem>) mainController.toBeOrderedItems.clone();
            Order o = new Order(orderedItems);
            mainController.storeOrders.add(o);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Alert");
            alert.setContentText("Your order has been successfully placed!");
            alert.showAndWait();

            mainController.toBeOrderedItems.clear();
            orderList.setItems(null);
            subtotalText.setText("$0.00");
            salesTaxText.setText("$0.00");
            totalText.setText("$0.00");
        }
    }

    /**
     * Item is removed from an order, if the order exists / item is selected.
     * @param event button clicked
     */
    @FXML
    void removeItem(ActionEvent event) {
        //----------------CHECK THAT AN ITEM IS SELECTED--------------
        if(orderList.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert");
            alert.setContentText("You have to select an order to remove it.");
            alert.showAndWait();
        }
        else {
            String selection = orderList.getSelectionModel().getSelectedItem();

            for (Iterator<MenuItem> iterator = mainController.toBeOrderedItems.iterator(); iterator.hasNext();){
                String m = iterator.next().toString();
                if (m.equals(selection)){
                    iterator.remove();
                }
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Alert");
            alert.setContentText("Item successfully removed from your order!");
            alert.showAndWait();

            updateOrderList();
            calculatePrices();
        }
    }

    /**
     * Updates the order list that is displayed.
     */
    private void updateOrderList(){
        ObservableList<String> newOrderList = FXCollections.observableArrayList();
        for(MenuItem m: mainController.toBeOrderedItems){
            newOrderList.add(m.toString());
        }
         orderList.setItems(newOrderList);
    }

    /**
     * Calculates the prices of the order and displays.
     */
    private void calculatePrices(){
        double subtotal = 0;
        for(MenuItem m: mainController.toBeOrderedItems){
            subtotal += m.itemPrice();
        }
        double salesTax = subtotal * STATE_TAX;

        DecimalFormat df = new DecimalFormat("0.00");
        subtotalText.setText("$" + df.format(subtotal));
        salesTaxText.setText("$" + df.format(salesTax));
        totalText.setText("$" + df.format(subtotal + salesTax));
    }
}