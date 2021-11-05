package meals;

import java.util.Hashtable;
/**
 This class represents a Meals.Meal that sent specific meal information to Events.Event Class.
 */

//more UPDATED will include later.

public class Meal {
    private String[] mealType = {"breakfast","lunch","dinner"};
    private Hashtable<String, Float> priceList = new Hashtable<>();
    private Hashtable<String, String[]> menuList = new Hashtable<>();
    private final Hashtable<String, Float> NUM_EMPLOYEE = new Hashtable<>();
    private String selectedMeal;

    /**
     Constructs a Meals.Meal with a number of attendees, and meal type requested.
     @param selectedMeal given meal type requested
     And Setup Meals.Meal Price List, Menu & NUM_EMPLOYEE List.
     */

    public Meal (String selectedMeal){
        this.selectedMeal = selectedMeal;
        setMealPriceList();
        setMenu();
        setEmployee();
    }

    // a private method which sets the Meals.Meal Price List per attendee.
    private void setMealPriceList(){
        this.priceList.put(mealType[0],(float) 7.0);
        this.priceList.put(mealType[1],(float) 14.0);
        this.priceList.put(mealType[2],(float) 22.0);
    }

    // a private method which sets the General Menu
    private void setMenu(){
        this.menuList.put(mealType[0],new String[]{"White Bread","Scrambled Egg", "Orange Juice", "Muffin"});
        this.menuList.put(mealType[1],new String[]{"Fried Rice", "Beef Soup", "Green Salad", "Apple Juice", "Fries"});
        this.menuList.put(mealType[2],new String[]{"Grilled Steak","Grilled Salmon","Large Salad","Shrimp And Corn Chowder Soup","Apple Juice"});
    }

    // a private method which sets the number Employees.Employee per numAttendeeGroup attendees.
    private void setEmployee(){
        this.NUM_EMPLOYEE.put(mealType[0], (float) 0.2);
        this.NUM_EMPLOYEE.put(mealType[1], (float) 0.4);
        this.NUM_EMPLOYEE.put(mealType[2], (float) 0.6);
    }

    /**
     * @return an integer represents the price of this meal per attendee.
     */
    public float getMealPrice(){
        return priceList.get(this.selectedMeal.toLowerCase());
    }

    /**
     * @return an integer represents the number Employees.Employee per numAttendeeGroup attendees.
     */
    public float getNumEmployee(){
        return NUM_EMPLOYEE.get(this.selectedMeal.toLowerCase());
    }

    @Override
    public String toString(){
        String message = "Menu of " + selectedMeal + ":";
        for(String dish :this.menuList.get(this.selectedMeal.toLowerCase())){
            message = message + "\r\n" + dish;
        }

        return message;
    }

}

