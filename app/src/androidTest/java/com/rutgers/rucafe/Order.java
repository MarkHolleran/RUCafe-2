package com.rutgers.rucafe;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class that represents an Order Object
 *
 * Methods within this class can construct an Order Object,
 * add and remove an Object from the Order Object's ObservableList,
 * calculate the price of an Order with or without tax,
 * get the Order Object's order number, get the Order Object's quantity,
 * get the Order Object's ObservableList, set the Order OBject's order number,
 * and represent an Order Object as a String
 *
 * @author Mark Holleran, Abhitej Bokka
 */
public class Order implements Customizable {

    public static final double TAX_MULTIPLIER = 1.06625;
    private int orderNumber;
    private ArrayList<MenuItem> order;

    /**
     * Default Order Object Constructor
     */
    public Order() { order = new  ArrayList<MenuItem>();}

    /**
     * Adds a MenuItem Object to the ObservableList of MenuItems of the Order Object
     *
     * @param obj MenuItem Object to be added
     * @return True if MenuItem Object is added, false otherwise
     */
    public boolean add(Object obj) {
        if (obj instanceof MenuItem item) {
            order.add(item);
            return true;
        }
        return false;
    }

    /**
     * Removes a MenuItem Object from the ObservableList of MenuItems of the Order Object
     *
     * @param obj MenuItem Object to be removed
     * @return True if MenuItem Object is removed, false otherwise
     */
    public boolean remove(Object obj) {
        if (obj instanceof MenuItem item) {
            order.remove(item);
            return true;
        }
        return false;
    }

    /**
     * Returns the combined Price of every MenuItem Object in the Order Object's ObservableList
     *
     * @return Double representing the total Price of an Order Object
     */
    public double orderPrice() {
        double sum = 0;
        for (MenuItem item : order) {
            sum += item.itemPrice() * item.getQuantity();
        }
        return sum;
    }

    /**
     * Returns the combined Price of every MenuItem Object in the Order Object's ObservableList with tax
     *
     * @return Double representing the total Price with tax of an Order Object
     */
    public double orderPriceTax() {
        return (this.orderPrice() * TAX_MULTIPLIER);
    }

    /**
     * Returns ObservableList parameter of the Order Object
     *
     * @return ObservableList containing MenuItems
     */
    public ArrayList<MenuItem> getOrder() {
        return order;
    }

    /**
     * Sets the Order Object's order number parameter
     *
     * @param uniqueOrderNumber Integer representing an order number
     */
    public void setOrderNumber(int uniqueOrderNumber) {
        this.orderNumber = uniqueOrderNumber;
    }

    /**
     * Returns a String representation of an Order Object
     *
     * @return String representation of the Order containing
     * the order number and all the Order's MenuItems within
     * it's ObservableList
     */
    public String toString() {
        return orderNumber + " " + order.toString();
    }
}
