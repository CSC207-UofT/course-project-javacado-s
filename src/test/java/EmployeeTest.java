import employees.Employee;
import org.junit.*;
import static org.junit.Assert.*;
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

//  Todo: pass the TestEquals() method

//    @Test(timeout = 50)
//    public void TestEquals(){
//        Employee e1 = new Employee("Carly", 1234);
//        e1.setUnavailability(new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24));
//        Employee e2 = new Employee("Carly", 1234);
//        e2.setUnavailability(new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24));
//        assertEquals(e1, e2);
//    }


}