import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 This class represents an Event that a user is requesting catering for.
 */

public class Event implements Serializable {
    private int id;
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
     @param id unique id for the event
     @param name given name of the event
     @param date given date of the event
     @param location given location of the event
     @param numAttendees given number of attendees at the event
     @param mealType given meal type requested
     */
    public Event(int id, String name, Date date, String location, int numAttendees, Meal mealType) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
        this.numAttendees = numAttendees;
        this.mealType = mealType;
        this.employees = new ArrayList<>();
        // Edit depending on what the meal price getter method is
        this.price = mealType.getMealPrice() * numAttendees;
    }

    /**
     * Event getter for event's ID.
     * @return int. Return the ID of this event.
     */
    public int getID() {
        return id;
    }

    /**
     * Event getter for event's name.
     * @return String. Return the given name of this event.
     */
    public String getName() {
        return name;
    }
    /**
     * Event getter for event's date.
     * @return Date. Return the date of this event.
     */
    public Date getDate() {
        return date;
    }
    /**
     * Event getter for event's location.
     * @return String. Return the location of this event.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Event getter for number of Attendees at this event.
     * @return int. Return the given number of attendees of this event.
     */
    public int getNumAttendees() {
        return numAttendees;
    }

    /**
     * Event getter for event's meal type.
     * @return a meal object, the specified meal type for the event.
     */
    public Meal getMealType() {
        return mealType;
    }

    /**
     * Event getter for list of employees assigned.
     * @return Arraylist of employees assigned to this event.
     */
    public ArrayList<String> getEmployees() {
        return employees;
    }

    /**
     * Event getter for event's price
     * @return float for the event's total price.
     */
    public float getPrice() {
        return price;
    }

    /**
     * Getter for how many employees are needed to work at this event.
     * @return int for total employees needed.
     */
    public int getEmployeesNeeded() {
        return (int) Math.ceil(mealType.getNumEmployee() * numAttendees);
    }

    /**
     * Event setter for event's name.
     */
    public void setName(String newName) {
        name = newName;
    }
    /**
     * Event setter for event's date.
     */
    public void setDate(Date newDate) {
        date = newDate;
    }
    /**
     * Event setter for event's location.
     */
    public void setLocation(String newLocation) {
        location = newLocation;
    }
    /**
     * Event setter for event's number of attendees.
     */
    public void setNumAttendees(int attendees) {
        numAttendees = attendees;
    }

    /**
     * Event setter for event's meal type.
     */
    public void setMealType(Meal newMealType) {
        mealType = newMealType;
    }

    /**
     * Event setter for employees assigned to this event.
     */
    public void setEmployees(ArrayList<String> newEmployees) {
        employees = newEmployees;
    }

    /**
     Return a String representation of the Event class, including event details: name, date, location,
     number of attendees and the total price for this event.
     */
    @Override
    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Event details: " + this.name + " on " + sdf.format(this.date) + " at " + this.location + " for "
                + this.numAttendees + " attendees. " + "\r\n" + this.mealType.toString() + "\r\n" +
                "Price of catering: $" + this.price;
    }
}