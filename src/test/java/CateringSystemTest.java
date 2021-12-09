import events.Event;
import front_end.CateringSystem;
import managers.EmployeeManager;
import managers.EventManager;
import managers.UserManager;
import read_writers.UserManagerReadWriter;
import meals.Dinner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class CateringSystemTest {
    CateringSystem c;
    EventManager EventM;
    UserManager UserM;
    EmployeeManager EmployeeM;
    UserManagerReadWriter UserRW;

    @Before
    public void setUp() throws Exception {
        String user = "Tester1";
        UserM = new UserManager();
        UserM.createUser(user, "1234");
        EventM = new EventManager(new FileInputStream("src/main/java/data_files/users/" + user + "/events.txt"));
        EmployeeM = new EmployeeManager();
        UserRW = new UserManagerReadWriter();

        c = new CateringSystem(EmployeeM);
        c.setEventManager(EventM);
    }

    @After
    public void tearDown(){
        for(File f: Objects.requireNonNull(UserRW.USER_DIRECTORY.listFiles())){
            if(f.getName().contains("Tester1")){
                for(File c: Objects.requireNonNull(f.listFiles())){
                    c.delete();
                }
                f.delete();
            }
        }
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
    public void testCreateEvent() {
        c.createEvent("Birthday", new GregorianCalendar(2021, Calendar.OCTOBER, 29), "CN Tower",
                30, "dinner");
        ArrayList<Event> Events = new ArrayList<>();
        Event test_event = new Event(0, "Birthday", new GregorianCalendar(2021, Calendar.OCTOBER, 29), "CN Tower",
                30, new Dinner("dinner"));
        Events.add(test_event);
        System.out.println(EventM.getEventList());
//        assert(EventM.getEventList().equals(Events));
    }

    @Test
    public void TestCancelEvent(){
        c.cancelEvent(0);
        ArrayList<Event> Events = new ArrayList<>();
        Event test_event = new Event(0, "Birthday", new GregorianCalendar(2021, Calendar.OCTOBER, 29), "CN Tower",
                30, new Dinner("dinner"));
        assert(EventM.getEventList().equals(Events));
    }

    @Test
    public void testModifyEventName() {
        c.createEvent("Test Event B", new GregorianCalendar(2021, Calendar.OCTOBER, 29),
                "BA", 10, "dinner");
        String input = "Reunion";
        Scanner s = new Scanner(input);
        c.modifyEvent(s, 0, "1");
        assert(EventM.getEventByID(0).getName().equals(input));
    }

    @Test
    public void testModifyEventLocation() {
        c.createEvent("Test Event B", new GregorianCalendar(2021, Calendar.OCTOBER, 29),
                "BA", 12, "dinner");
        String input = "MY";
        Scanner s = new Scanner(input);
        c.modifyEvent(s, 0, "2");
        assert(EventM.getEventByID(0).getLocation().equals(input));
    }

    @Test
    public void testModifyEventAttendees() {
        c.createEvent("Test Event B", new GregorianCalendar(2021, Calendar.OCTOBER, 29),
                "BA", 12, "dinner");
        String input = "10";
        Scanner s = new Scanner(input);
        c.modifyEvent(s, 0, "3");
        int actual = EventM.getEventByID(0).getNumAttendees();
        assert(Integer.toString(actual).equals("10"));
    }

    @Test
    public void testModifyEventMeal() {
        c.createEvent("Test Event B", new GregorianCalendar(2021, Calendar.OCTOBER, 29),
                "BA", 12, "dinner");
        String input = "lunch";
        Scanner s = new Scanner(input);
        c.modifyEvent(s, 0, "4");
        assert(EventM.getEventByID(0).getMealType().getMealName().equals(input));
    }

    @Test
    public void testViewEvent() {
        c.createEvent("Birthday", new GregorianCalendar(2021, Calendar.OCTOBER, 29), "CN Tower",
                10, "dinner");
        Event test_event = new Event(0, "Birthday", new GregorianCalendar(2021, Calendar.OCTOBER, 29), "CN Tower",
                10, new Dinner("dinner"));
        String expected = "Event details (ID: 0): Birthday on 10/29/2021 at CN Tower for 10 attendees. " + "\r\n" +
                "Menu of dinner:" + "\r\n" + "Grilled Steak"+ "\r\n" + "Grilled Salmon" + "\r\n"+ "Large Salad" + "\r\n" +
                "Shrimp And Corn Chowder Soup" + "\r\n" + "Apple Juice" + "\r\n" + "Price of catering: $" +
                test_event.getPrice() + "\r\nThe current event status is: "+ test_event.getStatus();
        assertEquals(expected, c.viewEvent(0));
    }

    @Test
    public void testViewAllEvent(){
        c.createEvent("Birthday", new GregorianCalendar(2021, Calendar.OCTOBER, 29), "CN Tower",
                10, "dinner");
        c.createEvent("Party", new GregorianCalendar(2021, Calendar.OCTOBER, 30), "CN Tower",
                10, "dinner");
        Event test_event = new Event(0, "Birthday", new GregorianCalendar(2021, Calendar.OCTOBER, 29), "CN Tower",
                10, new Dinner("dinner"));
        Event e2 = new Event(1, "Party", new GregorianCalendar(2021, Calendar.OCTOBER, 30), "CN Tower",
                10, new Dinner("dinner"));
        String expected = "Below are a list of all your events with their IDs:" + "\r\n" + "0. Birthday" + "\r\n" +
                "1. Party";
        assertEquals(expected, c.viewAllEvents());
    }
    @Test
    public void testEventIDExist(){
        c.createEvent("Birthday", new GregorianCalendar(2021, Calendar.OCTOBER, 29), "CN Tower",
                10, "dinner");
        Event test_event = new Event(0, "Birthday", new GregorianCalendar(2021, Calendar.OCTOBER, 29), "CN Tower",
                10, new Dinner("dinner"));
        assert(c.eventIDExists(test_event.getID()));
    }

    @Test
    public void testUpdateEventStatus(){
        c.createEvent("Birthday", new GregorianCalendar(2021, Calendar.OCTOBER, 29), "CN Tower",
                5, "dinner");
        c.updateEventStatus(new GregorianCalendar(2021, Calendar.OCTOBER, 29));
        assert(EventM.getEventByID(0).getStatus().equals("Completed"));
    }

}
