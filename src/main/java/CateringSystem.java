/*
This class is instantiated upon startup of program and is in charge of
communicating/sending information between EmployeeManager, EventManager,
and the command line/user.
 */

import java.util.Date;

public class CateringSystem {
    EmployeeManager employeeManager;
    EventManager eventManager ;

    /**
     * Constructor for CateringSystem
     */
    public CateringSystem() {
        this.employeeManager = new EmployeeManager();
        this.eventManager = new EventManager();
    }

    /**
     * Pass event info to EventManager's createEvent method to create a new event.
     * Also returns a message about whether event request was accepted.
     *
     * @param name              The name of the Event
     * @param date              The date of the Event
     * @param location          The location of the Event
     * @param numAttendees      The number of attendees of the Event
     * @param mealType          The meal type of the Event
     * @return a String message telling user if the event was successfully booked
     */
    public String createEvent(String name, Date date, String location,
                            int numAttendees, String mealType) {
        // TODO: the createEvent() in EventManager should return the created Event for ease of checking availability
        int newEventID = eventManager.createEvent(name, date, location, numAttendees, mealType);
        return checkAvailability(newEventID);
    }

    /**
     * Check availability of employees to see if event request can be accepted.
     *
     * @param newEvent New event being checked
     * @return String message indicating whether request was accepted
     */
    private String checkAvailability(int newEvent) {
        // TODO: create getEmployeesNeeded() in EmployeeManager Class
        boolean enoughEmployees = employeeManager.enoughEmployees(eventManager.getEmployeesNeeded(newEvent),
                eventManager.getEventDate(newEvent));

        if (enoughEmployees) {
            return "Thank you for choosing Javacado's! Your catering request was accepted." + "\r\n" +
                    eventManager.getEventByID(newEvent);
        }
        else {
            // TODO: create cancelEvent() in EventManager Class
            eventManager.cancelEvent(newEvent);
            return "Sorry, your catering request could not be accepted for this date. " +
                    "Please try requesting on a different date.";
        }

    }
}
