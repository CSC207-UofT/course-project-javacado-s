import java.util.ArrayList;
import java.util.Date;

/**
 * This class is used as a manager for the events
 */

public class EventManager {
    /*
    I'm pretty sure there should be more things in this class,
    let me know if any functionalities are still needed
     */

    private ArrayList<Event> eventList;

    /**
     * Construct a new EventManager, with an empty eventList
     *
     */
    public EventManager(){
        this.eventList = new ArrayList<>();
    }


    /**
     * Create a new Event from System input, and add it to the eventList
     *
     * @param name              The name of the Event
     * @param date              The date of the Event
     * @param location          The location of the Event
     * @param numAttendees      The number of attendees of the Event
     * @param mealType          The meal type of the Event
     */
    public void creatEvent(String name, Date date, String location,
                              int numAttendees, Meal mealType){
        Event newEvent = new Event(name, date, location, numAttendees, mealType);
        this.eventList.add(newEvent);
    }

    /**
     * Return a clone of the eventList
     *
     * @return      A clone of the eventList
     */
    public Object getEventList(){
        return this.eventList.clone();
    }

    /**
     * Return the event at the given index of eventList
     *
     * @param index     The index of the required event
     * @return          Return the required event
     */
    public Event getEventByIndex(int index){
        return this.eventList.get(index);
    }

}
