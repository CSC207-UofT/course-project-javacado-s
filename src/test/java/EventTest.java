/**
 * This file contains JUnit test cases for class Event.
 */

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
public class EventTest {
    Event event;
    @Before
    public void setup(){
        event = new Event(10, "Birthday", new Date(121, Calendar.OCTOBER, 29, 18, 30, 24), "CN Tower",
                30, new Meal("dinner"));
    }
    @Test
    public void testGetStatus(){
        assertEquals("Created", event.getStatus());
        event.setStatus("Under Preparation");
        assertEquals("Under Preparation", event.getStatus());
    }

    @ Test
    public void testGetPrice(){
        assertEquals(660.0, event.getPrice(), 0);
    }

    @Test
    public void testToString(){
        assertEquals("Event details: Birthday on 29/10/2021 at CN Tower for 30 attendees. " + "\r\n" +
                "Menu of dinner:" + "\r\n" + "Grilled Steak"+ "\r\n" + "Grilled Salmon" + "\r\n"+ "Large Salad" + "\r\n" +
                "Shrimp And Corn Chowder Soup" + "\r\n" + "Apple Juice" + "\r\n" + "Price of catering: $" +
                event.getPrice() + "\r\n The current event status is: " + event.getStatus(), event.toString());
    }

    @Test
    public void testEquals(){
        Event event2 = new Event(10, "Birthday", new Date(121, Calendar.OCTOBER, 29, 18, 30, 24), "CN Tower",
                30, new Meal("dinner"));
        Event event3 = new Event(11, "Birthday", new Date(121, Calendar.OCTOBER, 29, 18, 30, 24), "CN Tower",
                30, new Meal("dinner"));
        assert(event.equals(event2));
        assert!(event.equals(event3));
        event2.setName("Halloween");
        assert(event.equals(event2));
    }
}