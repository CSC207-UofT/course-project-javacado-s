package employees;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        for (GregorianCalendar g: this.unavailableDates){
            if (g.get(GregorianCalendar.YEAR) == (date.get(GregorianCalendar.YEAR)) &&
            g.get(GregorianCalendar.MONTH) == date.get(GregorianCalendar.MONTH) &&
            g.get(GregorianCalendar.DAY_OF_MONTH) == date.get(GregorianCalendar.DAY_OF_MONTH)) {
                return false;
            }
        }
        return true;
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
                this.id == ((Employee) e).id);
    }


    public void removeUnavailability(GregorianCalendar d) throws Exception {
        GregorianCalendar temp = new GregorianCalendar();
        for (GregorianCalendar g: unavailableDates){
            if (g.get(GregorianCalendar.YEAR) == (d.get(GregorianCalendar.YEAR)) &&
                    g.get(GregorianCalendar.MONTH) == d.get(GregorianCalendar.MONTH) &&
                    g.get(GregorianCalendar.DAY_OF_MONTH) == d.get(GregorianCalendar.DAY_OF_MONTH)){
                unavailableDates.remove(g);
                return;
            }
        }
        throw new Exception("Date was not found in " + this.name + "'s list of dates.");
//        if(!unavailableDates.remove(d)){
//        }
    }
    @Override
    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        StringBuilder dates = new StringBuilder();
        for (GregorianCalendar g: this.unavailableDates){
            Date time = g.getTime();
            String date = sdf.format(time);
            dates.append(date).append("\r\n");
        }
        return this.name + " " + this.id + " " + dates;
    }
}

