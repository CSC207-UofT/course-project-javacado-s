

/**
 This class represents a Meal that sent specific meal information to Event Class.
 */

//Let me know if anything is missing.

public class Meal {
    private String[] mealType = {"breakfast","lunch","dinner"};
    private final int BASE_PRICE = 3;
    private Hashtable<String, Integer> priceList = new Hashtable<String, Integer>();
    private Hashtable<String, String[]> menuList = new Hashtable<String, String[]>();
    private final Hashtable<String, Integer> BASE_NUM_EMPLOYEE = new Hashtable<String, Integer>();
    private Hashtable<String, Integer> addNumEmployee = new Hashtable<String, Integer>();
    private int numAttendees;
    private int totalMealPrice = 0;
    private int numEmployee = 0;
    private String selectedMeal = "";

    /**
     Constructs a Meal with a number of attendees, and meal type requested.
     @param numAttendees given number of attendees at the event
     @param selectedMeal given meal type requested
     And Setup Meal Price List & Menu.
     */

    public Meal (int numAttendees, String selectedMeal){
        this.numAttendees = numAttendees;
        this.selectedMeal = selectedMeal;
        setMealPriceList();
        setMenu();
        setEmployee();
    }

    // a private method which sets the Meal Price List
    private void setMealPriceList(){
        this.priceList.put(mealType[0],2);
        this.priceList.put(mealType[1],4);
        this.priceList.put(mealType[2],7);
    }

    // a private method which sets the General Menu
    private void setMenu(){
        this.menuList.put(mealType[0],new String[]{"White Bread","Scrambled Egg", "Orange Juice", "Muffin"});
        this.menuList.put(mealType[1],new String[]{"Fried Rice", "Beef Soup", "Green Salad", "Apple Juice", "Fries"});
        this.menuList.put(mealType[2],new String[]{"Grilled Steak","Grilled Salmon","Large Salad","Shrimp And Corn " + "Chowder Soup","Apple Juice"});
    }

    // a private method which sets the basic number Employee and additional number Employee needed.
    private void setEmployee(){
        this.BASE_NUM_EMPLOYEE.put(mealType[0],1);
        this.BASE_NUM_EMPLOYEE.put(mealType[1],2);
        this.BASE_NUM_EMPLOYEE.put(mealType[2],3);

        // additional number Employee applied for numAttendees >= 12
        this.addNumEmployee.put(mealType[0],1);
        this.addNumEmployee.put(mealType[1],2);
        this.addNumEmployee.put(mealType[2],2);
    }

    /**
     * @return an integer represents the total price of this meal
     * (the total meal price is based on basic price + specific price for different types of meal.)
     */
    public int getTotalPrice(){
        totalMealPrice = numAttendees * (BASE_PRICE + priceList.get(this.selectedMeal.toLowerCase()));
        return totalMealPrice;
    }

    /**
     * @return an integer represents the total number Employee needed for this meal
     * (the total number Employee is based on basic number Employee per Meal Type
     * + specific additional number Employee for different types of meal, this
     * only available for number Attendees >= 12)
     */
    public int getNumEmployee(){
        if (this.numAttendees >= 12){
            this.numEmployee = this.numEmployee + addNumEmployee.get(this.selectedMeal.toLowerCase());
        }
        this.numEmployee = this.numEmployee + BASE_NUM_EMPLOYEE.get(this.selectedMeal.toLowerCase());
        return this.numEmployee;
    }

    /**
     * @return a String represents the Menu (Dishes list) for requested Meal Type.
     */
    public String getMenu(){
        String message = "Menu of " + selectedMeal + ":";
        for(String dish :this.menuList.get(this.selectedMeal.toLowerCase())){
            message = message + "\r\n" + dish;
        }

        return message;
    }

    @Override
    /**
     * @return a String indicates the Menu (Dishes list) for requested Meal Type and Total Meal Price.
     */
    public String toString(){
        String message = "Menu:";
        for(String dish :this.menuList.get(this.selectedMeal.toLowerCase())){
            message = message + "\r\n" + dish;
        }

        return message + "\nTotal Meal Price: " + this.totalMealPrice;
    }

}

