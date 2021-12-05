package employees;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Objects;

/*
This class represents an Employee of the company
 */

public class Employee {
    private final String name;
    private final int id;
    private final ArrayList<GregorianCalendar> unavailableDates;
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
    public ArrayList<GregorianCalendar> getUnavailableDates(){
        return unavailableDates;
    }
    public int getWage(){
        return Employee.WAGE;
    }

    /**
     * Returns True if Employee is available on given date, False if unavailable
     * @param date given date
     * @return boolean, True if Employee is available on that date
     */
    public boolean isAvailable(GregorianCalendar date){
        return !(this.unavailableDates.contains(date));
    }
    public void setUnavailability(GregorianCalendar unavailableDate){
        unavailableDates.add(unavailableDate);
    }

    @Override
    public boolean equals(Object e) {
        if (!(e instanceof Employee)) {
            return false;
        }


        return (Objects.equals(this.name, ((Employee) e).name) &&
                this.id == ((Employee) e).id &&
                this.unavailableDates == ((Employee) e).unavailableDates);
    }


    public void removeUnavailability(GregorianCalendar d) throws Exception {
        if(!unavailableDates.remove(d)){
            throw new Exception("Date was not found in " + this.name + "'s list of dates.");
        }
    }
}

