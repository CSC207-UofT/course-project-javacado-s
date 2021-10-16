/*
 * This file contains JUnit test cases for Silly.java
 *
 * You should NOT modify this file! When you push Silly.java to
 * GitHub, it should run these tests. You can see the results in the
 * "Actions" tab of your repository.
 *//*


import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;
import java.util.ArrayList;

public class EmployeeManagerTest {

    EmployeeManager e_manager = new EmployeeManager();

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test(timeout = 50)
    public void testInitializer() {
        ArrayList<Employee> expected = new ArrayList<Employee>();
        expected.add(new Employee("Jane Doe", 12));
        expected.add(new Employee("John Doe", 13));
        assertEquals(expected, e_manager.getEmployeeList());
    }

    @Test(timeout = 50)
    public void testAddEmployee() {
        ArrayList<Employee> expected = new ArrayList<>();
        e_manager.addEmployee("Joshua Doe", 14);
        expected.add(new Employee("Jane Doe", 12));
        expected.add(new Employee("John Doe", 13));
        expected.add(new Employee("Joshua Doe", 14));
        assertEquals(expected, e_manager.getEmployeeList());
    }
}*/
