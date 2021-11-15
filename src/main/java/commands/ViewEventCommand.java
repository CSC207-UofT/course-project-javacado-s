package commands;

import events.Event;
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

    /** TODO: this part has been updated by Yifang/Lucas as to avoid being forced to exit due
     *  TODO: to EventNotFoundError. Please delete this line after checking its correctness
     *
     * View event.
     *
     * @return String message of event information.
     */
    @Override
    public String execute() {
        Event event = EVENT_MANAGER.getEventByID(ID);
        if (event == null){
            return "null";
        }
        return event.toString();
    }
}
