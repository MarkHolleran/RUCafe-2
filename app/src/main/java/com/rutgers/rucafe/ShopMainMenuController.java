package com.rutgers.rucafe;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Class that represents the GUI interface of the Main Menu
 *
 * Within this class are all the GUI components and their
 * corresponding functionality
 *
 * @author Mark Holleran, Abhitej Bokka
 */
public class ShopMainMenuController {

    @FXML
    private Button orderCoffee;

    @FXML
    private Button orderDonut;

    @FXML
    private Button displayCurrentOrder;

    @FXML
    private Button displayAllOrders;

    private int runningUniqueOrderNumber = 0;
    private final StoreOrders storeOrders = new StoreOrders();
    private final Order donutCustomerOrder = new Order();
    private final Order coffeeCustomerOrder = new Order();
    private Order totalCustomerOrder = new Order();

    /**
     * Default constructor for ShopMainMenuController
     */
    public ShopMainMenuController(){}

    /**
     * Returns a StoreOrder Object's ObservableList
     * containing a combination of the Coffee and Donut ObservableLists
     *
     * @return ObservableList of Order Objects
     */
    public ObservableList<Order> getStoreOrderObservableList() {
        return storeOrders.getOrderList();
    }

    /**
     * Returns Order Object containing MenuItems
     * from the contents of donutCustomerOrder and coffeeCustomerOrder
     *
     * @return Order Object
     */
    public Order getTotalCustomerOrder() {
        return totalCustomerOrder;
    }

    /**
     * Combines Coffee and Donut Order Objects
     *
     * @return ObservableList of MenuItems which contain
     * all contents from donutCustomerOrder and coffeeCustomerOrder
     */
    public ObservableList<MenuItem> getOrderObservableList() {
        totalCustomerOrder = new Order();
        totalCustomerOrder.getOrder().addAll(getCoffeeCustomerOrder().getOrder());
        totalCustomerOrder.getOrder().addAll(getDonutCustomerOrder().getOrder());
        return this.totalCustomerOrder.getOrder();
    }

    /**
     * Returns the Order for the donuts
     *
     * @return Order Object containing Donut order
     */
    public Order getDonutCustomerOrder() {
        return donutCustomerOrder;
    }

    /**
     * Returns the Order for the coffees
     *
     * @return Order Object containing Coffee order
     */
    public Order getCoffeeCustomerOrder() {
        return coffeeCustomerOrder;
    }

    /**
     * Returns all the orders made
     *
     * @return StoreOrder Object containing all the Order Objects
     */
    public StoreOrders getStoreOrders() {
        return storeOrders;
    }

    /**
     * Increments the total number of orders for order identification
     *
     * @return Integer representing an order number
     */
    public int getUniqueOrderNumber() {
        ++runningUniqueOrderNumber;
        return runningUniqueOrderNumber;
    }

    /**
     * Creates a new window to view the contents of the StoreOrders Object
     */
    @FXML
    void displayAllOrders() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ShopMainMenuMain.class.getResource("StoreOrdersView.fxml"));
            Parent root1 = (Parent) (fxmlLoader.load());
            StoreOrdersController StoreOrdersController = fxmlLoader.getController();
            StoreOrdersController.createShopMainMenuController(this);
            Stage stage = new Stage();
            stage.setTitle("Store Orders");
            stage.setScene(new Scene(root1));
            stage.resizableProperty().setValue(false);
            disableAllButtons();
            stage.show();
            stage.setOnCloseRequest(eventCalled -> enableAllButtons());
        } catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
    }

    /**
     * Creates a window to view the contents of the current order
     */
    @FXML
    void displayCurrentOrder() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ShopMainMenuMain.class.getResource("OrderingBasketView.fxml"));
            Parent root1 = (Parent) (fxmlLoader.load());
            OrderingBasketController orderingBasketController = fxmlLoader.getController();
            orderingBasketController.createShopMainMenuController(this);
            Stage stage = new Stage();
            stage.setTitle("Current Order");
            stage.setScene(new Scene(root1));
            stage.resizableProperty().setValue(false);
            disableAllButtons();
            stage.show();
            stage.setOnCloseRequest(eventCalled -> enableAllButtons());
        } catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
    }

    /**
     * Creates a window to order a Coffee form
     */
    @FXML
    void orderCoffee() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShopCoffeeView.fxml"));
            Parent root1 = (Parent) (fxmlLoader.load());
            ShopCoffeeController ShopCoffeeController = fxmlLoader.getController();
            ShopCoffeeController.createShopMainMenuController(this);
            Stage stage = new Stage();
            stage.setTitle("Order a Coffee");
            stage.setScene(new Scene(root1));
            stage.resizableProperty().setValue(false);
            disableAllButtons();
            stage.show();
            stage.setOnCloseRequest(eventCalled -> enableAllButtons());

        } catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
    }

    /**
     * Creates a window to order Donuts from
     */
    @FXML
    void orderDonut() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShopDonutView.fxml"));
            Parent root1 = (Parent) (fxmlLoader.load());    
            ShopDonutController shopDonutController = fxmlLoader.getController();
            shopDonutController.createShopMainMenuController(this);
            Stage stage = new Stage();
            stage.setTitle("Order a Donut");
            stage.setScene(new Scene(root1));
            stage.resizableProperty().setValue(false);
            disableAllButtons();
            stage.show();
            stage.setOnCloseRequest(eventCalled -> enableAllButtons());

        } catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
    }

    /**
     * Enables all menu buttons after a window is closed
     */
    public void enableAllButtons() {
        orderDonut.setDisable(false);
        orderCoffee.setDisable(false);
        displayAllOrders.setDisable(false);
        displayCurrentOrder.setDisable(false);
    }

    /**
     * Disables all menu buttons after a window is opened
     */
    public void disableAllButtons() {
        orderDonut.setDisable(true);
        orderCoffee.setDisable(true);
        displayAllOrders.setDisable(true);
        displayCurrentOrder.setDisable(true);
    }
}
