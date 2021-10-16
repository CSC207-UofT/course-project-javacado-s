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
                    "U of T", 20, "Dinner");
        assert (eventID == 108);
    }

    @Test
    public void getEventList() {
        em.createEvent(108, "Test Event A",
                new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24),
                "MY", 20, "Dinner");

        em.createEvent(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, "Dinner");

        Event eventA = new Event(108, "Test Event A",
                                new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24),
                                "MY", 20, "Dinner");

        Event eventB = new Event(204, "Test Event B",
                                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                                "BA", 25, "Dinner");

        ArrayList<Event> expected = new ArrayList<>();
        expected.add(eventA);
        expected.add(eventB);
        ArrayList<Event> eventList = em.getEventList();
        assert (eventList.equals(expected));
    }

    @Test
    public void getEventByIndex() {
        em.createEvent(108, "Test Event A",
                new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24),
                "MY", 20, "Dinner");

        em.createEvent(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, "Dinner");

        Event eventB = new Event(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, "Dinner");

        ArrayList<Event> eventList = em.getEventList();
        assert (eventList.get(1).equals(eventB));
    }

    @Test
    public void getEventByID() {
        em.createEvent(108, "Test Event A",
                new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24),
                "MY", 20, "Dinner");

        em.createEvent(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, "Dinner");

        Event eventB = new Event(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, "Dinner");

        assert (em.getEventByID(204).equals(eventB));
    }

    @Test
    public void getEventByName() throws EventNotFoundError {
        em.createEvent(108, "Test Event A",
                new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24),
                "MY", 20, "Dinner");

        em.createEvent(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, "Dinner");

        Event eventB = new Event(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, "Dinner");

        assert (em.getEventByName("Test Event B").equals(eventB));
    }

    @Test
    public void getEventByDate() throws EventNotFoundError {
        Date testedDate = new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24);

        em.createEvent(108, "Test Event A",
                new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24),
                "MY", 20, "Dinner");

        em.createEvent(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, "Dinner");

        Event eventB = new Event(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, "Dinner");

        assert (em.getEventByDate(testedDate).equals(eventB));
    }

    @Test
    public void getEventByLocation() throws EventNotFoundError {
        em.createEvent(108, "Test Event A",
                new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24),
                "MY", 20, "Dinner");

        em.createEvent(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, "Dinner");

        Event eventB = new Event(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, "Dinner");

        assert (em.getEventByLocation("Ba").equals(eventB));
    }

    @Test
    public void cancelEvent() {
        em.createEvent(108, "Test Event A",
                new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24),
                "MY", 20, "Dinner");

        em.createEvent(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, "Dinner");

        Event eventA = new Event(108, "Test Event A",
                new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24),
                "MY", 20, "Dinner");

        Event eventB = new Event(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, "Dinner");

        em.cancelEvent(eventA);
        ArrayList<Event> expected = new ArrayList<>();
        expected.add(eventB);
        ArrayList<Event> eventList = em.getEventList();
        assert (eventList.equals(expected));
    }


    @Test
    public void getCancelledEvent(){
        em.createEvent(108, "Test Event A",
                new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24),
                "MY", 20, "Dinner");

        em.createEvent(204, "Test Event B",
                new Date(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, "Dinner");

        Event eventA = new Event(108, "Test Event A",
                new Date(2021, Calendar.NOVEMBER, 20, 18, 30, 24),
                "MY", 20, "Dinner");

        em.cancelEvent(eventA);
        assert (em.getCancelledEvent(204)).equals(eventA);
    }
}
