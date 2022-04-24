package com.rutgers.rucafe;
/**
 * Class that represents a Donut MenuItem Object
 *
 * Methods within this class can construct a Donut Object, calculate Donut price, get Donut type, get Donut flavor,
 * compare two Donut Objects, and return a String representation of a Donut Object
 *
 * @author Mark Holleran, Abhitej Bokka
 */
public class Donut extends MenuItem  {

    private String flavor;
    private String type;

    public static final String YEAST = "Yeast Donut";
    public static final String CAKE = "Cake Donut";
    public static final String HOLE = "Donut Hole";
    public static final String DOUBLE_CHOCOLATE = "Double Chocolate";
    public static final String JELLY_FILLED = "Jelly Filled";
    public static final String OREO = "Oreo";
    public static final String FRENCH = "French";
    public static final String RED_VELVET = "Red Velvet";
    public static final String POWDERED_SUGAR = "Powdered Sugar";
    public static final String BAKED_JELLY = "Baked Jelly";
    public static final String BOSTON_CREAM = "Boston Cream";
    public static final String CHOCOLATE = "Chocolate";
    public static final String STRAWBERRY = "Strawberry";
    public static final String CINNAMON = "Cinnamon";
    public static final String COCONUT = "Coconut";
    public final static int SINGLEADDINTHRESHOLD = 1;
    public static final double YEAST_PRICE = 1.59;
    public static final double CAKE_PRICE = 1.79;
    public static final double HOLE_PRICE = 0.39;

    /**
     * Constructs a Donut Object given Strings
     * that represent the Donut's Type and Flavor
     *
     * @param donutType String representing the Type of the Donut Object
     *
     * @param donutFlavor String representing the Flavor of the Donut Object
     */
    public Donut(String donutType, String donutFlavor){
        type = donutType;
        flavor = donutFlavor;
    }

    /**
     * Calculates the price of a Donut Object based on it's Type
     *
     * @return Double representing the Donut's price based on the Donut's Type
     */
    @Override
    public double itemPrice(){

        if (type.equals(YEAST)){
            this.setItemPrice(YEAST_PRICE);
        }else if (type.equals(CAKE)){
            this.setItemPrice(CAKE_PRICE);
        }else if (type.equals(HOLE)){
            this.setItemPrice(HOLE_PRICE);
        }
        return super.itemPrice();
    }

    /**
     * Returns the target Donut Object's Type
     *
     * @return String containing the target Donut Object's Type parameter
     */
    public String getDonutType (){
        return this.type;
    }

    /**
     * Returns the target Donut Object's Flavor
     *
     * @return String containing the target Donut Object's Flavor parameter
     */
    public String getFlavor (){
        return this.flavor;
    }

    /**
     * Compares two Donut Objects for equality
     *
     * @param menuItem Donut Object to be compared with the target Donut Object
     *
     * @return True if both Donut Objects are of the same Type and Flavor, false otherwise
     */
    public boolean compare(MenuItem menuItem){
        if(menuItem instanceof Donut){
            return this.getFlavor().equals(((Donut) menuItem).getFlavor())
                    && this.getDonutType().equals(((Donut) menuItem).getDonutType());
        }
        return false;
    }

    /**
     * Returns a String representation of a Donut Object
     *
     * @return String representation of a Donut Object containing its quantity, Type, and Flavor
     */
    @Override
    public String toString(){
        String str = this.getQuantity() + " " + this.getFlavor() + " " + this.getDonutType();
        if(this.getQuantity() > SINGLEADDINTHRESHOLD){
            str += "s";
        }
        return str;
    }
}
