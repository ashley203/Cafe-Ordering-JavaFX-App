package com.example.rutgerscafe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import java.text.DecimalFormat;
import javafx.scene.control.Alert.AlertType;

/**
 This is the OrderingDonutsController class that handles the Ordering Donuts commands & GUI.
 @author Ashley Mathai, Shehneel Ashraf
 */
public class OrderingDonutsController {
    @FXML
    private ComboBox<String> comboDonutTypes;

    @FXML
    private ListView<String> donutFlavorsList, donutsToOrder;

    @FXML
    private ComboBox<Integer> comboDonutQuantity;

    @FXML
    private TextArea subtotalText;

    private final int REMOVE_QUANTITY_INDEX = 4;
    private final int GET_QUANTITY_INDEX = 2;

    ObservableList<String> yeastDonutFlavors= FXCollections.observableArrayList
            ("Jelly", "Old Fashioned", "Powdered");
    ObservableList<String> cakeDonutFlavors = FXCollections.observableArrayList
            ("Sugar", "Glazed", "Strawberry Frosted");
    ObservableList<String> donutHoleFlavors = FXCollections.observableArrayList
            ("Cruller", "Boston Kreme", "Chocolate Glazed");

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
     * Sets donut types, donut quantities, and default text.
     */
    @FXML
    private void initialize(){
        ObservableList<String> donutTypes = FXCollections.observableArrayList("Yeast Donut", "Cake Donut", "Donut Hole");
        comboDonutTypes.setItems(donutTypes);

        ObservableList<Integer> donutQuantity = FXCollections.observableArrayList(1, 2, 3, 4, 5);
        comboDonutQuantity.setItems(donutQuantity);
        subtotalText.setText("$0.00");
    }

    /**
     * Calls the show() method.
     * @param event combo box clicked
     */
    @FXML
    void showDonuts(ActionEvent event) {
        show();
    }

    /**
     * Displays donut flavors based on which type is selected.
     */
    private void show(){
        ObservableList<String> donutFlavors;
        String donutTypeSelection = comboDonutTypes.getSelectionModel().getSelectedItem();
        if (donutTypeSelection.equals("Yeast Donut")){
            donutFlavors = yeastDonutFlavors;
        }
        else if (donutTypeSelection.equals("Cake Donut")){
            donutFlavors = cakeDonutFlavors;
        }
        else{
            donutFlavors = donutHoleFlavors;
        }
        donutFlavorsList.setItems(donutFlavors);
    }

    /**
     * Removes donut from flavors list.
     * @param donut donut to remove
     */
    private void removeFromDonutFlavorsList(String donut){
        String donutTypeSelection = comboDonutTypes.getSelectionModel().getSelectedItem();
        if (donutTypeSelection.equals("Yeast Donut")){
            yeastDonutFlavors.remove(donut);
        }
        else if (donutTypeSelection.equals("Cake Donut")){
            cakeDonutFlavors.remove(donut);
        }
        else{
            donutHoleFlavors.remove(donut);
        }
        show();
    }

    /**
     * Adds donut to flavors list.
     * @param donut donut to add
     */
    private void addToDonutFlavorsList(String donut){
        if (isYeastDonut(donut)){
            yeastDonutFlavors.add(donut);
        }
        else if (isCakeDonut(donut)){
            cakeDonutFlavors.add(donut);
        }
        else{
            donutHoleFlavors.add(donut);
        }
        show();
    }

    /**
     * Adds donut to the right hand list menu that displays donuts currently added.
     * @param event button clicked
     */
    @FXML
    void addDonut(ActionEvent event) {
        // ------------------MUST SELECT QUANTITY AND DONUT--------------------
        if (donutFlavorsList.getSelectionModel().getSelectedItem() == null ||
                comboDonutQuantity.getSelectionModel().getSelectedItem() == null){
            // MUST SELECT DONUT FLAVOR ERROR MESSAGE
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Alert");
            alert.setContentText("You have to select a donut flavor and quantity to add it.");
            alert.showAndWait();
            return;
        }
        else {
            String item = donutFlavorsList.getSelectionModel().getSelectedItem();
            donutsToOrder.getItems().add(item + " (" +
                    comboDonutQuantity.getSelectionModel().getSelectedItem() + ")");

            removeFromDonutFlavorsList(item);

            calculateSubTotal();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Alert");
            alert.setContentText("Donut successfully added!");
            alert.showAndWait();
        }
    }

