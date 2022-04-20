package com.rutgers.rucafe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import static rucafe.cs213project4.Donut.*;

/**
 * Class that represents the GUI interface for Ordering a Donut
 *
 * Within this class are all the GUI's components and their
 * corresponding functionality
 *
 * @author Mark Holleran, Abhitej Bokka
 */
public class ShopDonutController {

    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;
    public static final int SIX = 6;
    public static final int SEVEN = 7;
    public static final int EIGHT = 8;
    public static final int NINE = 9;
    public static final int TEN = 10;
    public static final int ELEVEN = 11;
    public static final int TWELVE = 12;
    public static final int INDEX_OFFSET = 1;

    private ShopMainMenuController shopMainMenuControllersDonut;

    @FXML
    private ToggleButton cakeDonut;

    @FXML
    private ToggleGroup donutFlavor;

    @FXML
    private ToggleButton donutHoles;

    @FXML
    private ToggleGroup donutType;

    @FXML
    private ToggleButton flavorChoiceOne;

    @FXML
    private ToggleButton flavorChoiceTwo;

    @FXML
    private ToggleButton flavorChoiceThree;

    @FXML
    private ToggleButton flavorChoiceFour;

    @FXML
    private ComboBox<Integer> quantityOfOrder;

    @FXML
    private ChoiceBox<Integer> quantityOfOrders;

    @FXML
    private ListView<MenuItem> donutListView;

    @FXML
    private Label totalOrderCost;

    @FXML
    private ToggleButton yeastDonut;

    private static final int ZEROSIZE = 0;


    /**
     * Default constructor ShopDonutController
     */
    public ShopDonutController(){}

    /**
     * Pulls all current data such as the most recent StoreOrders Object,
     * Order Objects for both Donut and Coffee orders, and an Order Objet
     * for the combination of Coffee and Donut Order Objects. The Listview
     * component is then updated with the most up-to-date list of all the Donut Objects
     * from an ObservableList displayed in the ListView
     *
     * @param shopMainMenuController Controller that holds all the current state of program information
     */
    public void createShopMainMenuController(ShopMainMenuController shopMainMenuController) {
        this.shopMainMenuControllersDonut = shopMainMenuController;
        updateOrders();
        totalOrderCost.setText((String.format("%.2f", shopMainMenuController.getDonutCustomerOrder().orderPrice())));
    }

    /**
     * Updates the ListView with the most up-to-date ObservableList
     * holding Donut Objects
     */
    public void updateOrders() {
        ObservableList<MenuItem> created = FXCollections.observableArrayList();
        if (shopMainMenuControllersDonut.getDonutCustomerOrder().getOrder().size() > ZEROSIZE) {
            ObservableList<MenuItem> Orders = shopMainMenuControllersDonut.getDonutCustomerOrder().getOrder();
            created.addAll(Orders);
        }
        donutListView.setItems(created);
    }

    /**
     * Initializes the quantity drop down menu, sets the Flavor buttons to a default state,
     * and sets the default quantity to 1
     */
    @FXML
    public void initialize() {
        quantityOfOrder.getItems().addAll(ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, ELEVEN, TWELVE);
        quantityOfOrder.setValue(ONE);
        flavorChoiceOne.setText("Please");
        flavorChoiceTwo.setText("Select");
        flavorChoiceThree.setText("A");
        flavorChoiceFour.setText("Donut");
    }

