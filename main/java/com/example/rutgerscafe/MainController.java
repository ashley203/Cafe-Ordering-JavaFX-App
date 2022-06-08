package com.example.rutgerscafe;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import java.util.ArrayList;

import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 This is the MainController class that handles the GUI and switches screens.
 @author Ashley Mathai, Shehneel Ashraf
 */
public class MainController {
    ArrayList<MenuItem> toBeOrderedItems = new ArrayList<MenuItem>();
    StoreOrders storeOrders = new StoreOrders();

    /**
     * Switches to Order Donuts screen.
     * @param event button clicked
     * @throws IOException
     */
    public void switchToOrderDonutScreen(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderingDonutsView.fxml"));
        Parent root1 = (Parent) loader.load();
        Scene newscene = new Scene(root1, 400, 400);
        Stage newstage = new Stage();
        newstage.setTitle("Order Donuts");
        newstage.setScene(newscene);
        newstage.show();
        OrderingDonutsController donutViewController = loader.getController();
        donutViewController.setMainController(this);
    }

    /**
     * Adds Donut object to list of menu items.
     * @param d donut
     */
    public void addDonut(Donut d){
        toBeOrderedItems.add(d);
    }

    /**
     * Adds Coffee object to list of menu items.
     * @param c coffee
     */
    public void addCoffee(Coffee c){
        toBeOrderedItems.add(c);
    }

    /**
     * Switches to Order Coffee screen.
     * @param event button clicked
     * @throws IOException
     */
    public void switchToOrderCoffeeScreen(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderingCoffeeView.fxml"));
        Parent root1 = (Parent) loader.load();
        Scene newscene = new Scene(root1, 400, 400);
        Stage newstage = new Stage();
        newstage.setTitle("Order Coffee");
        newstage.setScene(newscene);
        newstage.show();
        OrderingCoffeeController coffeeViewController = loader.getController();
        coffeeViewController.setMainController(this);
    }

    /**
     * Switches to Your Order screen.
     * @param event button clicked
     * @throws IOException
     */
    public void switchToYourOrderScreen(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("YourOrderView.fxml"));
        Parent root1 = (Parent) loader.load();
        YourOrderController yourOrderController = loader.getController();
        yourOrderController.setMainController(this);

        Scene newscene = new Scene(root1, 400, 400);
        Stage newstage = new Stage();
        newstage.setTitle("Your Order");
        newstage.setScene(newscene);
        newstage.show();
    }

    /**
     * Switches to Store Orders screen.
     * @param event button clicked
     * @throws IOException
     */
    public void switchToStoreOrdersScreen(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StoreOrdersView.fxml"));
        Parent root1 = (Parent) loader.load();
        StoreOrdersController storeOrdersController = loader.getController();
        storeOrdersController.setMainController(this);

        Scene newscene = new Scene(root1, 400, 400);
        Stage newstage = new Stage();
        newstage.setTitle("Store Orders");
        newstage.setScene(newscene);
        newstage.show();
    }
}