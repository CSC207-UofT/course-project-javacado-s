package managers;

import employees.Employee;
import front_end.Tuple;
import read_writers.EmployeeManagerReadWriter;

import java.io.File;
import java.io.IOException;

import java.util.*;

/*
This class represents the EmployeeManager part of our system, which is in charge of most, if not all,
Employee management faculties.
 */
public class EmployeeManager {

    private ArrayList<Employee> employee_list;
    private final EmployeeManagerReadWriter RW;

    /**
     * Constructs an instance of EmployeeManager.
     * employee_list is initialized as an empty ArrayList, and Employees are added from text from the existing
     * database "employees.txt".
     *
     * This will be subject to change as we work on our program.
     */
    public EmployeeManager(){
        this.employee_list = new ArrayList<>();
        this.RW = new EmployeeManagerReadWriter();

        HashMap<Integer, Tuple<String, ArrayList<GregorianCalendar>>> employeeInfo = RW.read();

        for (Integer id: employeeInfo.keySet()) {
            Employee e = new Employee(employeeInfo.get(id).getFirst(), id);
            for (GregorianCalendar date: employeeInfo.get(id).getSecond()) {
                e.setUnavailability(date);
            }
            this.employee_list.add(e);
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
     * @return ArrayList containing the IDs of Employees in list
     */
    public ArrayList<Integer> getID(ArrayList<Employee> list){
        ArrayList<Integer> id_list = new ArrayList<>();
        for(Employee e: list){
            id_list.add(e.getid());
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
     * Changes Employee availability (from available to unavailable) on a specific date.
     * @param employees List of Employees to be assigned to work on a specific Date.
     * @param d the specific Date
     * Since this is EmployeeManager side, the parameter is left as an ArrayList of Employees rather than a list of IDs.
     */
    public void setUnavailable(ArrayList<Employee> employees, GregorianCalendar d){
        for(Employee e:employees){
            e.setUnavailability(d);
        }
        try {
            update();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Changes Employee availability (from unavailable to available) on a specific date.
     * @param id_list List of Employee IDs to be assigned to work on a specific Date.
     * @param d the specific Date
     * Since the input is EventManager side, the parameter is left as an ArrayList of IDs.
     */
    public void setAvailable(ArrayList<Integer> id_list, GregorianCalendar d){
        ArrayList<Employee> employees = IDToEmployee(id_list);
        for(Employee e:employees){
            try {
                e.removeUnavailability(d);
            }
            catch(Exception except){
                System.out.println(except.getMessage());
            }
        }
        try{
            update();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Helper function. Converts ArrayList of Integers to ArrayList of Employees.
     * @param id_list List of Employee IDs
     * @return List of Employees
     */
    public ArrayList<Employee> IDToEmployee(ArrayList<Integer> id_list){
        ArrayList<Employee> e_list = new ArrayList<>();
        for(Integer i:id_list){
            e_list.add(this.getEmployee(i));
        }
        return e_list;
    }

    /**
     * Updates employees.txt.
     */
    public void update() throws IOException {
        RW.update(this.employee_list);
    }

    public ArrayList<Employee> chooseEmployees(int num, GregorianCalendar d){
        ArrayList<Employee> chosen = new ArrayList<>();
        for(int i=0; i<num; i++){
            chosen.add(availableEmployees(d).get(i));
        }
        return chosen;
    }
}
