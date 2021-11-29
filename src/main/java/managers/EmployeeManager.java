package managers;

import employees.Employee;

import java.util.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
This class represents the EmployeeManager part of our system, which is in charge of most, if not all,
Employee management faculties.
 */
public class EmployeeManager {

    private ArrayList<Employee> employee_list;

    /**
     * Constructs an instance of EmployeeManager.
     * employee_list is initialized as an empty ArrayList, and Employees are added from text from
     * "stored_employees.txt". If "stored_employees.txt" does not exist, it will be created, and employee_list
     * For now, we assume each line in "stored_employees.txt" is in the following format:
     * id, name
     *
     * This will be subject to change as we work on our program.
     */
    public EmployeeManager(){
        this.employee_list = new ArrayList<>();

        String name;
        int id;
        File list = new File("src/main/java/data_files/employees.txt");
        try {
            Scanner reader = new Scanner(list);
            while (reader.hasNextLine()) {
                String info = reader.nextLine();
                name = info.substring(info.indexOf(",") + 1).trim();
                id = Integer.parseInt(info.substring(0, info.indexOf(",")).trim());
                Employee e = new Employee(name, id);
                String rawDates = info.substring(info.indexOf("|"));
                // Date formatting: |{yyyy}[mm](dd)|
                while(rawDates.indexOf("|")==rawDates.lastIndexOf("|")){
                    int y = Integer.parseInt(info.substring(info.indexOf("{"),info.indexOf("}")));
                    int m = Integer.parseInt(info.substring(info.indexOf("["),info.indexOf("]")));
                    int d = Integer.parseInt(info.substring(info.indexOf("("),info.indexOf(")")));
                    GregorianCalendar date = new GregorianCalendar();
                    date.set(Calendar.YEAR, y);
                    date.set(Calendar.MONTH, m);
                    date.set(Calendar.DAY_OF_MONTH, d);
                    e.setUnavailability(date);
                    rawDates = rawDates.substring(1).substring(info.indexOf("|"));
                }
                this.employee_list.add(e);
            }
        }
        catch(FileNotFoundException e){
            try{
                if(list.createNewFile()) {
                    System.out.println("employees.txt was not found and a new file has been created.");
                }
            }
            catch(IOException io){
                io.printStackTrace();
                System.out.println("Something went horribly, horribly wrong.");
            }
        }
    }

    /**
     * @param id id of Employee
     * @return Employee if an Employee with given id exists, null if no such Employee exists
     */
    public Employee getEmployee(int id){
        for(Employee e:this.employee_list){
            if(id==e.getid()){
                return e;
            }
        }
        return null;
    }

    /**
     * @return ArrayList of all registered Employees.
     */
    public ArrayList<Employee> getEmployeeList() {
        return this.employee_list;
    }

    /**
     * @param d Date d
     * @return ArrayList of available Employees for specific Date d
     */
    public ArrayList<Employee> availableEmployees(GregorianCalendar d){
        ArrayList<Employee> available = new ArrayList<>();
        for(Employee e:this.employee_list){
            if(e.isAvailable(d)){
                available.add(e);
            }
        }
        return available;
    }

    /**
     * @param list list of Employees
     * @return Array containing the IDs of Employees in list
     */
    public int[] getID(ArrayList<Employee> list){
        int[] id_list = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            id_list[i]= list.get(i).getid();
        }
        return id_list;
    }

    /**
     * Employee getter for a given id.
     * @param num number of employees required
     * @param d given Date
     * @return boolean True if there are enough available Employees on given Date, False otherwise.
     */
    public boolean enoughEmployees(int num, GregorianCalendar d){
        return availableEmployees(d).size() >= num;
    }

    /**
     * Changes Employee availability (from available to unavailable) on a specific Date.
     * @param employees Employees to be assigned to work on a specific Date. Subject to change;
     *                  may be modified to accept an array of integers instead.
     * @param d the specific Date
     *
     * I believe we agreed that EventManager should be in charge of assigning Employees to
     * Events, specifically, so for now, this will just set specific dates to unavailable.
     */
    public void setUnavailable(ArrayList<Employee> employees, GregorianCalendar d){
        for(Employee e:employees){
            e.setUnavailability(d);
        }
    }

    public void setAvailable(ArrayList<Employee> employees, GregorianCalendar d) throws Exception {
        for(Employee e:employees){
            e.removeUnavailability(d);
        }
    }
}
