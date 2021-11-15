package meals;

import java.io.Serializable;

public class Lunch extends Meal implements Serializable {

    private static final long serialVersionUID = 2915823301012894712L;

    /** Construct the Lunch.
     * @param selectedMeal given meal type requested
     *  send the selectedMeal to the Meal class to set the Lunch.
     */
    public Lunch(String selectedMeal) {
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
