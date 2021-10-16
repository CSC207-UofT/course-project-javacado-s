/*
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EventManagerTest {

    EventManager em;

    @Before
    public void setUp(){
        em = new EventManager();
    }

    @Test
    public void creatEvent() {
        int eventID = em.createEvent(108, "Test Event",
                    new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24),
                    "U of T", 20, "dinner");
        assert (eventID == 108);
    }

    @Test
    public void getEventList() {
        Meal testMeal = new Meal("dinner");
        em.createEvent(108, "Test Event A",
                new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24),
                "MY", 20, "dinner");

        em.createEvent(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, "dinner");

        Event eventA = new Event(108, "Test Event A",
                                new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24),
                                "MY", 20, testMeal);

        Event eventB = new Event(204, "Test Event B",
                                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                                "BA", 25, testMeal);

        ArrayList<Event> expected = new ArrayList<>();
        expected.add(eventA);
        expected.add(eventB);
        ArrayList<Event> eventList = em.getEventList();
        assert (eventList.equals(expected));
    }

    @Test
    public void getEventByIndex() {
        Meal testMeal = new Meal("dinner");
        em.createEvent(108, "Test Event A",
                new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24),
                "MY", 20, "dinner");

        em.createEvent(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, "dinner");

        Event eventB = new Event(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, testMeal);

        ArrayList<Event> eventList = em.getEventList();
        assert (eventList.get(1).equals(eventB));
    }

    @Test
    public void getEventByID() {
        Meal testMeal = new Meal("dinner");
        em.createEvent(108, "Test Event A",
                new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24),
                "MY", 20, "dinner");

        em.createEvent(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, "dinner");

        Event eventB = new Event(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, testMeal);

        assert (em.getEventByID(204).equals(eventB));
    }

    @Test
    public void getEventByName() throws EventNotFoundError {
        Meal testMeal = new Meal("dinner");
        em.createEvent(108, "Test Event A",
                new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24),
                "MY", 20, "dinner");

        em.createEvent(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, "dinner");

        Event eventB = new Event(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, testMeal);

        assert (em.getEventByName("Test Event B").equals(eventB));
    }

    @Test
    public void getEventByDate() throws EventNotFoundError {
        Meal testMeal = new Meal("dinner");
        Date testedDate = new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24);

        em.createEvent(108, "Test Event A",
                new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24),
                "MY", 20, "dinner");

        em.createEvent(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, "dinner");

        Event eventB = new Event(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, testMeal);

        assert (em.getEventByDate(testedDate).equals(eventB));
    }

    @Test
    public void getEventByLocation() throws EventNotFoundError {
        Meal testMeal = new Meal("dinner");
        em.createEvent(108, "Test Event A",
                new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24),
                "MY", 20, "dinner");

        em.createEvent(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, "dinner");

        Event eventB = new Event(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, testMeal);

        assert (em.getEventByLocation("Ba").equals(eventB));
    }

    @Test
    public void cancelEvent() {
        Meal testMeal = new Meal("dinner");
        em.createEvent(108, "Test Event A",
                new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24),
                "MY", 20, "dinner");

        em.createEvent(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, "dinner");

        Event eventA = new Event(108, "Test Event A",
                new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24),
                "MY", 20, testMeal);

        Event eventB = new Event(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, testMeal);

        em.cancelEvent(eventA);
        ArrayList<Event> expected = new ArrayList<>();
        expected.add(eventB);
        ArrayList<Event> eventList = em.getEventList();
        assert (eventList.equals(expected));
    }


    @Test
    public void getCancelledEvent(){
        Meal testMeal = new Meal("dinner");
        em.createEvent(108, "Test Event A",
                new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24),
                "MY", 20, "dinner");

        em.createEvent(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, "dinner");

        Event eventA = new Event(108, "Test Event A",
                new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24),
                "MY", 20, testMeal);

        em.cancelEvent(eventA);
        assert (em.getCancelledEvent(204)).equals(eventA);
    }
}
*/
