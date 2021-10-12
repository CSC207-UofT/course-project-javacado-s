import java.util.ArrayList;
import java.util.Date;

/*
This class represents an Employee of the company
 */

public class Employee {
    private final String name;
    private final int id;
    private final ArrayList<Date> unavailableDates;
    private static final int WAGE = 10;

    /**
     Constructs instance of Employee, given employee name and ID
     * @param name given name of Employee
     * @param id given ID of employee
     */

    public Employee(String name, int id){
        this.name = name;
        this.id = id;
        this.unavailableDates = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public int getid(){
        return id;
    }
    public ArrayList<Date> getUnavailableDates(){
        return unavailableDates;
    }

    /**
     * Returns True if Employee is available on given date, False if unavailable
     * @param date given date
     * @return boolean, True if Employee is avaialble on that date
     */
    public boolean isAvailable(Date date){
        return !(this.unavailableDates.contains(date));
    }
    public void setUnavailability(Date unavailableDate){
        unavailableDates.add(unavailableDate);
    }
}

