import events.Event;
import exceptions.EventNotFoundException;
import exceptions.MealNotFoundException;
import managers.EmployeeManager;
import managers.EventManager;
import managers.UserManager;
import read_writers.UserManagerReadWriter;
import meals.Meal;
import meals.Dinner;
import meals.Lunch;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.GregorianCalendar;

public class EventManagerTest {

    EventManager em;
    UserManager um;
    EmployeeManager employeeManager;
    UserManagerReadWriter UserRW;

    @Before
    public void setUp() throws Exception {
        String user = "Tester2";
        um = new UserManager();
        um.createUser(user, "1234");
        em = new EventManager(new FileInputStream("src/main/java/data_files/users/" + user + "/events.txt"));
        employeeManager = new EmployeeManager();
        UserRW = new UserManagerReadWriter();
    }

    @After
    public void tearDown(){
        for(File f: Objects.requireNonNull(UserRW.USER_DIRECTORY.listFiles())){
            if(f.getName().contains("Tester")){
                for(File c: Objects.requireNonNull(f.listFiles())){
                    c.delete();
                }
                f.delete();
            }
        }
    }

    @Test
    public void createEvent() throws MealNotFoundException {
        int eventID = em.createEvent(108, "Test Event",
                new GregorianCalendar(2021, Calendar.DECEMBER, 1),
                "U of T", 20, "dinner");
        assert (eventID == 108);
    }

    @Test
    public void testCreateEvent2() throws MealNotFoundException {
        int eventID = em.createEvent("Test Event",
                new GregorianCalendar(2021, Calendar.DECEMBER, 1),
                "U of T", 20, "dinner");
        assert (eventID == 0);
    }

    @Test
    public void getEventList() throws MealNotFoundException {
        Meal testMeal = new Dinner("dinner");
        em.createEvent(108, "Test Event A",
                new GregorianCalendar(2021, Calendar.NOVEMBER, 20),
                "MY", 20, "dinner");

        em.createEvent(204, "Test Event B",
                new GregorianCalendar(2021, Calendar.DECEMBER, 21),
                "BA", 25, "dinner");

        Event eventA = new Event(108, "Test Event A",
                                new GregorianCalendar(2021, Calendar.NOVEMBER, 20),
                                "MY", 20, testMeal);

        Event eventB = new Event(204, "Test Event B",
                                new GregorianCalendar(2021, Calendar.DECEMBER, 21),
                                "BA", 25, testMeal);

        ArrayList<Event> expected = new ArrayList<>();
        expected.add(eventA);
        expected.add(eventB);
        ArrayList<Event> eventList = em.getEventList();
        assert (eventList.equals(expected));
    }

    @Test
    public void getEventByIndex() throws MealNotFoundException {
        Meal testMeal = new Dinner("dinner");
        em.createEvent(108, "Test Event A",
                new GregorianCalendar(2021, Calendar.NOVEMBER, 20),
                "MY", 20, "dinner");

        em.createEvent(204, "Test Event B",
                new GregorianCalendar(2021, Calendar.DECEMBER, 21),
                "BA", 25, "dinner");

        Event eventB = new Event(204, "Test Event B",
                new GregorianCalendar(2021, Calendar.DECEMBER, 21),
                "BA", 25, testMeal);

        ArrayList<Event> eventList = em.getEventList();
        assert (eventList.get(1).equals(eventB));
    }

    @Test
    public void getEventByID() throws MealNotFoundException {
        Meal testMeal = new Dinner("dinner");
        em.createEvent(108, "Test Event A",
                new GregorianCalendar(2021, Calendar.NOVEMBER, 20),
                "MY", 20, "dinner");

        em.createEvent(204, "Test Event B",
                new GregorianCalendar(2021, Calendar.DECEMBER, 21),
                "BA", 25, "dinner");

        Event eventB = new Event(204, "Test Event B",
                new GregorianCalendar(2021, Calendar.DECEMBER, 21),
                "BA", 25, testMeal);

        assert (em.getEventByID(204).equals(eventB));
        assert (em.getEventByID(200) == null);
    }

    @Test
    public void testGetEmployeesNeeded() throws MealNotFoundException {
        em.createEvent(108, "Test Event A",
                new GregorianCalendar(2021, Calendar.NOVEMBER, 20),
                "MY", 20, "dinner");
        assert (em.getEmployeesNeeded(108) == 12);
    }

