package commands;

import managers.EventManager;

/*
Command class that lets a user view details about an event.
 */

public class ViewEventCommand implements ICommand<String>{
    private final int ID;
    private final EventManager EVENT_MANAGER;

    /**
     * Contructor for ViewEventCommand
     *
     * @param eventManager Event manager that will get info on the event
     * @param id           ID of event to view
     */
    public ViewEventCommand(EventManager eventManager, int id) {
        ID = id;
        EVENT_MANAGER = eventManager;
    }

    /**
     * View event.
     *
     * @return String message of event information.
     */
    @Override
    public String execute() {
        return EVENT_MANAGER.getEventByID(ID).toString();
    }
}
