import meals.Meal;
import org.junit.*;
import meals.Breakfast;
import meals.Lunch;
import meals.Dinner;

import static org.junit.Assert.*;

public class MealTest {
    Meal e_meal = new Breakfast("breakfast");
    Meal meal2 = new Lunch("lunch");
    Meal meal3 = new Dinner("dinner");

    @Test(timeout = 50)
    public void TestGetMealPrice(){
        assertEquals(7.0, e_meal.getMealPrice(),3);
        assertEquals(14.0, meal2.getMealPrice(), 3);
    }

    @Test(timeout = 50)
    public void TestGetNumEmployee(){
        assertEquals(0.2, e_meal.getNumEmployee(),3);
        assertEquals(0.4, meal2.getNumEmployee(), 3);
    }

    @Test(timeout = 50)
    public void TestGetName(){
        assertEquals(e_meal.getMealName(), "breakfast");
        assertEquals(meal2.getMealName(), "lunch");
        assertEquals(meal3.getMealName(), "dinner");
    }
    @Test(timeout = 100)
    public void TestToString() {
        assertEquals("Menu of breakfast:\r\nWhite Bread\r\nScrambled Egg\r\nOrange Juice\r\nMuffin",
                e_meal.toString());
        assertEquals("Menu of lunch:\r\nFried Rice\r\nBeef Soup\r\nGreen Salad\r\nApple Juice\r\nFries",
                meal2.toString());
    }

    @Test(timeout = 50)
    public void TestEquals(){
        Meal meal3 = new Breakfast("breakfast");
        assertNotEquals(e_meal, meal2);
        assertEquals(e_meal, meal3);
    }
}
