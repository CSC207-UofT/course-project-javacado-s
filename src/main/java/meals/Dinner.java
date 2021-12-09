package meals;

import java.io.Serializable;
import java.util.ArrayList;

/**
 This class represents Dinner and store the details.
 */

public class Dinner extends Meal implements Serializable {

    private static final long serialVersionUID = 8885238632835006644L;
    private float numEmployeeTypes = (float) 0.6;
    private float price = (float) 22.0;
    private ArrayList<String> menuList = new ArrayList<>(); //for the future expansions
    private String[] Menu = {"Grilled Steak","Grilled Salmon","Large Salad",
            "Shrimp And Corn Chowder Soup","Apple Juice"};

    /** Construct the Dinner.
     * @param selectedMeal given meal type requested
     *  send the selectedMeal to the Meal class to set the Dinner.
     */
    public Dinner(String selectedMeal) {
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
        return this.price;
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
