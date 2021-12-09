package meals;

import java.io.Serializable;
import java.util.ArrayList;

/**
 This class represents Lunch and store the details.
 */

public class Lunch extends Meal implements Serializable {

    private static final long serialVersionUID = 2915823301012894712L;
    private float numEmployeeTypes = (float) 0.4;
    private float price = (float) 14.0;
    private ArrayList<String> menuList = new ArrayList<>(); //for the future expansions
    private String[] Menu = {"Fried Rice", "Beef Soup", "Green Salad", "Apple Juice", "Fries"};

    /** Construct the Lunch.
     * @param selectedMeal given meal type requested
     *  send the selectedMeal to the Meal class to set the Lunch.
     */
    public Lunch(String selectedMeal) {
        super(selectedMeal);
    }

    //set price (future expansion)
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the price of this Meal type
     */
    @Override
    public float getMealPrice(){
        return price;
    }

    public float getNumEmployee(String employee){
        return super.getNumEmployee(employee);
    }

    public String getMealName(){
        return super.getMealName();
    }

    /**
     * @return the full Menu of this Meal type
     */
    @Override
    public String toString(){
        StringBuilder message = new StringBuilder();
        for(String dish : this.Menu){
            message.append("\r\n").append(dish);
        }
        return super.toString() + message;
    }

}