    /**
     * Constructs a Donut Object based on which buttons and quantity
     * have been selected in the GUI
     *
     * @return Donut Object
     */
    private Donut createDonut() {
        String selectedDonutTypeButtonToString = donutType.getSelectedToggle().toString();
        String selectedDonutFlavorButtonToString = donutFlavor.getSelectedToggle().toString();
        String selectedDonutType = selectedDonutTypeButtonToString.substring(selectedDonutTypeButtonToString.indexOf("'") + INDEX_OFFSET, selectedDonutTypeButtonToString.lastIndexOf("'"));
        String selectedDonutFlavor = selectedDonutFlavorButtonToString.substring(selectedDonutFlavorButtonToString.indexOf("'") + INDEX_OFFSET, selectedDonutFlavorButtonToString.lastIndexOf("'"));
        Donut newDonutOrder = new Donut(selectedDonutType, selectedDonutFlavor);
        newDonutOrder.setQuantity(quantityOfOrder.getSelectionModel().getSelectedIndex() + INDEX_OFFSET);
        donutType.getSelectedToggle().setSelected(false);
        donutFlavor.getSelectedToggle().setSelected(false);
        return newDonutOrder;
    }

    /**
     * When the Add button is pressed, a Donut Object is made
     * and added to the ObservableList of Donut Objects from ShopMainMenuController
     * and the current total is updated
     */
    @FXML
    void addToOrder() {
        if (donutType.getSelectedToggle() != null && donutFlavor.getSelectedToggle() != null) {
            boolean duplicateDonutFound = false;
            Donut newDonutOrder = createDonut();
            for (MenuItem num : shopMainMenuControllersDonut.getDonutCustomerOrder().getOrder()) {
                if (num.compare(newDonutOrder)) {
                    duplicateDonutFound = true;
                    num.setQuantity(num.getQuantity() + newDonutOrder.getQuantity());
                    updateOrders();
                }
            }
            if (!duplicateDonutFound) {
                shopMainMenuControllersDonut.getDonutCustomerOrder().add(newDonutOrder);
                updateOrders();
            }
            totalOrderCost.setText(String.format("%.2f", shopMainMenuControllersDonut.getDonutCustomerOrder().orderPrice()));
            quantityOfOrder.setValue(ONE);
        } else {
            createAlert();
        }
    }

    /**
     * Creates and displays an errorAlert Message box when
     * A Flavor has been selected but a Donut Type has not,
     * or when nothing is selected at all
     */
    private void createAlert() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Donut Order is not valid");
        errorAlert.setContentText("Please make sure you've selected the Type & Flavor for the Donut(s)");
        errorAlert.showAndWait();
    }

    /**
     * Removes a selected Donut Object from the ObservableList
     * and updates the ObservableList along with the total
     */
    @FXML
    void removeFromOrder() {
        if (donutListView.getSelectionModel().getSelectedItem() != null) {
            shopMainMenuControllersDonut.getDonutCustomerOrder().remove(donutListView.getSelectionModel().getSelectedItem());
            updateOrders();
            totalOrderCost.setText(String.format("%.2f", shopMainMenuControllersDonut.getDonutCustomerOrder().orderPrice()));
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Donut removal Error");
            errorAlert.setContentText("Please make sure you've selected an a Donut to remove");
            errorAlert.showAndWait();
        }
    }

    /**
     * When the Donut Holes button is selected,
     * the Flavor button choices change
     */
    @FXML
    void DonutHolesSelected() {
        flavorChoiceOne.setText(DOUBLE_CHOCOLATE);
        flavorChoiceTwo.setText(JELLY_FILLED);
        flavorChoiceThree.setText(OREO);
        flavorChoiceFour.setText(FRENCH);
    }

    /**
     * When the Cake Donut button is selected,
     * the Flavor button choices change
     */
    @FXML
    void cakeDonutSelected() {
        flavorChoiceOne.setText(RED_VELVET);
        flavorChoiceTwo.setText(POWDERED_SUGAR);
        flavorChoiceThree.setText(BAKED_JELLY);
        flavorChoiceFour.setText(BOSTON_CREAM);
    }

    /**
     * When the Yeast Donut button is selected,
     * the Flavor button choices change
     */
    @FXML
    void yeastDonutSelected() {
        flavorChoiceOne.setText(CHOCOLATE);
        flavorChoiceTwo.setText(STRAWBERRY);
        flavorChoiceThree.setText(CINNAMON);
        flavorChoiceFour.setText(COCONUT);
    }
}
