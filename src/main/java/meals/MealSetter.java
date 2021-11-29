package meals;

/**
 This class helps to determine which Meal Type the user wants to book.
 */

public class MealSetter {

    private String selectedMeal;
    private Meal newMeal = null;


    /**
     * @return the Meal constructed by the Class
     */
    public Meal getMeal(String selectedMeal){

        switch (selectedMeal.toLowerCase()) {
            case "breakfast" : {
                newMeal = new Breakfast(selectedMeal);
                break;
            }
            case "lunch" : {
                newMeal = new Lunch(selectedMeal);
                break;
            }
            case "dinner" : {
                newMeal = new Dinner(selectedMeal);
                break;
            }
        }

        return this.newMeal;
    }

}
