package com.rutgers.rucafe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that represents a StoreOrders Object
 *
 * Methods within this class can create a StoreOrders Object,
 * return the ArrayLists of Order Objects within a StoreOrders Object,
 * add and remove an Order Object to/from the ArrayList,
 * and export all Order Objects to a text file
 *
 * @author Mark Holleran, Abhitej Bokka
 */
public class StoreOrders implements Customizable {

    private final ArrayList<Order> orders;

    /**
     * Default constructor for creating a StoreOrders Object
     */
    public StoreOrders() {
        orders = new ArrayList<Order>();
    }

    /**
     * Adds an Order Object to the ArrayLists of a StoreOrders Object
     *
     * @param obj Order Object to be added
     * @return True if Order Object was added from the ArrayList of a StoreOrders Object, false otherwise
     */
    @Override
    public boolean add(Object obj) {

        if (obj instanceof Order) {
            orders.add((Order)obj);
            return true;
        }
        return false;
    }

    /**
     * Removes an Order Object from the ArrayList of a StoreOrders Object
     *
     * @param obj Order Object to be removed
     * @return True if Order Object was removed from the ArrayList of a StoreOrders Object, false otherwise
     */
    public boolean remove(Object obj) {

        if (obj instanceof Order) {
            orders.remove((Order)obj);
            return true;
        }
        return false;
    }

    /**
     * Returns the ArrayList parameter of a StoreOrders Object
     *
     * @return ArrayList of Order Objects
     */
    public ArrayList<Order> getOrderList() {
        return this.orders;
    }

    /**
     * Exports all Order Objects within the StoreOrder's ArrayList to a text file
     *
     * @param file File to be written to
     * @return True if file successfully written to, false otherwise
     */
    public boolean export(File file) {

        try {
            FileWriter output = new FileWriter(file);
            for (Order order : orders) {
                output.write("Order: " + order.toString() + " Total: $"
                        + String.format("%.2f", order.orderPriceTax()));
                output.write("\n");
            }
            output.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Exports all Order Objects within the StoreOrder's ArrayList to a text file without Price
     *
     * @param file File to be written to
     * @return True if file successfully written to, false otherwise
     */
    public boolean exportNoPrice(File file) {
        try {
            FileWriter output = new FileWriter(file);
            for (Order order : orders) {
                output.write("Order: " + order.toString());
                output.write("\n");
            }
            output.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
