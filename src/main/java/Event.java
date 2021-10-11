import java.util.ArrayList;
import java.util.Date;

/**
This class represents an Event that an outside user is requesting catering for.
 */

public class Event {
    // Ask Faith about employee wages??
    // private static final int WAGE = 10;

    private String name;
    private Date date;
    private String location;
    private int numAttendees;
    private Meal mealType;
    private ArrayList<String> employees;
    private float price;

    /**
    Constructs an Event with a given event name, date, location, number of attendees, meal type requested, and a list
     of employees assigned to the event.

     @param name given name of the event
     @param date given date of the event
     @param location given location of the event
     @param numAttendees given number of attendees at the event
     @param mealType given meal type requested
     */
    public Event(String name, Date date, String location, int numAttendees, Meal mealType) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.numAttendees = numAttendees;
        this.mealType = mealType;
        this.employees = new ArrayList<>();
        this.price = 0;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public int getNumAttendees() {
        return numAttendees;
    }

    public Meal getMealType() {
        return mealType;
    }

    public ArrayList<String> getEmployees() {
        return employees;
    }

    /*
    public int getEmployeesNeeded() {
        // use getter method for meal attribute #employees per # attendees
    }

    public float getPrice() {
        //Ask Faith about Employee wages???
        // return (mealType.price * numAttendees) + (getEmployeesNeeded() * WAGE);
    }

     */
}
