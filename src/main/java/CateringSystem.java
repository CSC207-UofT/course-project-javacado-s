/*
This class is instantiated upon startup of program and is in charge of
communicating/sending information between EmployeeManager, EventManager,
and the command line/user.
 */

import java.util.Date;

public class CateringSystem {
    EmployeeManager employeeManager;
    EventManager eventManager;

    /**
     * Constructor for CateringSystem
     * @param employeeManager Employee manager for system
     * @param eventManager Event manager for system
     */
    public CateringSystem(EmployeeManager employeeManager, EventManager eventManager) {
        this.employeeManager = employeeManager;
        this.eventManager = eventManager;
    }

    /**
     * Create a new event and return a message about whether event request was accepted.
     *
     * @param name              The name of the Event
     * @param date              The date of the Event
     * @param location          The location of the Event
     * @param numAttendees      The number of attendees of the Event
     * @param mealType          The meal type of the Event
     * @return a String message telling user if the event was successfully booked
     */
    public String createEvent(String name, Date date, String location, int numAttendees, String mealType) {
        CreateEventCommand cmd = new CreateEventCommand(eventManager, employeeManager, name, date, location,
                                                        numAttendees, mealType);
        return cmd.execute();
    }
}
