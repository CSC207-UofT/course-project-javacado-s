import org.junit.*;

import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.Assert.*;
import java.util.Date;
import java.util.ArrayList;

public class EmployeeTest {
    Employee e;

    @Before
    public void setUp() throws Exception {
        e = new Employee("Carly", 1234);
        e.setUnavailability(new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24));
    }
    @Test(timeout = 50)
    public void TestGetName() {
        assertEquals("Carly", e.getName());
    }

    @Test(timeout = 50)
    public void TestGetId() {
        assertEquals(1234, e.getid());
    }

    @Test(timeout = 50)
    public void TestGetWage(){
        assertEquals(10, e.getWage());
    }

    @Test(timeout = 50)
    public void TestSetAvailability(){
        ArrayList<Date> Unavailable = new ArrayList<Date>();
        Unavailable.add(new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24));
        assertEquals(e.getUnavailableDates(), Unavailable);}

    @Test(timeout = 50)
    public void TestisAvailable(){assertTrue(e.isAvailable(new Date(2021,
                Calendar.NOVEMBER, 8, 18, 30, 24)));}

}