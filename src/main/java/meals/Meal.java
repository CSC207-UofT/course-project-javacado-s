package meals;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Objects;

/**
 This class represents an Abstract Meal that sent specific meal information to Event Class.
 */

public abstract class Meal implements Ingredient, Serializable {

    private static final long serialVersionUID = -1114328364648793004L;
    private String[] mealType = {"breakfast","lunch","dinner"};
    private final Hashtable<String, Hashtable<String, Float>> NUM_EMPLOYEE = new Hashtable<>();
    private String selectedMeal;

    /**
     Constructs a Meal with meal type requested.
     @param selectedMeal given meal type requested
     */
    public Meal (String selectedMeal){
        this.selectedMeal = selectedMeal;
        createEmployee();
    }


    // a private method which initialize the number Employee types per numAttendeeGroup attendees.
    private void createEmployee(){
        double[] numEmployeeTypes = {0.2, 0.4, 0.6};
        for(int j = 0; j < mealType.length; j ++){
            String[] employeeType = {"Chef", "Cleaner", "Server", "Supervisor"};
            Hashtable<String, Float> employeeTypes = new Hashtable<>();
            for(String employee: employeeType){
                employeeTypes.put(employee, (float) numEmployeeTypes[j]);
            }
            this.NUM_EMPLOYEE.put(mealType[j], employeeTypes);
        }
    }

    // update a certain number Employee types per numAttendeeGroup attendees (for future expansion)
    public void setNumEmployee(String e, float n){
        this.NUM_EMPLOYEE.get(this.selectedMeal).put(e,n);
    }

    /**
     * @return a float represents the price of this meal per attendee (default).
     * (Subclasses of related Meal Type will provide the price.)
     */
    public float getMealPrice(){
        return (float) 0.0;
    }

    /**
     * @return a float represents the number Employee per numAttendeeGroup attendees.
     */
    public float getNumEmployee(String employee){
        return NUM_EMPLOYEE.get(this.selectedMeal.toLowerCase()).get(employee);
    }

    /**
     * @return the Meal type name of the Meal which user requested.
     */
    public String getMealName(){return this.selectedMeal;}

    /**
     * Prints the menu of the selected meal type.
     * @return a String only contain the heading of the menu.
     * (Subclasses of related Meal Type will provide the remaining of the menu content.)
     */
    @Override
    public String toString(){
        StringBuilder message = new StringBuilder("Menu of " + selectedMeal + ":");
        return message.toString();
    }

    /**
     * Return true if two given meal objects are equal, considered equal if they have the same mealtype.
     * @param obj the meal object to be compared.
     * @return True if the two meal objects are equal.
     */
    @Override
    public boolean equals(Object obj){
        if (this.getClass() != obj.getClass()){
            return false;
            }
        Meal meal2 = (Meal) obj;
        return(Objects.equals(this.selectedMeal, meal2.selectedMeal));
    }
}
