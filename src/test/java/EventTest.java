import events.Event;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import meals.Dinner;
import meals.Lunch;
import java.util.GregorianCalendar;

public class EventTest {

    Event event;
    @Before
    public void setUp() {
        GregorianCalendar initial = new GregorianCalendar(2021, Calendar.NOVEMBER, 29);
        event = new Event(10, "Birthday", initial, "CN Tower",
                30, new Dinner("dinner"));
    }

    @Test
    public void getID() {
        assertEquals(10, event.getID());
    }

    @Test
    public void getName() {
        assertEquals("Birthday", event.getName());
    }

    @Test
    public void getDate() {
        assertEquals(new GregorianCalendar(2021, Calendar.NOVEMBER, 29), event.getDate());
    }

    @Test
    public void getLocation() {
        assertEquals("CN Tower", event.getLocation());
    }

    @Test
    public void getNumAttendees() {
        assertEquals(30, event.getNumAttendees());
    }

    @Test
    public void getMealType() {assert(new Dinner("dinner").equals(event.getMealType()));
    }

    @Test
    public void getEmployees() {
        assertEquals(new ArrayList<String>(), event.getEmployees());
    }

    @Test
    public void setEmployees() {
        ArrayList<String> employees = new ArrayList<>();
        employees.add("A");
        employees.add("B");
        employees.add("C");
        event.setEmployees(employees);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("A");
        expected.add("B");
        expected.add("C");
        assertEquals(expected, event.getEmployees());
    }

    @Test
    public void testGetPrice(){
        assertEquals(660.0, event.getPrice(), 0);
    }

    @Test
    public void getEmployeesNeeded() {
        assertEquals(18, event.getEmployeesNeeded());
    }

    @Test
    public void testGetStatus(){
        assertEquals("Created", event.getStatus());
        event.setStatus("Under Preparation");
        assertEquals("Under Preparation", event.getStatus());
    }

    @Test
    public void setName() {
        event.setName("test name");
        assertEquals("test name", event.getName());
    }

    @Test
    public void setDate() {
        event.setDate(new GregorianCalendar(2021, Calendar.DECEMBER, 1));
        assertEquals(new GregorianCalendar(2021, Calendar.DECEMBER, 1), event.getDate());
    }

    @Test
    public void setLocation() {
        event.setLocation("UofT");
        assertEquals("UofT", event.getLocation());
    }

    @Test
    public void setNumAttendees() {
        event.setNumAttendees(25);
        assertEquals(25, event.getNumAttendees());
    }

    @Test
    public void setMealType() {
        event.setMealType(new Dinner("dinner"));
        assertNotEquals(new Lunch("lunch"), event.getMealType());
    }

    @Test
    public void setStatus() {
        event.setStatus("Cancelled");
        assertEquals("Cancelled", event.getStatus());
    }

    @Test
    public void testToString(){
        assertEquals("Event details (ID: 10): Birthday on 11/29/2021 at CN Tower for 30 attendees. " + "\r\n" +
                "Menu of dinner:" + "\r\n" + "Grilled Steak"+ "\r\n" + "Grilled Salmon" + "\r\n"+ "Large Salad" + "\r\n" +
                "Shrimp And Corn Chowder Soup" + "\r\n" + "Apple Juice" + "\r\n" + "Price of catering: $" +
                event.getPrice() + "\r\nThe current event status is: " + event.getStatus(), event.toString());
    }

    @Test
    public void testEquals(){
        Event event2 = new Event(10, "Birthday", new GregorianCalendar(2021, Calendar.DECEMBER, 1), "CN Tower",
                30, new Dinner("dinner"));
        Event event3 = new Event(11, "Birthday", new GregorianCalendar(2021, Calendar.DECEMBER, 1), "CN Tower",
                30, new Dinner("dinner"));
        assert(event.equals(event2));
        assert!(event.equals(event3));
        event2.setName("Halloween");
        assert(event.equals(event2));
    }
}