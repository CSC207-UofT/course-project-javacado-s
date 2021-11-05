package front_end;
/*
This class is instantiated upon startup of program and is in charge of
communicating/sending information between Managers.EmployeeManager, Managers.EventManager,
and the command line/user.
 */

import commands.CreateEventCommand;
import managers.EmployeeManager;
import managers.EventManager;

import java.util.Date;

public class CateringSystem {
    EmployeeManager employeeManager;
    EventManager eventManager;

    /**
     * Constructor for front_end.CateringSystem
     * @param employeeManager Employees.Employee manager for system
     * @param eventManager Events.Event manager for system
     */
    public CateringSystem(EmployeeManager employeeManager, EventManager eventManager) {
        this.employeeManager = employeeManager;
        this.eventManager = eventManager;
    }

    /**
     * Create a new event and return a message about whether event request was accepted.
     *
     * @param name              The name of the Events.Event
     * @param date              The date of the Events.Event
     * @param location          The location of the Events.Event
     * @param numAttendees      The number of attendees of the Events.Event
     * @param mealType          The meal type of the Events.Event
     * @return a String message telling user if the event was successfully booked
     */
    public String createEvent(String name, Date date, String location, int numAttendees, String mealType) {
        CreateEventCommand cmd = new CreateEventCommand(eventManager, employeeManager, name, date, location,
                                                        numAttendees, mealType);
        return cmd.execute();
    }
}
