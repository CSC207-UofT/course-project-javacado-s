package front_end;/*
Command line interface that takes in user input
 */

import exceptions.InvalidPasswordException;
import exceptions.UserNotFoundException;
import managers.*;
import users.User;

import java.io.FileInputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        UserManager userManager = new UserManager();
        EmployeeManager employeeManager = new EmployeeManager();
        CateringSystem system = new CateringSystem(employeeManager);
        Scanner input = new Scanner(System.in);
        Tuple<User, Boolean> logInResult = new Tuple<>(null, false);
        boolean loggedIn = false;
        String logout = "";
        String exit = "";
        GregorianCalendar current = (GregorianCalendar) Calendar.getInstance();


        System.out.println("********************************************" +
                "********************************************");
        System.out.println("Hello! Welcome to Javacado's, your #1 catering service.");

        while (!exit.equals("exit")) {
            while (!logout.equals("x")) {
                while (!loggedIn) {
                    logInResult = loginPrompt(input, userManager);
                    loggedIn = logInResult.getSecond();

                    if (loggedIn) {
                        FileInputStream loggedInFile = logInResult.getFirst().getSerialized_events();
                        EventManager eventManager = new EventManager(loggedInFile);
                        system.setEventManager(eventManager);
                    }
                    system.updateEventStatus(current);
                }
                actionPrompt(input, system);

                System.out.println("\nIf you would like to log out, please enter \"x\", otherwise, press \"enter\": ");
                logout = input.nextLine().toLowerCase();
            }
            logout = "";
            loggedIn = false;

            /* EXTREMELY BAND-AID FIX HERE; CHANGE LATER */
            system.getEventManager().checkout();
            userManager.updateUser(logInResult.getFirst());

            System.out.println("\nIf you would like to exit, please enter \"exit\", otherwise, press \"enter\": ");
            exit = input.nextLine().toLowerCase();
        }

        System.out.println("\n******************************************" +
                "**********************************************");
        System.out.println("Thank you for choosing Javacado's! Have a nice day :)");
    }

    /**
     * Prompt user to log in or create new account
     * @param input Scanner object
     * @param userManager UserManager object
     * @return FileInputStream of User's existing Events in serialized form
     */
    private static Tuple<User, Boolean> loginPrompt(Scanner input, UserManager userManager) throws Exception {
        Tuple<User, Boolean> tuple;

        System.out.println("****************************************************************************************");
        System.out.println("\nWhich action would you like to perform?");
        System.out.println("\t1 Log in");
        System.out.println("\t2 Create a new account");
        System.out.println("\nPlease enter the action:");
        String action = input.nextLine();

        if (action.equals("1")) {
                System.out.println("\nUsername: ");
                String username = input.nextLine();
                System.out.println("Password: ");
                String password = input.nextLine();
                try {
                    User user = userManager.getUser(username, password);
                    System.out.println("\nWelcome, " + username + "!");
                    tuple = new Tuple<>(user, true);
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                    tuple = new Tuple<>(null, false);
                }
                return tuple;
        }
        else if (action.equals("2"))  {
            System.out.println("\nNew username: ");
            String username = input.nextLine();
            System.out.println("New password: ");
            String password = input.nextLine();
            userManager.createUser(username, password);
            System.out.println("\nSuccess! You may now log in with your new account.");
        }
        tuple = new Tuple<>(null, false);
        return tuple;
    }

    /**
     * Prompt user to choose an action to perform
     * @param input Scanner object
     * @param system CateringSystem object
     */
    private static void actionPrompt(Scanner input, CateringSystem system) {
        System.out.println("****************************************************************************************");
        System.out.println("\nWhich action would you like to perform?");
        System.out.println("\t1 Create new event");
        System.out.println("\t2 Cancel event");
        System.out.println("\t3 Modify event");
        System.out.println("\t4 View events");
        System.out.println("\nPlease enter the action (Press \"enter\" if you do not wish to perform an action):");
        String action = input.nextLine();

        switch (action) {
            case "1" : {
                newEventPrompt(input, system);
                break;
            }
            case "2" : {
                cancelEventPrompt(input, system);
                break;
            }
            case "3" : {
                modifyEventPrompt(input, system);
                break;
            }
            case "4" : {
                viewEventsPrompt(input, system);
                break;
            }
        }
    }

    /**
     * Prompt user for event ID to modify event and print whether change was successful.
     * @param input Scanner object
     * @param system CateringSystem object
     */
    private static void modifyEventPrompt(Scanner input, CateringSystem system) {
        String cont;

        System.out.println("\nPlease enter the ID of the event you would like to modify: ");
        String str_id = input.nextLine();
        int id = Integer.parseInt(str_id);

        cont = modifyEventHelper(input, system, id);

        while (cont.equalsIgnoreCase("yes")) {
            cont = modifyEventHelper(input, system, id);
        }
    }

    /**
     * Helper method for modifyEventPrompt()
     * @param input Scanner object
     * @param system CateringSystem object
     * @param id ID of Event to modify
     * @return String representing whether user wants to continue modifying the given Event
     */
    private static String modifyEventHelper(Scanner input, CateringSystem system, int id) {
        System.out.println("\nWhat would you like to modify in the event?");
        System.out.println("\t1 Name");
        System.out.println("\t2 Location");
        System.out.println("\t3 Number of attendees");
        System.out.println("\t4 Meal type");
        System.out.println("\nPlease enter the action (Press \"enter\" if you do not wish to perform an action):");
        String action = input.nextLine();

        System.out.println("\n"+system.modifyEvent(input, id, action));

        System.out.println("\nWould you like to modify anything else for this event? Please enter \"yes\" or \"no\": ");
        return input.nextLine();
    }

    /**
     * Prompt user for info to create new event and print whether request was successful.
     * @param input Scanner object
     * @param system CateringSystem object
     */
    private static void newEventPrompt(Scanner input, CateringSystem system) {
        System.out.println("\nPlease enter the name of your event: ");
        String name = input.nextLine();

        System.out.println("\nPlease enter the date of your event (month/day, e.g. 10/24): ");
        String date = input.nextLine();
        String[] newDate = date.split("/");
        GregorianCalendar eventDate = new GregorianCalendar(2021-1900, Integer.parseInt(newDate[0])-1, Integer.parseInt(newDate[1]));

        System.out.println("\nPlease enter the location of your event: ");
        String location = input.nextLine();

        System.out.println("\nPlease enter the number of attendees for your event: ");
        int numAttendees = input.nextInt();
        input.nextLine();

        System.out.println("\nAre we catering for breakfast, lunch, or dinner?: ");
        String mealType = input.nextLine();

        System.out.println("\n"+system.createEvent(name, eventDate, location, numAttendees, mealType));
    }

    /**
     * Prompt user for event ID to cancel event and print whether cancellation was successful.
     * @param input Scanner object
     * @param system CateringSystem object
     */
    private static void cancelEventPrompt(Scanner input, CateringSystem system) {
        System.out.println("\nPlease enter the ID of the event you would like to cancel: ");
        String str_id = input.nextLine();
        int id = Integer.parseInt(str_id);

        System.out.println("\n"+system.cancelEvent(id));
    }

    /**
     * Prompt user for event ID to view event and print event details.
     * @param input Scanner object
     * @param system CateringSystem object
     */
    private static void viewEventsPrompt(Scanner input, CateringSystem system) {
        System.out.println(system.viewAllEvents());
        System.out.println("\nPlease enter the ID of the event you would like to view: (Enter -1 to exit)");
        String str_id = input.nextLine();
        if((str_id.equals("-1"))){
            System.out.println("Exited Event Viewing");
            return;
        }
        int id = Integer.parseInt(str_id);

        String result = system.viewEvent(id);

        while (result.equals("null")) {
            if((str_id.equals("-1"))){
                System.out.println("Exited Event Viewing");
                return;
            }
            System.out.println("\nThe id you entered cannot be found. Please enter a different one. (Enter -1 to exit)");
            str_id = input.nextLine();
            id = Integer.parseInt(str_id);
            result = system.viewEvent(id);
        }
        System.out.println("\n" + system.viewEvent(id));

    }
}
