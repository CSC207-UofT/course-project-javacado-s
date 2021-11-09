package commands;

import managers.EventManager;

public class CancelEventCommand implements ICommand<String>{
    private final int ID;
    private final EventManager EVENT_MANAGER;

    public CancelEventCommand(EventManager eventManager, int id) {
        ID = id;
        EVENT_MANAGER = eventManager;
    }

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
