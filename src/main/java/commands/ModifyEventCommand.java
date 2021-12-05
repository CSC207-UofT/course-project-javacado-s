package commands;

import exceptions.MealNotFoundException;
import managers.EmployeeManager;
import managers.EventManager;
import meals.Meal;
import meals.MealSetter;

import java.util.Scanner;

public class ModifyEventCommand implements ICommand<String>{
    private final int ID;
    private final EventManager EVENT_MANAGER;
    private final String ACTION;
    private final Scanner INPUT;
    private final EmployeeManager EMPLOYEE_MANAGER;

    /**
     * Contructor for CancelEventCommand
     * @param eventManager Event manager that'll modify the event
     * @param id ID of event to modify
     */
    public ModifyEventCommand(Scanner input, EventManager eventManager, int id, String action,
                              EmployeeManager employeeManager) {
        ID = id;
        EVENT_MANAGER = eventManager;
        EMPLOYEE_MANAGER = employeeManager;
        ACTION = action;
        INPUT = input;
    }

    /**
     * Modifies event
     * @return new toString of modified Event
     */
    @Override
    public String execute() {
        switch (ACTION) {
            case "1": {
                System.out.println("\nPlease enter the new name of your event: ");
                String name = INPUT.nextLine();
                EVENT_MANAGER.setEventName(ID, name);
                break;
            }
            case "2": {
                System.out.println("\nPlease enter the new location of your event: ");
                String location = INPUT.nextLine();
                EVENT_MANAGER.setEventLocation(ID, location);
                break;
            }
            case "3": {
                System.out.println("\nPlease enter the new number of attendees for your event: ");
                String str_numAttendees = INPUT.nextLine();
                int numAttendees = Integer.parseInt(str_numAttendees);
                boolean result = EVENT_MANAGER.setEventNumAttendees(ID, numAttendees, EMPLOYEE_MANAGER);

                if (result) {
                    return EVENT_MANAGER.getEventByID(ID).toString();
                }
                else {
                    return "Sorry, we could not change the number of attendees. Please try again at a later time.";
                }
            }
            case "4": {
                System.out.println("\nAre we now catering for breakfast, lunch, or dinner?: ");
                String mealType = INPUT.nextLine();
                try {
                    MealSetter setMeal = new MealSetter(mealType);
                    Meal newMeal = setMeal.getMeal();
                    EVENT_MANAGER.setEventMeal(ID, newMeal);
                } catch (MealNotFoundException e) {
                    return "Sorry, the given meal type does not exist. Please try again.";
                }
                break;
            }
        }
        return EVENT_MANAGER.getEventByID(ID).toString();
    }
}
