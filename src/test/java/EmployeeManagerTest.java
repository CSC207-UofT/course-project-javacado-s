import org.junit.*;
import static org.junit.Assert.*;
import managers.EmployeeManager;
import employees.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class EmployeeManagerTest {

    EmployeeManager e_manager = new EmployeeManager();

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        try{
            FileInputStream employees = new FileInputStream("src/main/java/data_files/original_employees.txt");
            Path file_path = Paths.get("src/main/java/data_files/employees.txt");
            Files.copy(employees, file_path, StandardCopyOption.REPLACE_EXISTING);
            employees.close();
        }
        catch(IOException io){
            io.printStackTrace();
        }
    }

    @Test
    public void testAvailableEmployee() {
        GregorianCalendar date = new GregorianCalendar(2020, Calendar.JANUARY, 1, 12, 0, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");

        assert (e_manager.availableEmployees(date).equals(new ArrayList<Employee>()));
//        for (Employee e: e_manager.availableEmployees(date)){
//            System.out.println(e);
//            StringBuilder dates = new StringBuilder();
//            for(GregorianCalendar g: e.getUnavailableDates()){
//                Date time = g.getTime();
//                String temp = sdf.format(time);
//                dates.append(temp).append("\r\n");
//                System.out.println(dates);
//            }
//            System.out.println(dates);
//            assertFalse(e.isAvailable(date));
//        }
    }

    @Test
    public void testGetID() {
        Employee testEmployee = new Employee("Freddy James", 10);
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(testEmployee);
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(10);
        assert (e_manager.getID(employees).equals(ids));
    }

    @Test
    public void testEnoughEmployees() {
        assertTrue(e_manager.enoughEmployees(60,
                new GregorianCalendar(2020, Calendar.JANUARY, 2, 12, 0, 0)));
        assertFalse(e_manager.enoughEmployees(60,
                new GregorianCalendar(2020, Calendar.JANUARY, 1, 12, 0, 0)));
    }

    @Test
    public void testSetUnavailable() {
        Employee testEmployee = new Employee("Freddy James", 10);
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(testEmployee);
        e_manager.setUnavailable(employees, new GregorianCalendar(2021, Calendar.DECEMBER, 1));
        ArrayList<GregorianCalendar> unavailable = new ArrayList<>();
        unavailable.add(new GregorianCalendar(2021, Calendar.DECEMBER, 1));
        assert (testEmployee.getUnavailableDates().equals(unavailable));
    }

    /**
     * when running this method, it changes the txt file for employee 10. Remember to rollback change
     */
    @Test
    public void testSetAvailable() {
        Employee testEmployee = new Employee("Freddy James", 10);
        GregorianCalendar date = new GregorianCalendar(2020, Calendar.JANUARY, 1, 0, 0, 0);
        testEmployee.setUnavailability(date);
        ArrayList<Integer> employees = new ArrayList<>();
        employees.add(testEmployee.getid());
        e_manager.setAvailable(employees, date);
        for (Integer e: employees){
            assert(e_manager.getEmployee(e).isAvailable(date));
        }
    }

    @Test
    public void testIDtoEmployee() {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(10);
        ids.add(11);
        Employee testEmployee = new Employee("Freddy James", 10);
        Employee e2 = new Employee("Georgia Snow", 11);
        testEmployee.setUnavailability(new GregorianCalendar(2020, Calendar.JANUARY, 1));
        e2.setUnavailability(new GregorianCalendar(2020, Calendar.JANUARY, 1));
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(testEmployee);
        employees.add(e2);
        assert (e_manager.IDToEmployee(ids).equals(employees));
    }

    @Test
    public void testChooseEmployee() {
        Employee e1 = new Employee("Joslyn Riggs", 1);
        Employee e2 = new Employee("Jamarcus Silva", 2);
        e1.setUnavailability(new GregorianCalendar(2020, Calendar.JANUARY, 1));
        e2.setUnavailability(new GregorianCalendar(2020, Calendar.JANUARY, 1));
        ArrayList<Employee> chosen = e_manager.chooseEmployees(2,
                new GregorianCalendar(2021, Calendar.JANUARY, 1));
        assert (chosen.get(0).equals(e1));
//        assertEquals(chosen.get(1), e2);
    }
}
