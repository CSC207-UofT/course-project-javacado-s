import java.util.ArrayList;
import java.util.Date;

/**
 This class represents an Event that a user is requesting catering for.
 */

public class Event {
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
        // Edit depending on what the meal price getter method is
        this.price = mealType.getPrice() * numAttendees;
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

    public float getPrice() {
        return price;
    }

    public void setName(String newName) {
        name = newName;
    }
    public void setDate(Date newDate) {
        date = newDate;
    }

    public void setLocation(String newLocation) {
        location = newLocation;
    }

    public void setNumAttendees(int attendees) {
        numAttendees = attendees;
    }

    public void setMealType(Meal newMealType) {
        mealType = newMealType;
    }

    public void setEmployees(ArrayList<String> newEmployees) {
        employees = newEmployees;
    }

    @Override
    public String toString(){
        return "Event details: " + this.name + " on "+ this.date + " at " + this.location + " for " + this.mealType +
                " for " + this.numAttendees + " attendees." + "\r\n" + "Price of this event: " +  this.price;
    }

    /*
    public int getEmployeesNeeded() {
        // use getter method for Meal attribute # employees per # attendees
    }
    */
}

