package front_end;
/*
This class is instantiated upon startup of program and is in charge of
communicating/sending information between EmployeeManager, EventManager,
and the command line/user.
 */

import commands.*;
import managers.EmployeeManager;
import managers.EventManager;

import java.util.GregorianCalendar;
import java.util.Scanner;

public class CateringSystem {
    EmployeeManager employeeManager;
    EventManager eventManager;

    /**
     * Constructor for CateringSystem
     * @param employeeManager Employee manager for system
     */
    public CateringSystem(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }

    /**
     * Setter method for System's EventManager
     * @param eventManager EventManager for specific User
     */
    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    /**
     * Create a new event and return a message about whether event request was accepted.
     * @param name              The name of the Event
     * @param date              The date of the Event
     * @param location          The location of the Event
     * @param numAttendees      The number of attendees of the Event
     * @param mealType          The meal type of the Event
     * @return a String message telling user if the event was successfully booked
     */
    public String createEvent(String name, GregorianCalendar date, String location, int numAttendees, String mealType) {
        CreateEventCommand cmd = new CreateEventCommand(eventManager, employeeManager, name, date, location,
                                                        numAttendees, mealType);
        return cmd.execute();
    }

    /**
     * Cancel event specified by ID.
     * @param id ID of event
     * @return a String message telling user if cancellation was successful
     */
    public String cancelEvent(int id) {
        CancelEventCommand cmd = new CancelEventCommand(eventManager, employeeManager, id);
        return cmd.execute();
    }

    /**
     * Modify event specified by ID.
     * @param id ID of event
     * @param action Action corresponding to attribute of Event to modify
     * @return a String message telling user if modification was successful
     */
    public String modifyEvent(Scanner input, int id, String action) {
        ModifyEventCommand cmd = new ModifyEventCommand(input, eventManager, id, action, employeeManager);
        return cmd.execute();
    }

    /**
     * View event specified by ID.
     * @param id ID of event
     * @return a String message with the details of the event
     */
    public String viewEvent(int id) {
        ViewEventCommand cmd = new ViewEventCommand(eventManager, id);
        return cmd.execute();
    }

    public String viewAllEvents() { return eventManager.getEventListString(); }

    /**
     * Check if event ID exists
     * @param id ID of Event
     * @return whether ID exists or not
     */
    public boolean eventIDExists(int id) {
        return eventManager.getEventByID(id) != null;
    }
  
    /**
     * Update event status for all events in the eventManager when the programs.
     * @param current current time when the program runs.
     */
    public void updateEventStatus(GregorianCalendar current){
        eventManager.updateEventStatus(current);
    }

    /* EXTREMELY BAND-AID FIX; CHANGE LATER */
    public EventManager getEventManager(){
        return this.eventManager;
    }
}
