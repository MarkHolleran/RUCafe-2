package com.rutgers.rucafe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.ArrayList;
import static com.rutgers.rucafe.Coffee.*;

/**
 * Class that represents the GUI interface for ordering a Coffee
 *
 * Within this class are all the GUI components and their
 * corresponding functionality
 *
 * @author Mark Holleran, Abhitej Bokka
 */
public class ShopCoffeeController {

    @FXML
    private ToggleGroup coffeeType;

    @FXML
    private ToggleButton grandeCoffee;

    @FXML
    private ToggleButton shortCoffee;

    @FXML
    private ToggleButton tallCoffee;

    @FXML
    private ToggleButton ventiCoffee;

    @FXML
    private ToggleButton caramelAddin;

    @FXML
    private ToggleButton creamAddin;

    @FXML
    private ToggleButton syrupAddin;

    @FXML
    private ToggleButton whippedCreamAddin;

    @FXML
    private Label totalCoffeeOrderCost;

    @FXML
    private ListView<MenuItem> totalCoffeeOrderOutput;

    private ShopMainMenuController shopMainMenuController;

    private static final int STRINGOFFSET = 1;
    private static final int ZEROSIZE = 0;
    private static final int INITIALCOFFEEQUANTITY = 1;

    /**
     * Default constructor ShopCoffeeController
     */
    public ShopCoffeeController(){}

    /**
     * Pulls all current data such as the most recent StoreOrders Object,
     * Order Objects for both Donut and Coffee orders, and an Order Objet
     * for the combination of Coffee and Donut Order Objects. The Listview
     * component is then updated with the most up-to-date list of all the Coffee Objects
     * from an ObservableList displayed in the ListView
     *
     * @param shopMainMenuController Controller that holds all the current state of program information
     */
    public void createShopMainMenuController(ShopMainMenuController shopMainMenuController){
        this.shopMainMenuController = shopMainMenuController;
        totalCoffeeOrderOutput.setItems(shopMainMenuController.getCoffeeCustomerOrder().getOrder());
        updateOrders();
        totalCoffeeOrderCost.setText((String.format("%.2f", shopMainMenuController.getCoffeeCustomerOrder().orderPrice())));
    }

    /**
     * Updates the ListView with the most up-to-date ObservableList
     * holding Coffee Objects.
     */
    public void updateOrders(){
        ObservableList<MenuItem> created = FXCollections.observableArrayList();
        if (shopMainMenuController.getCoffeeCustomerOrder().getOrder().size() > ZEROSIZE) {
            ObservableList<MenuItem> Orders = shopMainMenuController.getCoffeeCustomerOrder().getOrder();
            created.addAll(Orders);
        }
        totalCoffeeOrderOutput.setItems(created);
    }

    /**
     * Adds selected Addins to a Coffee Object's ArrayList of Addins
     *
     * @return ArrayList of Strings containing the Addins that were selected
     */
    private ArrayList<String> createAddins(){
        ArrayList<String> newCoffeeAddins = new ArrayList<>();
        if (creamAddin.isSelected()) {
            newCoffeeAddins.add(CREAM);
        }
        if (syrupAddin.isSelected()) {
            newCoffeeAddins.add(SYRUP);
        }
        if (caramelAddin.isSelected()) {
            newCoffeeAddins.add(CARAMEL);
        }
        if (whippedCreamAddin.isSelected()) {
            newCoffeeAddins.add(WHIPPEDCREAM);
        }
        return newCoffeeAddins;
    }

    /**
     * When the Add button is pressed, a Coffee Object is made
     * and added to the ObservableList of Coffee Objects from ShopMainMenuController
     * and the current total is updated
     */
    @FXML
    void addCoffeeToOrder() {
        if (coffeeType.getSelectedToggle() != null) {
            boolean duplicateDonutFound = false;
            String selectedCoffeeSizeToString = coffeeType.getSelectedToggle().toString();
            ArrayList<String> newCoffeeAddins = createAddins();
            String selectedCoffeeSize = selectedCoffeeSizeToString.substring(selectedCoffeeSizeToString.indexOf("'") + STRINGOFFSET, selectedCoffeeSizeToString.lastIndexOf("'"));
            Coffee newCoffee = new Coffee(selectedCoffeeSize, newCoffeeAddins);
            newCoffee.setQuantity(INITIALCOFFEEQUANTITY);
            for (MenuItem num : shopMainMenuController.getCoffeeCustomerOrder().getOrder()) {
                if (num.compare(newCoffee)) {
                    duplicateDonutFound = true;
                    num.setQuantity(num.getQuantity() + newCoffee.getQuantity());
                    updateOrders();
                }
            }
            if (!duplicateDonutFound) {
                shopMainMenuController.getCoffeeCustomerOrder().add(newCoffee);
                updateOrders();
            }
            editButtons();
            totalCoffeeOrderOutput.setItems(shopMainMenuController.getCoffeeCustomerOrder().getOrder());
            totalCoffeeOrderCost.setText((String.format("%.2f", shopMainMenuController.getCoffeeCustomerOrder().orderPrice())));
        } else {
            createError();
        }
    }

    /**
     * Creates and displays an errorAlert Message box when an Addin
     * but no coffee size is selected or when nothing is selected
     */
    private void createError(){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Coffee Order is not valid");
        errorAlert.setContentText("Please make sure you've selected the Size for the Coffee");
        errorAlert.showAndWait();
    }

    /**
     * Resets all buttons after a Coffee objects has been made
     */
    private void editButtons(){
        coffeeType.getSelectedToggle().setSelected(false);
        creamAddin.setSelected(false);
        syrupAddin.setSelected(false);
        caramelAddin.setSelected(false);
        whippedCreamAddin.setSelected(false);
    }

    /**
     * Removes a selected Coffee Object from the ObservableList
     * and updates the ObservableList along with the total
     */
    @FXML
    void removeCoffeeFromOrder() {
        if (totalCoffeeOrderOutput.getSelectionModel().getSelectedItem() != null) {
            shopMainMenuController.getCoffeeCustomerOrder().remove(totalCoffeeOrderOutput.getSelectionModel().getSelectedItem());
            updateOrders();
            totalCoffeeOrderCost.setText((String.format("%.2f", shopMainMenuController.getCoffeeCustomerOrder().orderPrice())));
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Coffee removal Error");
            errorAlert.setContentText("Please make sure you've selected a Coffee to remove");
            errorAlert.showAndWait();
        }
    }
}
