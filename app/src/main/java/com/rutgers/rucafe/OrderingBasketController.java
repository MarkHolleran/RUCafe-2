package com.rutgers.rucafe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * Class that represents the GUI interface for an Order Object
 *
 * Within this class are all the GUI's components and their
 * corresponding functionality
 *
 * @author Mark Holleran, Abhitej Bokka
 */
public class OrderingBasketController {

    @FXML
    private Label subTotalOrderCost;

    @FXML
    private Label totalOrderCost;

    @FXML
    private ListView<MenuItem> totalOrderOutput;

    @FXML
    private Label totalOrderTax;

    private ShopMainMenuController shopMainMenuController;

    /**
     * Default constructor for the OrderingBasketController
     */
    public OrderingBasketController(){}


    /**
     * Pulls all current data such as the most recent StoreOrders Object,
     * Order Objects for both Donut and Coffee orders, and an Order Objet
     * for the combination of Coffee and Donut Order Objects. The Listview
     * component is then updated with the most up-to-date list of all the MenuItems
     * from the ObservableList
     *
     * @param shopMainMenuController Controller that holds all the current state of program information
     */
    public void createShopMainMenuController(ShopMainMenuController shopMainMenuController){
        this.shopMainMenuController = shopMainMenuController;
        totalOrderOutput.setItems(shopMainMenuController.getOrderObservableList());
        updateOrders();
    }

    /**
     * This function updates the ListView with the most up-to-date ObservableList
     * holding all MenuItems of an Order. The subtotal, tax, and total order prices
     * are then recalculated based on the MenuItems within the totalCustomerOrder Order Object.
     */
    public void updateOrders(){
        ObservableList<MenuItem> created = FXCollections.observableArrayList();
        created.setAll(shopMainMenuController.getOrderObservableList());
        totalOrderOutput.setItems(created);
        subTotalOrderCost.setText((String.format("%.2f", shopMainMenuController.getTotalCustomerOrder().orderPrice())));
        totalOrderCost.setText((String.format("%.2f", shopMainMenuController.getTotalCustomerOrder().orderPriceTax())));
        totalOrderTax.setText((String.format("%.2f", shopMainMenuController.getTotalCustomerOrder().orderPriceTax() - shopMainMenuController.getTotalCustomerOrder().orderPrice())));
    }

    /**
     * When the place Order button is pressed, the Order Object
     * containing all MenuItems is added to the StoreOrders Object
     * containing all Orders.
     */
    @FXML
    void placeOrder() {
        if (shopMainMenuController.getOrderObservableList().isEmpty()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("The Order list is empty");
            errorAlert.setContentText("The list of Orders must be populated before placing an Order");
            errorAlert.showAndWait();
        } else {
            shopMainMenuController.getTotalCustomerOrder().setOrderNumber(shopMainMenuController.getUniqueOrderNumber());
            shopMainMenuController.getStoreOrders().add(shopMainMenuController.getTotalCustomerOrder());
            shopMainMenuController.getCoffeeCustomerOrder().getOrder().clear();
            shopMainMenuController.getDonutCustomerOrder().getOrder().clear();
            updateOrders();
        }
    }

    /**
     * When the Remove button is pressed, the selected MenuItem Object is removed from
     * the ObservableList it was contained in. The ObservableList containing all
     * MenuItems is then updated along with the subtotal, tax, and order total.
     */
    @FXML
    void removeItemFromOrder() {
        if (totalOrderOutput.getSelectionModel().getSelectedItem() != null && (totalOrderOutput.getSelectionModel().getSelectedItem() instanceof Donut)){
            shopMainMenuController.getDonutCustomerOrder().remove(totalOrderOutput.getSelectionModel().getSelectedItem());
            updateOrders();

        }else if(totalOrderOutput.getSelectionModel().getSelectedItem() != null && (totalOrderOutput.getSelectionModel().getSelectedItem() instanceof Coffee)){
            shopMainMenuController.getCoffeeCustomerOrder().remove(totalOrderOutput.getSelectionModel().getSelectedItem());
            updateOrders();
        }else{
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Item couldn't be removed");
            errorAlert.setContentText("Please make sure you've selected an Item to remove");
            errorAlert.showAndWait();
        }
    }
}

