package meals;

public class Dinner extends Meal{

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

    @Override
    public String toString(){
        return super.toString();
    }

}
