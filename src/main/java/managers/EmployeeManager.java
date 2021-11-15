package managers;

import employees.Employee;

import java.util.ArrayList;
import java.util.Date;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

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
     * Adds a new Employee to employee_list (and writes it to stored_employees.txt).
     * @param id id of new Employee
     * @param name name of new Employee
     * For now, we assume any input id is unique and valid.
     */
    public void addEmployee(String name, int id){
        Employee e = new Employee(name, id);
        this.employee_list.add(e);
        try{
            FileWriter fw = new FileWriter("src/main/java/data_files/employees.txt", true);
            fw.write(id + ", " + name + "\n");
            fw.close();
        }
        catch(IOException io){
            System.out.println("Something went wrong with writing to file.");
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
    public ArrayList<Employee> availableEmployees(Date d){
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
    public boolean enoughEmployees(int num, Date d){
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
    public void setUnavailable(ArrayList<Employee> employees, Date d){
        for(Employee e:employees){
            e.setUnavailability(d);
        }
    }
}
