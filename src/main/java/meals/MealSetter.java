package meals;

import exceptions.MealNotFoundException;

import java.io.Serializable;

/**
 This class helps to determine which Meal Type the user wants to book.
 */

public class MealSetter implements Serializable {

    private static final long serialVersionUID = -3590348619976786670L;
    private Meal newMeal;

    /**
     * @return the Meal constructed by the Class
     */
    public Meal getMeal(String selectedMeal) throws MealNotFoundException {
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
            default: {
                throw new MealNotFoundException("Given meal type does not exist");
            }
        }
        return this.newMeal;
    }
}
