import employees.Employee;
import org.junit.*;
import static org.junit.Assert.*;


public class EmployeeTest {
    Employee e;

    @Before
    public void setUp() throws Exception {
        e = new Employee("Carly", 1234);
    }
    @Test(timeout = 50)
    public void TestgetName() {
        assertEquals("Carly", e.getName());
    }
    @Test(timeout = 50)
    public void Testgetid() {
        assertEquals(1234, e.getid());
    }
    @Test(timeout = 50)
    public void TestgetWage(){
        assertEquals(10, e.getWage());
    }

}