    @Test
    public void testGetEventDate() throws MealNotFoundException {
        GregorianCalendar testDate = new GregorianCalendar(2021, Calendar.NOVEMBER, 20);
        em.createEvent(108, "Test Event A",
                new GregorianCalendar(2021, Calendar.NOVEMBER, 20),
                "MY", 20, "dinner");
        assert (em.getEventDate(108).equals(testDate));
    }

    @Test
    public void testGetEventName() throws MealNotFoundException {
        em.createEvent(108, "Test Event A",
                new GregorianCalendar(2021, Calendar.NOVEMBER, 20),
                "MY", 20, "dinner");
        assert (em.getEventName(108).equals("Test Event A"));
    }

    @Test
    public void getEventByName() throws EventNotFoundException, MealNotFoundException {
        Meal testMeal = new Dinner("dinner");
        em.createEvent(108, "Test Event A",
                new GregorianCalendar(2021, Calendar.NOVEMBER, 20),
                "MY", 20, "dinner");

        em.createEvent(204, "Test Event B",
                new GregorianCalendar(2021, Calendar.DECEMBER, 21),
                "BA", 25, "dinner");

        Event eventB = new Event(204, "Test Event B",
                new GregorianCalendar(2021, Calendar.DECEMBER, 21),
                "BA", 25, testMeal);

        assert (em.getEventByName("Test Event B").equals(eventB));
    }

    @Test
    public void getEventByDate() throws EventNotFoundException, MealNotFoundException {
        Meal testMeal = new Dinner("dinner");
        GregorianCalendar testedDate = new GregorianCalendar(2021, Calendar.DECEMBER, 21);

        em.createEvent(108, "Test Event A",
                new GregorianCalendar(2021, Calendar.NOVEMBER, 20),
                "MY", 20, "dinner");

        em.createEvent(204, "Test Event B",
                new GregorianCalendar(2021, Calendar.DECEMBER, 21),
                "BA", 25, "dinner");

        Event eventB = new Event(204, "Test Event B",
                new GregorianCalendar(2021, Calendar.DECEMBER, 21),
                "BA", 25, testMeal);

        assert (em.getEventByDate(testedDate).equals(eventB));
    }

    @Test
    public void getEventByLocation() throws EventNotFoundException, MealNotFoundException {
        Meal testMeal = new Dinner("dinner");
        em.createEvent(108, "Test Event A",
                new GregorianCalendar(2021, Calendar.NOVEMBER, 20),
                "MY", 20, "dinner");

        em.createEvent(204, "Test Event B",
                new GregorianCalendar(2021, Calendar.DECEMBER, 21),
                "BA", 25, "dinner");

        Event eventB = new Event(204, "Test Event B",
                new GregorianCalendar(2021, Calendar.DECEMBER, 21),
                "BA", 25, testMeal);

        assert (em.getEventByLocation("BA").equals(eventB));
    }

    @Test
    public void cancelEvent() throws MealNotFoundException {
        Meal testMeal = new Dinner("dinner");
        em.createEvent(108, "Test Event A",
                new GregorianCalendar(2021, Calendar.NOVEMBER, 20),
                "MY", 20, "dinner");

        em.createEvent(204, "Test Event B",
                new GregorianCalendar(2021, Calendar.DECEMBER, 21),
                "BA", 25, "dinner");

        Event eventA = new Event(108, "Test Event A",
                new GregorianCalendar(2021, Calendar.NOVEMBER, 20),
                "MY", 20, testMeal);

        Event eventB = new Event(204, "Test Event B",
                new GregorianCalendar(2021, Calendar.DECEMBER, 21),
                "BA", 25, testMeal);

        em.cancelEvent(eventA.getID());
        ArrayList<Event> expected = new ArrayList<>();
        expected.add(eventB);
        ArrayList<Event> eventList = em.getEventList();
        assert (eventList.equals(expected));
    }

