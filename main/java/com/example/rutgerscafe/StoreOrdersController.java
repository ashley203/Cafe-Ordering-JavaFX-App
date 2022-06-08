package com.example.rutgerscafe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

/**
 This is the StoreOrdersController class that handles the Store Orders commands & GUI.
 @author Ashley Mathai, Shehneel Ashraf
 */
public class StoreOrdersController {
    @FXML
    private ComboBox<Integer> comboStoreOrdersQuantity;

    @FXML
    private TextField totalText;

    private MainController mainController;

    @FXML
    private ListView<String> storeOrdersList;

    private final double STATE_TAX = 0.06625;

    /**
     * Sets the controller and the quantity / text to default values.
     * @param controller controller
     */
    public void setMainController(MainController controller)
    {
        mainController = controller;
        ObservableList<Integer> storeOrdersQuantity = FXCollections.observableArrayList();
        ArrayList<Order> orderArray = mainController.storeOrders.getOrdersDatabase();
        for (int i = 0; i < mainController.storeOrders.getOrdersDatabase().size(); i++){
            storeOrdersQuantity.add(orderArray.get(i).getOrderNumber());
        }
        comboStoreOrdersQuantity.setItems(storeOrdersQuantity);
        totalText.setText("0.00");
    }

    /**
     * Displays the Order information that has been selected.
     * @param event Order number selected
     */
    @FXML
    void showOrders(ActionEvent event){
        if (comboStoreOrdersQuantity.getSelectionModel().getSelectedItem() != null){
            int orderNumberSelected = comboStoreOrdersQuantity.getSelectionModel().getSelectedItem();
            Order o = mainController.storeOrders.getOrder(orderNumberSelected);
            ObservableList<String> newOrderList = o.returnMenuItems();
            storeOrdersList.setItems(newOrderList);
            printTotal();
        }
    }

    // SHORTEN
    /**
     * Cancels the order and removes from the Order database.
     * @param event button clicked
     */
    @FXML
    void cancelOrder(ActionEvent event) {
        if(comboStoreOrdersQuantity.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert");
            alert.setContentText("You have to select an order to cancel.");
            alert.showAndWait();
        }
            int orderNumberSelected = comboStoreOrdersQuantity.getSelectionModel().getSelectedItem();
            Order o = mainController.storeOrders.getOrder(orderNumberSelected);
            mainController.storeOrders.remove(o);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Alert");
            alert.setContentText("Order successfully cancelled!");
            alert.showAndWait();

            ObservableList<Integer> storeOrdersQuantity = FXCollections.observableArrayList();
            ArrayList<Order> orderArray = mainController.storeOrders.getOrdersDatabase();
            cancelBasedOnDatabaseSize(orderArray);
    }

    /**
     * This method is called in cancelOrder(). Uses the Order database size to cancel the orders.
     * @param orderArray Database of Orders placed
     */
    public void cancelBasedOnDatabaseSize(ArrayList<Order> orderArray){
        ObservableList<Integer> storeOrdersQuantity = FXCollections.observableArrayList();
        if (orderArray.size() != 0){
            for (int i = 0; i < orderArray.size(); i++){
                storeOrdersQuantity.add(orderArray.get(i).getOrderNumber());
            }
            comboStoreOrdersQuantity.setItems(storeOrdersQuantity);
            Order firstOrder = orderArray.get(0);
            comboStoreOrdersQuantity.setValue(firstOrder.getOrderNumber());
            storeOrdersList.setItems(firstOrder.returnMenuItems());
            totalText.setText("$0.00");
        }
        else {
            comboStoreOrdersQuantity.setItems(null);
            comboStoreOrdersQuantity.setValue(null);
            storeOrdersList.setItems(null);
            totalText.setText("$0.00");
        }
    }

    /**
     * Export all orders to a txt file
     * @param event button clicked
     */
    @FXML
    void exportOrders(ActionEvent event) {
        if(comboStoreOrdersQuantity.getItems().size() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert");
            alert.setContentText("There must be orders to export.");
            alert.showAndWait();
        }
        else {
            File file = new File("orderText.txt");
            PrintWriter printWriter = null;
            try
            {
                printWriter = new PrintWriter(file);
                printWriter.println(mainController.storeOrders.print());
                printWriter.close();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Alert");
                alert.setContentText("Orders successfully exported!");
                alert.showAndWait();
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
                printWriter.close();
            }
        }
    }

    /**
     * Total of each order is calculated and displayed.
     */
    @FXML
    void printTotal(){
        double subtotal = 0;
        int orderNumberSelected = comboStoreOrdersQuantity.getSelectionModel().getSelectedItem();
        Order o = mainController.storeOrders.getOrder(orderNumberSelected);
        ArrayList<MenuItem> menuItemArray = o.getItems();
        for(MenuItem m: menuItemArray){
            subtotal += m.itemPrice();
        }
        double salesTax = subtotal * STATE_TAX;
        DecimalFormat df = new DecimalFormat("0.00");
        totalText.setText("$" + df.format(subtotal + salesTax));
    }
}

