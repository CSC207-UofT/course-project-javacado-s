import events.Event;
import exceptions.EventNotFoundError;
import managers.EventManager;
import meals.Meal;
import meals.Dinner;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EventManagerTest {

    EventManager em;

    @Before
    public void setUp() throws IOException, ClassNotFoundException {
        String user = "testNew";
        em = new EventManager(new FileInputStream("src/main/java/data_files/users/" + user + "/events.txt"));
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
        Meal testMeal = new Dinner("dinner");
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
        Meal testMeal = new Dinner("dinner");
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
        Meal testMeal = new Dinner("dinner");
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
        Meal testMeal = new Dinner("dinner");
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
        Meal testMeal = new Dinner("dinner");
        Date testedDate = new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31);

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
        Meal testMeal = new Dinner("dinner");
        em.createEvent(108, "Test Event A",
                new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24),
                "MY", 20, "dinner");

        em.createEvent(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, "dinner");

        Event eventB = new Event(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, testMeal);

        assert (em.getEventByLocation("BA").equals(eventB));
    }

    @Test
    public void cancelEvent() {
        Meal testMeal = new Dinner("dinner");
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

        em.cancelEvent(eventA.getID());
        ArrayList<Event> expected = new ArrayList<>();
        expected.add(eventB);
        ArrayList<Event> eventList = em.getEventList();
        assert (eventList.equals(expected));
    }


    @Test
    public void getCancelledEvent(){
        Meal testMeal = new Dinner("dinner");

        em.createEvent(108, "Test Event A",
                new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24),
                "MY", 20, "dinner");

        em.createEvent(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, "dinner");

        Event eventA = new Event(108, "Test Event A",
                new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24),
                "MY", 20, testMeal);

        em.cancelEvent(eventA.getID());
        assert (em.getCancelledEvent(108).equals(eventA));
    }
}
