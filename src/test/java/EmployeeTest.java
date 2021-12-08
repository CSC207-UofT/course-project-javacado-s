import employees.Employee;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import employees.Chef;
import employees.Server;
import employees.Cleaner;
import employees.Supervisor;


public class EmployeeTest {
    Employee e;
    Employee e2;
    Employee e3;
    Employee e4;

    @Before
    public void setUp() throws Exception {
        e = new Chef("Carly", 1234);
        e2 = new Server("Cindy", 200);
        e3 = new Cleaner("David", 248);
        e4 = new Supervisor("Rebecca", 24);
        e.setUnavailability(new GregorianCalendar(2021, Calendar.NOVEMBER, 20));
    }
    @Test(timeout = 50)
    public void TestGetName() {
        assertEquals("Carly", e.getName());
        assertEquals("Cindy", e2.getName());
        assertEquals("David", e3.getName());
        assertEquals("Rebecca", e4.getName());
    }

    @Test(timeout = 50)
    public void TestGetId() {
        assertEquals(1234, e.getid());
        assertEquals(200, e2.getid());
        assertEquals(248, e3.getid());
        assertEquals(24, e4.getid());
    }

    @Test(timeout = 50)
    public void TestGetWage(){
        assertEquals(10, e.getWage());
        assertEquals(10, e2.getWage());
        assertEquals(10, e3.getWage());
        assertEquals(10, e4.getWage());
    }

    @Test(timeout = 50)
    public void TestSetAvailability(){
        ArrayList<GregorianCalendar> Unavailable = new ArrayList<GregorianCalendar>();
        Unavailable.add(new GregorianCalendar(2021, Calendar.NOVEMBER, 20));
        assertEquals(e.getUnavailableDates(), Unavailable);
        assertEquals(e2.getUnavailableDates(), new ArrayList<>());
        assertEquals(e3.getUnavailableDates(), new ArrayList<>());
        assertEquals(e4.getUnavailableDates(), new ArrayList<>());
    }

    @Test(timeout = 50)
    public void TestisAvailable(){
        assertTrue(e.isAvailable(new GregorianCalendar(2021,
            Calendar.NOVEMBER, 8)));
        assertTrue(e2.isAvailable(new GregorianCalendar(2021, Calendar.NOVEMBER, 20)));
        assertTrue(e3.isAvailable(new GregorianCalendar(2021, Calendar.NOVEMBER, 20)));
        assertTrue(e3.isAvailable(new GregorianCalendar(2021, Calendar.NOVEMBER, 20)));
    }

    @Test(timeout = 50)
    public void TestEquals(){
        Employee e1 = new Employee("Carly", 1234);
        e1.setUnavailability(new GregorianCalendar(2021, Calendar.NOVEMBER, 20, 18, 30, 24));
        Employee e2 = new Employee("Carly", 1234);
        e2.setUnavailability(new GregorianCalendar(2021, Calendar.NOVEMBER, 20, 18, 30, 24));
        assert(e1.equals(e2));
    }

    @Test(timeout = 50)
    public void testRemoveUnavailability() throws Exception {
        Employee e1 = new Employee("Carly", 1234);
        e1.setUnavailability(new GregorianCalendar(2021, Calendar.NOVEMBER, 20, 18, 30, 24));
        e1.removeUnavailability(new GregorianCalendar(2021, Calendar.NOVEMBER, 20, 18, 30, 24));
        assert(e1.getUnavailableDates().equals(new ArrayList<>()));
        assert(e1.isAvailable(new GregorianCalendar(2021, Calendar.NOVEMBER, 20, 18, 30, 24)));
    }


}