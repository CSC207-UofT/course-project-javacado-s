package meals;

import java.util.Hashtable;
import java.util.ArrayList;
/**
 This class represents a Meal that sent specific meal information to Event Class.
 */


public abstract class Meal implements Ingredient{
    private String[] mealType = {"breakfast","lunch","dinner"};
    private Hashtable<String, Float> priceList = new Hashtable<>();
    private Hashtable<String, String[]> menuList = new Hashtable<>();
    private Hashtable<String, Ingredient[]> IngredientList = new Hashtable<>();
    private final Hashtable<String, Float> NUM_EMPLOYEE = new Hashtable<>();
    private String selectedMeal;

    /**
     Constructs a Meal with a number of attendees, and meal type requested.
     @param selectedMeal given meal type requested
     And Setup Meal Price List, Menu & NUM_EMPLOYEE List.
     */

    public Meal (String selectedMeal){
        this.selectedMeal = selectedMeal;
        setMealPriceList();
        setMenu();
        setEmployee();
    }

    // a private method which sets the Meal Price List per attendee.
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

    // a private method which sets the number Employee per numAttendeeGroup attendees.
    private void setEmployee(){
        this.NUM_EMPLOYEE.put(mealType[0], (float) 0.2);
        this.NUM_EMPLOYEE.put(mealType[1], (float) 0.4);
        this.NUM_EMPLOYEE.put(mealType[2], (float) 0.6);
    }

    //a private method leave for phase 2 update.
    private void addMenu(Ingredient Ig){
        Ingredient[] Ig_breakfast = new Ingredient[100];
        Ingredient[] Ig_lunch = new Ingredient[100];
        Ingredient[] Ig_Dinner = new Ingredient[100];
        this.IngredientList.put(mealType[0], Ig_breakfast);
        this.IngredientList.put(mealType[1], Ig_breakfast);
        this.IngredientList.put(mealType[2], Ig_breakfast);
    }

    /**
     * @return an integer represents the price of this meal per attendee.
     */
    public float getMealPrice(){
        return priceList.get(this.selectedMeal.toLowerCase());
    }

    /**
     * @return an integer represents the number Employee per numAttendeeGroup attendees.
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

