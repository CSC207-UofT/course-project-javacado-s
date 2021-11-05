/*


import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;
import java.util.ArrayList;

public class EmployeeManagerTest {

    Managers.EmployeeManager e_manager = new Managers.EmployeeManager();

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test(timeout = 50)
    public void testInitializer() {
        ArrayList<Employees.Employee> expected = new ArrayList<Employees.Employee>();
        expected.add(new Employees.Employee("Jane Doe", 12));
        expected.add(new Employees.Employee("John Doe", 13));
        assertEquals(expected, e_manager.getEmployeeList());
    }

    @Test(timeout = 50)
    public void testAddEmployee() {
        ArrayList<Employees.Employee> expected = new ArrayList<>();
        e_manager.addEmployee("Joshua Doe", 14);
        expected.add(new Employees.Employee("Jane Doe", 12));
        expected.add(new Employees.Employee("John Doe", 13));
        expected.add(new Employees.Employee("Joshua Doe", 14));
        assertEquals(expected, e_manager.getEmployeeList());
    }
}*/
