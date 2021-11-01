/*
Command line interface that takes in user input
 */

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

        while (!exit.equals("exit")) {
            System.out.println("Hello! Welcome to Javacado's, your #1 catering service. " +
                    "Please enter your request below:\n");

            newEventCommand(input, system);

            System.out.println("\nIf you would like to finish and exit, please enter \"exit\": ");
            exit = input.nextLine().toLowerCase(Locale.ROOT);
        }
    }

    // TODO: This method can be turned into a Command
    /**
     * Prompt user for info to create new event and print whether request was successful.
     * @param input Scanner object
     * @param system CateringSystem object
     */
    private static void newEventCommand(Scanner input, CateringSystem system) {
        System.out.println("Please enter the name of your event: ");
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
}
