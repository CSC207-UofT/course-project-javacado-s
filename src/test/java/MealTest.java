import meals.Meal;
import org.junit.*;
import meals.Breakfast;

import static org.junit.Assert.*;

public class MealTest {
    Meal e_meal = new Breakfast("breakfast");

    @Test(timeout = 50)
    public void TestGetMealPrice(){
        assertEquals(7.0, e_meal.getMealPrice(),3);
    }

    @Test(timeout = 50)
    public void TestGetNumEmployee(){
        assertEquals(0.2, e_meal.getNumEmployee(),3);
    }

    @Test
    public void TestEquals(){
        Meal meal2 = new Breakfast("breakfast");
        assert(e_meal.equals(meal2));
    }

}
