import java.util.ArrayList;
import java.util.Hashtable;

/**
 This class represents a Meal that sent specific meal information to Event Class.
 */

//more UPDATED will include later.

public class Meal {
    private String[] mealType = {"breakfast","lunch","dinner"};
    private Hashtable<String, Integer> priceList = new Hashtable<String, Integer>();
    private Hashtable<String, String[]> menuList = new Hashtable<String, String[]>();
    private final Hashtable<String, Integer> NUM_EMPLOYEE = new Hashtable<String, Integer>();
    private String selectedMeal = "";

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
        this.priceList.put(mealType[0],7);
        this.priceList.put(mealType[1],14);
        this.priceList.put(mealType[2],22);
    }

    // a private method which sets the General Menu
    private void setMenu(){
        this.menuList.put(mealType[0],new String[]{"White Bread","Scrambled Egg", "Orange Juice", "Muffin"});
        this.menuList.put(mealType[1],new String[]{"Fried Rice", "Beef Soup", "Green Salad", "Apple Juice", "Fries"});
        this.menuList.put(mealType[2],new String[]{"Grilled Steak","Grilled Salmon","Large Salad","Shrimp And Corn Chowder Soup","Apple Juice"});
    }

    // a private method which sets the number Employee per numAttendeeGroup attendees.
    private void setEmployee(){
        this.NUM_EMPLOYEE.put(mealType[0],1);
        this.NUM_EMPLOYEE.put(mealType[1],2);
        this.NUM_EMPLOYEE.put(mealType[2],3);
    }

    /**
     * @return an integer represents the price of this meal per attendee.
     */
    public int getMealPrice(){
        return priceList.get(this.selectedMeal.toLowerCase());
    }

    /**
     * @return an integer represents the number Employee per numAttendeeGroup attendees.
     */
    public int getNumEmployee(){
        return NUM_EMPLOYEE.get(this.selectedMeal.toLowerCase());
    }

    @Override
    /**
     * @return a String represents the Menu (Dishes list) for requested Meal Type.
     */
    public String toString(){
        String message = "Menu of " + selectedMeal + ":";
        for(String dish :this.menuList.get(this.selectedMeal.toLowerCase())){
            message = message + "\r\n" + dish;
        }

        return message;
    }

}
