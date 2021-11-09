package front_end;/*
Command line interface that takes in user input
 */

import managers.EmployeeManager;
import managers.EventManager;

import java.util.Locale;
import java.util.Scanner;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        EmployeeManager employeeManager = new EmployeeManager();
        EventManager eventManager = new EventManager();
        CateringSystem system = new CateringSystem(employeeManager, eventManager);
        Scanner input = new Scanner(System.in);
        String exit = "";

        System.out.println("********************************************" +
                "********************************************");
        System.out.println("Hello! Welcome to Javacado's, your #1 catering service.");

        while (!exit.equals("exit")) {
            actionPrompt(input, system);

            System.out.println("\nIf you would like to finish and exit, please enter \"exit\", " +
                    "otherwise, press \"enter\": ");
            exit = input.nextLine().toLowerCase(Locale.ROOT);
        }

        System.out.println("\n******************************************" +
                "**********************************************");
        System.out.println("Thank you for choosing Javacado's! Have a nice day :)");
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
        System.out.println("\nPlease enter the action (Press \"enter\" if you do not wish to perform an action):");
        String action = input.nextLine();

        if (action.equals("1")) {
            newEventPrompt(input, system);
        }
        else if (action.equals("2")) {
            cancelEventPrompt(input, system);
        }
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
        Date eventDate = new Date(2021, Integer.parseInt(newDate[0]), Integer.parseInt(newDate[1]));

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
}
