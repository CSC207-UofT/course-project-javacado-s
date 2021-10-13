import java.util.ArrayList;
import java.util.Hashtable;

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

    public Meal (int numAttendees, String selectedMeal){
        this.numAttendees = numAttendees;
        this.selectedMeal = selectedMeal;
        setMealPriceList();
        setMenu();
    }

    private void setMealPriceList(){
        this.priceList.put(mealType[0],2);
        this.priceList.put(mealType[1],4);
        this.priceList.put(mealType[2],7);
    }
    private void setMenu(){
        this.menuList.put(mealType[0],new String[]{"White Bread","Scrambled Egg", "Orange Juice", "Muffin"});
        this.menuList.put(mealType[1],new String[]{"Fried Rice", "Beef Soup", "Green Salad", "Apple Juice", "Fries"});
        this.menuList.put(mealType[2],new String[]{"Grilled Steak","Grilled Salmon","Large Salad","Shrimp And Corn Chowder Soup","Apple Juice"});
    }

    private void setEmployee(){
        this.BASE_NUM_EMPLOYEE.put(mealType[0],1);
        this.BASE_NUM_EMPLOYEE.put(mealType[1],2);
        this.BASE_NUM_EMPLOYEE.put(mealType[2],3);

        this.addNumEmployee.put(mealType[0],1);
        this.addNumEmployee.put(mealType[1],2);
        this.addNumEmployee.put(mealType[2],2);
    }

    public int getTotalPrice(){
        totalMealPrice = numAttendees * (BASE_PRICE + priceList.get(this.selectedMeal.toLowerCase()));
        return totalMealPrice;
    }

    public int getNumEmployee(){
        if (this.numAttendees >= 12){
            this.numEmployee = this.numEmployee + addNumEmployee.get(this.selectedMeal.toLowerCase());
        }
        this.numEmployee = this.numEmployee + BASE_NUM_EMPLOYEE.get(this.selectedMeal.toLowerCase());
        return this.numEmployee;
    }

    public String getMenu(){
        String message = "Menu of " + selectedMeal + ":";
        for(String dish :this.menuList.get(this.selectedMeal.toLowerCase())){
            message = message + "\r\n" + dish;
        }
        return message;
    }

    @Override
    public String toString(){
        String message = "Menu:";
        for(String dish :this.menuList.get(this.selectedMeal.toLowerCase())){
            message = message + "\r\n" + dish;
        }

        return message + "\nTotal Meal Price: " + this.totalMealPrice;
    }

}
