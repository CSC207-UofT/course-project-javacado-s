package meals;

import java.io.Serializable;

public class Dinner extends Meal implements Serializable {

    private static final long serialVersionUID = 8885238632835006644L;

    /** Construct the Dinner.
     * @param selectedMeal given meal type requested
     *  send the selectedMeal to the Meal class to set the Dinner.
     */
    public Dinner(String selectedMeal) {
        super(selectedMeal);
    }

    public float getMealPrice(){
        return super.getMealPrice();
    }

    public float getNumEmployee(){
        return super.getNumEmployee();
    }
    
    public String getMealName(){
        return super.getMealName();
    }

    @Override
    public String toString(){
        return super.toString();
    }

}
