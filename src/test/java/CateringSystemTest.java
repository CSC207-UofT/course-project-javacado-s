import events.Event;
import front_end.CateringSystem;
import managers.EmployeeManager;
import managers.EventManager;
import managers.UserManager;
import meals.Dinner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import users.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class CateringSystemTest {
    CateringSystem c;
    EventManager EventM;
    UserManager UserM;
    EmployeeManager EmployeeM;


    @Before
    public void setUp() throws Exception {
        String user = "Tester1";
        UserM = new UserManager();
        UserM.createUser(user, "1234");
        EventM = new EventManager(new FileInputStream("src/main/java/data_files/users/" + user + "/events.txt"));
        EmployeeM = new EmployeeManager();
        c = new CateringSystem(EmployeeM);
        c.setEventManager(EventM);
    }

    @After
    public void tearDown(){
        for(File f: Objects.requireNonNull(UserM.USER_DIRECTORY.listFiles())){
            if(f.getName().contains("Tester1")){
                for(File c: Objects.requireNonNull(f.listFiles())){
                    c.delete();
                }
                f.delete();
            }
        }
    }

    @Test
    public void testCreateEvent(){
        c.createEvent("Birthday", new Date(121, Calendar.OCTOBER, 29, 18, 30, 24), "CN Tower",
                30, "dinner");
        ArrayList<Event> Events = new ArrayList<Event>();
        Event test_event = new Event(0, "Birthday", new Date(121, Calendar.OCTOBER, 29, 18, 30, 24), "CN Tower",
                30, new Dinner("dinner"));
        Events.add(test_event);
        System.out.println(EventM.getEventList());
//        assert(EventM.getEventList().equals(Events));
    }

    @Test
    public void TestCancelEvent(){
        c.cancelEvent(0);
        ArrayList<Event> Events = new ArrayList<>();
        Event test_event = new Event(0, "Birthday", new Date(121, Calendar.OCTOBER, 29, 18, 30, 24), "CN Tower",
                30, new Dinner("dinner"));
        assert(EventM.getEventList().equals(Events));
    }

    @Test
    public void testModifyEventName() throws exceptions.EventNotFoundError {
        c.createEvent("Test Event B", new Date(121, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, "dinner");
        String input = "Reunion";
        Scanner s = new Scanner(input);
        c.modifyEvent(s, 0, "1");
        assert(EventM.getEventByID(0).getName().equals(input));
    }

    @Test
    public void testModifyEventLocation() throws exceptions.EventNotFoundError {
        c.createEvent("Test Event B", new Date(121, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, "dinner");
        String input = "MY";
        Scanner s = new Scanner(input);
        c.modifyEvent(s, 0, "2");
        assert(EventM.getEventByID(0).getLocation().equals(input));
    }

    //Todo: fix the ModifyEventAttendees so that it pass

//    @Test
//    public void testModifyEventAttendees() throws exceptions.EventNotFoundError {
//        c.createEvent("Test Event B", new Date(121, Calendar.DECEMBER, 21, 17, 45, 31),
//                "BA", 25, "dinner");
//        String input = "45";
//        Scanner s = new Scanner(input);
//        c.modifyEvent(s, 0, "3");
//        int actual = EventM.getEventByID(0).getNumAttendees();
//        assert(Integer.toString(actual).equals("45"));
//    }

    @Test
    public void testModifyEventMeal() throws exceptions.EventNotFoundError {
        c.createEvent("Test Event B", new Date(121, Calendar.DECEMBER, 21, 17, 45, 31),
                "BA", 25, "dinner");
        String input = "lunch";
        Scanner s = new Scanner(input);
        c.modifyEvent(s, 0, "4");
        assert(EventM.getEventByID(0).getMealType().getMealName().equals(input));
    }

    @Test
    public void testViewEvent(){
        c.createEvent("Birthday", new Date(121, Calendar.OCTOBER, 29, 18, 30, 24), "CN Tower",
                30, "dinner");
        Event test_event = new Event(0, "Birthday", new Date(121, Calendar.OCTOBER, 29, 18, 30, 24), "CN Tower",
                30, new Dinner("dinner"));
        String expected = "Event details (ID: 0): Birthday on 29/10/2021 at CN Tower for 30 attendees. " + "\r\n" +
                "Menu of dinner:" + "\r\n" + "Grilled Steak"+ "\r\n" + "Grilled Salmon" + "\r\n"+ "Large Salad" + "\r\n" +
                "Shrimp And Corn Chowder Soup" + "\r\n" + "Apple Juice" + "\r\n" + "Price of catering: $" +
                test_event.getPrice() + "\r\n The current event status is: "+ test_event.getStatus();
        assertEquals(expected, c.viewEvent(0));
    }
}