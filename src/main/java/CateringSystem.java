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
                            int numAttendees, Meal mealType) {
        // TODO: the createEvent() in EventManager should return the created Event for ease of checking availability
        Event newEvent = eventManager.creatEvent(name, date, location, numAttendees, mealType);
        return checkAvailability(newEvent);
    }

    private String checkAvailability(Event newEvent) {
        float price = newEvent.getPrice();
        int numAttendees = newEvent.getNumAttendees();

        // TODO: create getEmployeesNeeded() in EmployeeManager Class
        boolean enoughEmployees = employeeManager.enoughEmployees(newEvent.getEmployeesNeeded(), newEvent.getDate());

        if (enoughEmployees) {
            return "Thank you for choosing Javacado's! Your catering request was accepted. " + newEvent;
        }
        else {
            // TODO: create cancelEvent() in EventManager Class
            eventManager.cancelEvent(newEvent);
            return "Sorry, your catering request could not be accepted for this date. " +
                    "Please try requesting on a different date.";
        }

    }
}
