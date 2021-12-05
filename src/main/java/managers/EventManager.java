package managers;

//import commands.CreateMealCommand;
import events.Event;
import exceptions.EventNotFoundError;
import meals.MealSetter;
import gateway.CheckOutEvents;

import java.io.*;
import java.util.*;

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
    private int newId;
    private final EventNotFoundError eventNotFoundError;
    private final MealSetter setMeal = new MealSetter();

    /**
     * Construct a new EventManager, with an empty eventList
     *
     */
    @SuppressWarnings("unchecked")
    public EventManager(FileInputStream input) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(input);
        this.eventList = (ArrayList<Event>) in.readObject();
        this.idEventMap = new HashMap<>();
        for(Event e: eventList){
            idEventMap.put(e.getID(),e);
        }
        this.cancelledEvent = new HashMap<>();
        this.newId = 0;
        this.eventNotFoundError = new EventNotFoundError("The required event cannot be found");
        in.close();
        input.close();
    }


    /**
     * Create a new Event from System input, and add it to the eventList
     *
     * @param name              The name of the Event
     * @param date              The date of the Event
     * @param location          The location of the Event
     * @param numAttendees      The number of attendees of the Event
     * @param selectedMeal      The selected meal type
     * @return                  Return the created Event
     */

    public int createEvent(String name, GregorianCalendar date, String location,
                          int numAttendees, String selectedMeal){
        Event newEvent = new Event(this.newId, name, date, location,
                numAttendees, setMeal.getMeal(selectedMeal));
        this.eventList.add(newEvent);
        this.idEventMap.put(this.newId, newEvent);
        this.newId = this.newId + 1;
        return this.newId - 1;
    }

    /**
     * Create a new Event from System input with specific id, and add it to the eventList
     * Used in tests
     *
     * @param id                The id of the Event
     * @param name              The name of the Event
     * @param date              The date of the Event
     * @param location          The location of the Event
     * @param numAttendees      The number of attendees of the Event
     * @param selectedMeal      The selected meal type
     * @return                  Return the created Event
     */
    public int createEvent(int id, String name, GregorianCalendar date, String location,
                           int numAttendees, String selectedMeal){
        Event newEvent = new Event(id, name, date, location,
                numAttendees, setMeal.getMeal(selectedMeal));
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
        return new ArrayList<>(this.eventList);
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
     * Return the event that has the given id. Throws exceptions.EventNotFoundError
     * if the event cannot be found
     *
     * @param id        The required event's id
     * @return          Return the required event
     */
    public Event getEventByIDWithException(int id) throws EventNotFoundError {
        Event result = this.idEventMap.get(id);
        if (result == null){
            throw new EventNotFoundError("The event with the given id is not found");
        }
        return result;
    }

    public Event getEventByID(int id){
        try {
            return getEventByIDWithException(id);
        } catch (EventNotFoundError notFoundError) {
            return null;
        }
    }

    /**
     * Return the number of employees needed for the event with the given id.
     *
     * @param id        The required event's id
     * @return          Return the number of employees needed for the event
     */
    public int getEmployeesNeeded(int id) { return getEventByID(id).getEmployeesNeeded();}

    /**
     * Return the date of the event with the given id.
     *
     * @param id        The required event's id
     * @return          Return the date of the event
     */
    public GregorianCalendar getEventDate(int id) { return getEventByID(id).getDate();}

    /**
     * Return the name of the event with the given id.
     *
     * @param id        The required event's id
     * @return          Return the name of the event
     */
    public String getEventName(int id) { return getEventByID(id).getName();}

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
    public Object getEventByDate(GregorianCalendar time) throws EventNotFoundError {
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
     * Remove the event that has the given ID
     *
     * @param eventID      The ID of the event that needs to be removed
     * @return             Return true if event was removed from eventList
     */
    public boolean cancelEvent(int eventID){
        boolean result = this.eventList.remove(getEventByID(eventID));
        this.cancelledEvent.put(eventID, getEventByID(eventID));
        this.idEventMap.remove(eventID);
        return result;
    }

    /**
     * @return a String list of all the user's requested event names with their IDs.
     */
    public String getEventListString() {
        StringBuilder allEvents = new StringBuilder("Below are a list of all your events with their IDs:");

        SortedSet<Integer> ids = new TreeSet<>(idEventMap.keySet());
        for (int id : ids) {
            allEvents.append("\r\n").append(id).append(". ").append(idEventMap.get(id).getName());
        }

        return allEvents.toString();
    }

    /**
     * Get a cancelled event by its id
     *
     * @param id        The id of the cancelled event
     * @return          Return the cancelled event
     */
    public Event getCancelledEvent(int id){
        return this.cancelledEvent.get(id);
    }

    /**
     * Set the status of the event from "Created" to "Completed" or "Under Preparation" for all the events
     * in eventList.
     * @param current current time when the program run.
     */
    public void updateEventStatus(GregorianCalendar current){
        for (Event e: eventList) {
            if (e.getDate().compareTo(current) <= 0) {
                e.setStatus("Completed");
            }
            else if (e.getDate().compareTo(current) > 0){
                e.setStatus("Under Preparation");
            }
        }
    }

    /**
     * Set the name of the event with the given id to the given new name
     * @param id        The id of the Event
     * @param name      The new name of the Event
     */
    public void setEventName(int id, String name){
        getEventByID(id).setName(name);
    }

    /**
     * Set the mealType of the event with the given id to the given new mealType
     * @param id        The id of the Event
     * @param mealType  The new Meal of the Event
     */
    public void setEventMeal(int id, String mealType){
        getEventByID(id).setMealType(setMeal.getMeal(mealType));
    }

    /**
     * Set location of the event with the given id to the given new location
     * @param id        The id of the Event
     * @param location  The new location of the Event
     */
    public void setEventLocation(int id, String location){
        getEventByID(id).setLocation(location);
    }

    /**
     * Set number of attendees of the event with the given id to the new number
     * @param id        The id of the Event
     * @param newNum    The new number of attendees
     */
    public void setEventNumAttendees(int id, int newNum){ getEventByID(id).setNumAttendees(newNum); }

    /**
     * If there are enough employees for change that set date
     * of the event with the given id to the given new one.
     * Return True iff such change was able to carry out.
     *
     * @param id        The id of the Event
     * @param date      The new number of attendees
     * @param empM      The employeeManager, which has the data of the employees
     * @return          True iff such change was able to carry out, false otherwise
     */
    public boolean setEventDate(int id, GregorianCalendar date, EmployeeManager empM) {
        Event currEvent = getEventByID(id);
        if (!empM.enoughEmployees(currEvent.getEmployeesNeeded(), date)) {
            return false;
        }
        currEvent.setDate(date);
        return true;
    }

    /**
     * Serializes events_list to "_checkout.ser" file.
     */
    public void checkout(){
        new CheckOutEvents().checkout(this.eventList);
    }
}
