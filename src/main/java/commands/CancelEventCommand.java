package commands;

import managers.EventManager;

/*
Command class that cancels an event
 */

public class CancelEventCommand implements ICommand<String>{
    private final int ID;
    private final EventManager EVENT_MANAGER;

    /**
     * Contructor for CancelEventCommand
     * @param eventManager Event manager that'll cancel the event
     * @param id ID of event to cancel
     */
    public CancelEventCommand(EventManager eventManager, int id) {
        ID = id;
        EVENT_MANAGER = eventManager;
    }

    /**
     * Cancel event.
     * @return String message indicating whether cancellation was successful
     */
    @Override
    public String execute() {
        boolean removed = EVENT_MANAGER.cancelEvent(ID);
        if (removed) {
            return "Your event cancellation (ID: " + ID + ") was successful.";
        }
        else {
            return "Sorry, your event cancellation was unsuccessful. Please make sure the event's ID is correct.";
        }
    }
}
