package com.rutgers.rucafe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

/**
 * Class that represents the GUI interface for accessing a Store Orders Object
 *
 * Within this class are all the GUI components and their
 * corresponding functionality
 *
 * @author Mark Holleran, Abhitej Bokka
 */
public class StoreOrdersController {

    private ShopMainMenuController shopMainMenuController;

    @FXML
    private ListView<String> allOrdersOutput;

    @FXML
    private CheckBox priceSelection;

    /**
     * Default constructor for StoreOrdersController
     */
    public StoreOrdersController(){}

    /**
     * Pulls all current data such as the most recent StoreOrders Object,
     * Order Objects for both Donut and Coffee orders, and an Order Objet
     * for the combination of Coffee and Donut Order Objects. The Listview
     * component is then updated with the most up-to-date list of all the Orders
     * in String form to the ListView
     *
     * @param shopMainMenuController Controller that holds all the current state of program information
     */
    public void createShopMainMenuController(ShopMainMenuController shopMainMenuController) {
        this.shopMainMenuController = shopMainMenuController;
        update();
    }

    /**
     * Updates the ObservableList of Strings displaying all the Orders and their contents
     */
    public void update() {
        ObservableList<String> updatedObservableList = FXCollections.observableArrayList();
        for (Order order : shopMainMenuController.getStoreOrders().getOrderList()) {
            updatedObservableList.add("Order: " + order.toString() + " " + "Total: $" + String.format("%.2f", order.orderPriceTax()));
        }
        allOrdersOutput.setItems(updatedObservableList);
    }

    /**
     * Cancels an Order by removing it from the ListView then updating the Listview
     */
    @FXML
    private void cancelOrder() {
        if (allOrdersOutput.getSelectionModel().getSelectedItem() != null) {
            shopMainMenuController.getStoreOrders().getOrderList().remove((allOrdersOutput.getSelectionModel().getSelectedIndex()));
            update();
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("The list is empty or an Order is not selected");
            errorAlert.setContentText("Please make sure you've selected an order to cancel");
            errorAlert.showAndWait();
        }
    }

    /**
     * Exports all orders to a Text File
     */
    @FXML
    private void exportOrders() {
        if (!shopMainMenuController.getStoreOrderObservableList().isEmpty()) {
            try {
                FileChooser chooser = new FileChooser();
                chooser.setTitle("Select a text file or create one to export to");
                chooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("Text Documents", "*.txt"));
                Stage stage = new Stage();
                File file = chooser.showSaveDialog(stage);
                displayExportAlert(file);
            } catch (Exception e) {

            }
        } else {
            createAlert();
        }

    }

    /**
     * Creates and displays an Export Alert Message Box depending on success of export
     *
     * @param file File to store the exports
     */
    private void displayExportAlert(File file){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (priceSelection.isSelected() && shopMainMenuController.getStoreOrders().export(file)) {
            alert.setHeaderText("Successfully Exported.");
            alert.setContentText("The text file now contains all store Orders with price");

        }else if(!priceSelection.isSelected() && shopMainMenuController.getStoreOrders().exportNoPrice(file)){
            alert.setHeaderText("Successfully Exported.");
            alert.setContentText("The text file now contains all store Orders");
        } else {
            alert.setHeaderText("Error");
            alert.setContentText("Error occurred when exporting Orders.");
        }
        alert.show();
    }

    /**
     * Creates and displays an errorAlert Message Box when the ListView
     * of Orders is empty
     */
    private void createAlert(){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("There are no Orders to export");
        errorAlert.setContentText("place an Order before trying to export.");
        errorAlert.showAndWait();
    }
}