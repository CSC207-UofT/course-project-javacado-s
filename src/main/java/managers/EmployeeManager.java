package managers;

import employees.*;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;

import java.util.*;

/*
This class represents the EmployeeManager part of our system, which is in charge of most, if not all,
Employee management faculties.
 */
public class EmployeeManager {

    private ArrayList<Employee> employee_list;

    /**
     * Constructs an instance of EmployeeManager.
     * employee_list is initialized as an empty ArrayList, and Employees are added from text from the existing
     * database "employees.txt".
     *
     * This will be subject to change as we work on our program.
     */
    public EmployeeManager(){
        this.employee_list = new ArrayList<>();

        String name;
        int id;
        File employees_path = new File("src/main/java/data_files/employees.txt");
        try {
            Scanner reader = new Scanner(list);
            int i = 0;
            while (reader.hasNextLine()) {
                String info = reader.nextLine();
                name = info.substring(info.indexOf(",") + 1).trim();
                id = Integer.parseInt(info.substring(0, info.indexOf(",")).trim());
                Employee e;
                if (i % 4 == 0){
                    e = new Chef(name, id);
                }
                else if(i % 4 == 1){
                    e = new Cleaner(name, id);
                }
                else if (i % 4 == 2){
                    e = new Server(name, id);
                }
                else {
                    e = new Supervisor(name, id);
                }
                this.employee_list.add(e);
                i ++;
            }
            br.close();
            fr.close();
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
        FileWriter fw = new FileWriter("src/main/java/data_files/employees.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        for(Employee e: employee_list){
            //id, name|{yyyy}[mm](dd)|{yyyy}[mm](dd)|...|
            String line = e.getid()  + ", " + e.getName() + "|";
            for(GregorianCalendar d: e.getUnavailableDates()){
                line = line.concat("{"+d.get(Calendar.YEAR)+"}");
                line = line.concat("["+d.get(Calendar.MONTH)+"]");
                line = line.concat("("+d.get(Calendar.DAY_OF_MONTH)+")");
            }
            bw.write(line);
        }
        bw.close();
        fw.close();
    }

    public ArrayList<Employee> chooseEmployees(int num, GregorianCalendar d){
        ArrayList<Employee> chosen = new ArrayList<>();
        for(int i=0; i<num; i++){
            chosen.add(availableEmployees(d).get(i));
        }
        return chosen;
    }
}
