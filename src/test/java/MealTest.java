import meals.Meal;
import org.junit.*;

import static org.junit.Assert.*;

public class MealTest {
    Meal e_meal = new Meal("breakfast");

    @Test(timeout = 50)
    public void TestgetMealPrice(){
        assertEquals(7.0, e_meal.getMealPrice(),3);
    }

    @Test(timeout = 50)
    public void TestgetNumEmployee(){
        assertEquals(0.2, e_meal.getNumEmployee(),3);
    }

    @Test
    public void Testequals(){
        Meal meal2 = new Meal("breakfast");
        assert(e_meal.equals(meal2));
    }

}
