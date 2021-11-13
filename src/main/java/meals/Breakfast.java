package meals;

public class Breakfast extends Meal{

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
