package meals;

public class Lunch extends Meal{

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

    @Override
    public String toString(){
        return super.toString();
    }

}
