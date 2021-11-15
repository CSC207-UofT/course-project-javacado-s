import org.junit.Test;
import meals.Dish;
import static org.junit.Assert.*;

public class DishTest {
    Dish d = new Dish("Chicken wings", "chicken", 12);
    @Test
    public void TestGetMealPrice(){
        assertEquals(12, d.getMealPrice(), 2);
    }
    @Test
    public void TestGetName(){
        assertEquals("Chicken wings", d.getMealName());
    }
    @Test
    public void TestGetIngredient(){
        assertEquals("chicken", d.getIgName());
    }
}