    @Test
    public void getCancelledEvent() throws MealNotFoundException {
        Meal testMeal = new Dinner("dinner");

        em.createEvent(108, "Test Event A",
                new GregorianCalendar(2021, Calendar.NOVEMBER, 20),
                "MY", 20, "dinner");

        em.createEvent(204, "Test Event B",
                new GregorianCalendar(2021, Calendar.DECEMBER, 21),
                "BA", 25, "dinner");

        Event eventA = new Event(108, "Test Event A",
                new GregorianCalendar(2021, Calendar.NOVEMBER, 20),
                "MY", 20, testMeal);

        em.cancelEvent(eventA.getID());
        assert (em.getCancelledEvent(108).equals(eventA));
    }
    @Test
    public void testUpdateEventStatus() throws MealNotFoundException {
        em.createEvent(108, "Test Event A",
                new GregorianCalendar(2021, Calendar.NOVEMBER, 20),
                "MY", 20, "dinner");
        em.createEvent(204, "Test Event B",
                new GregorianCalendar(2021, Calendar.NOVEMBER, 21),
                "BA", 25, "dinner");

        em.updateEventStatus(new GregorianCalendar(2021, Calendar.NOVEMBER, 18));
        assert(em.getEventByID(108).getStatus().equals("Under Preparation"));
        assert(em.getEventByID(204).getStatus().equals("Under Preparation"));
        em.updateEventStatus(new GregorianCalendar(2021, Calendar.NOVEMBER, 24));
        assert(em.getEventByID(108).getStatus().equals("Completed"));
        assert(em.getEventByID(204).getStatus().equals("Completed"));
    }

    @Test
    public void testSetEventName() throws MealNotFoundException {
        em.createEvent(108, "Test Event A",
                new GregorianCalendar(2021, Calendar.NOVEMBER, 20),
                "MY", 20, "dinner");

        em.setEventName(108, "Test Event B");

        assert (em.getEventName(108).equals("Test Event B"));
    }

    @Test
    public void testSetEventMeal() throws MealNotFoundException {
        Meal testMeal = new Lunch("lunch");
        em.createEvent(108, "Test Event A",
                new GregorianCalendar(2021, Calendar.NOVEMBER, 20),
                "MY", 20, "dinner");

        em.setEventMeal(108, testMeal.getMealName());

        assert (em.getEventByID(108).getMealType().equals(testMeal));
    }

    @Test
    public void testSetEventLocation() throws MealNotFoundException {
        em.createEvent(108, "Test Event A",
                new GregorianCalendar(2021, Calendar.NOVEMBER, 20),
                "MY", 20, "dinner");

        em.setEventLocation(108, "BA");

        assert (em.getEventByID(108).getLocation().equals("BA"));
    }

    @Test
    public void testSetEventNumAttendees() throws MealNotFoundException {
        em.createEvent(108, "Test Event A",
                new GregorianCalendar(2021, Calendar.NOVEMBER, 20),
                "MY", 20, "dinner");
        //TODO: fix setEventNumAttendees tests
        //assert (em.setEventNumAttendees(108, 30, employeeManager));
        assert (em.getEventByID(108).getNumAttendees() == 30);
        //TODO: fix setEventNumAttendees tests
        //assert (!em.setEventNumAttendees(108, 5000, employeeManager));
        assert (em.getEventByID(108).getNumAttendees() == 30);
    }

    @Test
    public void testSetEventDate() throws MealNotFoundException {
        GregorianCalendar testDate = new GregorianCalendar(2021, Calendar.NOVEMBER, 31);

        em.createEvent(108, "Test Event A",
                new GregorianCalendar(2021, Calendar.NOVEMBER, 20),
                "MY", 20, "dinner");

        assert (em.setEventDate(108, testDate, employeeManager));
        assert (em.getEventByID(108).getDate().equals(testDate));
    }

    @Test
    public void testGetEventByIDWithException() throws EventNotFoundException, MealNotFoundException {
        Meal testMeal = new Dinner("dinner");

        em.createEvent(204, "Test Event B",
                new GregorianCalendar(2021, Calendar.DECEMBER, 21),
                "BA", 25, "dinner");

        Event eventB = new Event(204, "Test Event B",
                new GregorianCalendar(2021, Calendar.DECEMBER, 21),
                "BA", 25, testMeal);

        assert (em.getEventByIDWithException(204).equals(eventB));
    }

    @Test
    public void testGetEventListString() throws MealNotFoundException {
        em.createEvent(20, "Test Event A",
                new GregorianCalendar(2021, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, "dinner");

        em.createEvent(100, "Test Event B",
                new GregorianCalendar(2021, Calendar.NOVEMBER, 21, 17, 45, 31),
                "MY", 10, "dinner");

        em.createEvent(500, "Test Event C",
                new GregorianCalendar(2021, Calendar.NOVEMBER, 12, 17, 45, 31),
                "MY", 1, "dinner");

        String expected = "Below are a list of all your events with their IDs:" + "\r\n" + "20. Test Event A" +
                "\r\n" + "100. Test Event B" + "\r\n" + "500. Test Event C";

        System.out.println(em.getEventListString());

        assert (em.getEventListString().equals(expected));
    }
}
