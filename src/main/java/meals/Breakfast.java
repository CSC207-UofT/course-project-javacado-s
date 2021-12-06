package meals;

import java.io.Serializable;

public class Breakfast extends Meal implements Serializable {

    private static final long serialVersionUID = 7557750465848715141L;

    /** Construct the Breakfast.
     * @param selectedMeal given meal type requested
     *  send the selectedMeal to the Meal class to set the Breakfast.
     */

    public Breakfast(String selectedMeal) {
        super(selectedMeal);
    }

    public float getMealPrice(){
        return super.getMealPrice();
    }

    public float getNumEmployee(String employee){
        return super.getNumEmployee(employee);
    }

    public String getMealName(){
        return super.getMealName();
    }

    @Override
    public String toString(){
        return super.toString();
    }
}
