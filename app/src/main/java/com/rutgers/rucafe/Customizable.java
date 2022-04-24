package com.rutgers.rucafe;
/**
 * Class representing an Interface containing Add and Remove methods
 * This Interface is implemented by the Coffee and Donut Objects
 *
 * @author Mark Holleran, Abhitej Bokka
 */
public interface Customizable {

    /**
     * Serves multiple purposes of adding objects to Array lists
     * Used to add an Object like a MenuItem to an order or Addins on a Coffee
     *
     * @param obj Object containing the required object to be added to a list
     *
     * @return true if added, false otherwise
     */
    boolean add(Object obj);

    /**
     * Serves multiple purposes of adding objects to Array lists
     * Used to remove an Object like a MenuItem from an order or Addins on a Coffee
     *
     * @param obj Object containing the required object to be removed from a list
     *
     * @return true if removed, false otherwise
     */
    boolean remove(Object obj);
}



