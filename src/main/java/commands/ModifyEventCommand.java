package commands;

import managers.EventManager;
import meals.Meal;
import meals.MealSetter;

import java.util.Scanner;

public class ModifyEventCommand implements ICommand<String>{
    private final int ID;
    private final EventManager EVENT_MANAGER;
    private final String ACTION;
    private final Scanner INPUT;

    /**
     * Contructor for CancelEventCommand
     * @param eventManager Event manager that'll modify the event
     * @param id ID of event to modify
     */
    public ModifyEventCommand(Scanner input, EventManager eventManager, int id, String action) {
        ID = id;
        EVENT_MANAGER = eventManager;
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
                int numAttendees = INPUT.nextInt();
                INPUT.nextLine();
                EVENT_MANAGER.setNumAttendees(ID, numAttendees);
                break;
            }
            case "4": {
                System.out.println("\nAre we now catering for breakfast, lunch, or dinner?: ");
                String mealType = INPUT.nextLine();
                MealSetter setMeal = new MealSetter(mealType);
                Meal newMeal = setMeal.getMeal();
                EVENT_MANAGER.setMealType(ID, newMeal);
                break;
            }
        }
        return EVENT_MANAGER.getEventByID(ID).toString();
    }
}
