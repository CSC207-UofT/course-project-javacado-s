import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * This class is used as a manager for the events
 */

public class EventManager {
    /*
    I'm pretty sure there should be more things in this class,
    let me know if any functionalities are still needed
     */

    private ArrayList<Event> eventList;
    private HashMap<Integer, Event> idEventMap;
    private HashMap<Integer, Event> cancelledEvent;

    /**
     * Construct a new EventManager, with an empty eventList
     *
     */
    public EventManager(){
        this.eventList = new ArrayList<>();
        this.idEventMap = new HashMap<>();
        this.cancelledEvent = new HashMap<>();
    }


    /**
     * Create a new Event from System input, and add it to the eventList
     *
     * @param id                The id of the Event
     * @param name              The name of the Event
     * @param date              The date of the Event
     * @param location          The location of the Event
     * @param numAttendees      The number of attendees of the Event
     * @param selectedMeal      The selected meal type
     * @return                  Return the created Event
     */
    public int createEvent(int id, String name, Date date, String location,
                          int numAttendees, String selectedMeal){
        Meal newMeal = new Meal(numAttendees, selectedMeal);
        Event newEvent = new Event(name, date, location, numAttendees, newMeal);
        this.eventList.add(newEvent);
        this.idEventMap.put(id, newEvent);
        return id;
    }

    /**
     * Return a clone of the eventList
     *
     * @return      A clone of the eventList
     */
    public ArrayList<Event> getEventList(){
        ArrayList<Event> result = new ArrayList<>(this.eventList);
        return result;
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


    /**
     * Return the event that has the given id. Throws EventNotFoundError
     *
     * @param id        The required event's id
     * @return          Return the required event
     */
    public Event getEventByID(int id) {
        return this.idEventMap.get(id);
    }


    // Some more getter methods, in case needed

    /**
     * Return the event that has the given name
     *
     * @param name      The required event's name
     * @return          Return the required event. Return "Event name
     *      *                  not found." if there is not such event
     */
    public Event getEventByName(String name) throws EventNotFoundError{
        for (Event e : this.eventList) {
            if (e.getName().equals(name)) {
                return e;
            }
        }

        throw new EventNotFoundError("Event name " + name + " not found");
    }

    /**
     * Return the event that happens on the given time
     *
     * @param time      The required event's time
     * @return          Return the required event. Return "Event date
     *                  not found." if there is not such event
     */
    public Object getEventByDate(Date time) throws EventNotFoundError {
        for (Event e : this.eventList){
            if (e.getDate().equals(time)){
                return e;
            }
        }

        throw new EventNotFoundError("Event name " + time + " not found");
    }

    /**
     * Return the event that happens at the given location
     *
     * @param location  The required event's location
     * @return          Return the required event. Return "Event location
     *                  not found." if there is not such event
     */
    public Object getEventByLocation(String location) throws EventNotFoundError {
        for (Event e : this.eventList){
            if (e.getLocation().equals(location)){
                return e;
            }
        }

        throw new EventNotFoundError("Event name " + location + " not found");
    }

    /**
     * Remove the event that has the given name
     *
     * @param event      The name of the event that needs to be removed
     */
    public void cancelEvent(Event event){
        this.eventList.remove(event);
        this.idEventMap.remove(event.getID());
        this.cancelledEvent.put(event.getID(), event);
    }

    public String toString(int id) {
        Event event = this.getEventByID(id);
        return event.toString();

    }

    public Event getCancelledEvent(int id){
        return this.cancelledEvent.get(id);
    }

}
