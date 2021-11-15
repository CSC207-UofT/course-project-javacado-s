package commands;

import managers.EmployeeManager;
import managers.EventManager;

import java.util.Date;

/*
Command class that creates a new Event class
 */

public class CreateEventCommand implements ICommand<String>{
    private final EventManager EVENT_MANAGER;
    private final EmployeeManager EMPLOYEE_MANAGER;
    private final String NAME;
    private final Date DATE;
    private final String LOCATION;
    private final int NUM_ATTENDEES;
    private final String MEAL_TYPE;

    /**
     * Constructor for CreateEventCommand.
     * @param eventManager Event manager that'll create the new Event
     * @param employee_manager Employee manager that'll check employees' availabilities
     * @param name name of event
     * @param date date of event
     * @param location location of event
     * @param numAttendees number of attendees of event
     * @param mealType meal type of event
     */
    public CreateEventCommand(EventManager eventManager, EmployeeManager employee_manager, String name, Date date,
                              String location, int numAttendees, String mealType) {
        EVENT_MANAGER = eventManager;
        EMPLOYEE_MANAGER = employee_manager;
        NAME = name;
        DATE = date;
        LOCATION = location;
        NUM_ATTENDEES = numAttendees;
        MEAL_TYPE = mealType;
    }

    /**
     * Create new event.
     * @return String message indicating whether request was accepted
     */
    @Override
    public String execute() {
        int newEventID = EVENT_MANAGER.createEvent(NAME, DATE, LOCATION, NUM_ATTENDEES, MEAL_TYPE);
        boolean enoughEmployees = EMPLOYEE_MANAGER.enoughEmployees(EVENT_MANAGER.getEmployeesNeeded(newEventID),
                EVENT_MANAGER.getEventDate(newEventID));

        if (enoughEmployees) {
            return "Thank you for choosing Javacado's! Your catering request was accepted." + "\r\n" +
                    EVENT_MANAGER.getEventByID(newEventID);
        }
        else {
            EVENT_MANAGER.cancelEvent(newEventID);
            return "Sorry, your catering request could not be accepted for this date (" + DATE + "). " +
                    "Please try requesting on a different date.";
        }
    }
}