    /**
     * Calculates the subtotal of all the donuts added.
     */
    private void calculateSubTotal(){
        String selection = donutFlavorsList.getSelectionModel().getSelectedItem();
        int quantity;
        double subtotal = 0;
        for(int i = 0; i < donutsToOrder.getItems().size(); i++){
            String donut = donutsToOrder.getItems().get(i);
            quantity = getQuantityFromString(donut);
            donut = removeQuantityInString(donut);
            if (isYeastDonut(donut)){
                subtotal += DonutType.YEASTDONUT.getPrice() * quantity;
            }
            else if (isCakeDonut(donut)){
                subtotal += DonutType.CAKEDONUT.getPrice() * quantity;
            }
            else if (isDonutHole(donut)){
                subtotal += DonutType.DONUTHOLE.getPrice() * quantity;
            }
        }

        DecimalFormat df = new DecimalFormat("0.00");
        subtotalText.setText("$" + df.format(subtotal));
    }

    /**
     * Removes the quantity from the donut string
     * @param line String
     * @return String
     */
    private String removeQuantityInString(String line){
        line = line.trim();
        return line.substring(0, line.length() - REMOVE_QUANTITY_INDEX);
    }

    /**
     * Adds the quantity to the donut string
     * @param line String
     * @return String
     */
    private int getQuantityFromString(String line){
        char value = line.charAt(line.length()- GET_QUANTITY_INDEX);
        return Character.getNumericValue(value);
    }

    /**
     * Deletes donut from the righthand list menu that displays donuts currently added.
     * @param event button clicked
     */
    @FXML
    void deleteDonut(ActionEvent event){
        // -------------------MUST SELECT DONUT----------------------------
        if(donutsToOrder.getSelectionModel().getSelectedItem() == null){
            //MUST SELECT DONUT TO REMOVE ERROR MESSAGE
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Alert");
            alert.setContentText("You have to select a donut to remove it.");
            alert.showAndWait();
            return;
        }
        else {
            String donut = donutsToOrder.getSelectionModel().getSelectedItem();
            donutsToOrder.getItems().remove(donut);

            addToDonutFlavorsList(removeQuantityInString(donut));

            calculateSubTotal();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Alert");
            alert.setContentText("Donut successfully removed!");
            alert.showAndWait();
        }
    }

    /**
     * Adds the donuts to the order.
     * @param event button clicked
     */
    @FXML
    void addToOrder(ActionEvent event) {
        //-----------------MAKE SURE DONUTSTOORDERNOT EMPTY---------------------
        if(donutsToOrder.getItems().size() == 0){
            //MUST SELECT DONUT TO REMOVE ERROR MESSAGE
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Alert");
            alert.setContentText("You have to have a donut to order.");
            alert.showAndWait();
            return;
        }
        while(donutsToOrder.getItems().size() != 0){
            String line = donutsToOrder.getItems().get(0);
            int quantity = getQuantityFromString(line);
            String donutName = removeQuantityInString(line);
            donutsToOrder.getItems().remove(line);
            addToDonutFlavorsList(donutName);
            for(DonutFlavor flavor : DonutFlavor.values()){
                if(flavor.toString().equals(donutName)){
                    Donut d = new Donut(quantity, flavor);
                    mainController.addDonut(d);
                }
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Alert");
            alert.setContentText("Donut order successfully added!");
            alert.showAndWait();
        }
        resetScreen();
    }

    /**
     * Resets to default values.
     */
    private void resetScreen(){
        subtotalText.setText("$0.00");
        comboDonutQuantity.setValue(null);
    }

    /**
     * Checks if the donut type is a yeast donut.
     * @param donutType type of donut
     * @return boolean
     */
    private boolean isYeastDonut(String donutType){
        if (donutType.equals("Jelly") || donutType.equals("Old Fashioned") || donutType.equals("Powdered")){
            return true;
        }
        return false;
    }

    /**
     * Checks if the donut type is a cake donut.
     * @param donutType type of donut
     * @return boolean
     */
    private boolean isCakeDonut(String donutType){
        if (donutType.equals("Sugar") || donutType.equals("Glazed") || donutType.equals("Strawberry Frosted")){
            return true;
        }
        return false;
    }

    /**
     * Checks if the donut type is a donut hole.
     * @param donutType type of donut
     * @return boolean
     */
    private boolean isDonutHole(String donutType){
        if (donutType.equals("Cruller") || donutType.equals("Boston Kreme") || donutType.equals("Chocolate Glazed")){
            return true;
        }
        return false;
    }
}