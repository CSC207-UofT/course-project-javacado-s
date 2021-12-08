package read_writers;

import employees.Employee;
import front_end.Tuple;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * This class performs text/database parsing and updating to be used in class EmployeeManager.
 */

public class EmployeeManagerReadWriter implements
        IEmployeeReadWriter<HashMap<Integer, Tuple<String, ArrayList<GregorianCalendar>>>, ArrayList<Employee>>{

    public HashMap<Integer, Tuple<String, ArrayList<GregorianCalendar>>> read(File input) {
        return new HashMap<>();
    }

    /**
     * Reads the employees.txt file and parse contents for EmployeeManager to initialize employees.
     * @return Hashmap containing information on an employee's id, name, and unavailable dates
     */
    @Override
    public HashMap<Integer, Tuple<String, ArrayList<GregorianCalendar>>> read() {
        File employeesFile = new File("src/main/java/data_files/employees.txt");
        try {
            FileReader fr = new FileReader(employeesFile);
            BufferedReader br = new BufferedReader(fr);
            String line;

            HashMap<Integer, Tuple<String, ArrayList<GregorianCalendar>>> fileInfo = new HashMap<>();

            while ((line = br.readLine()) != null) {
                String name = line.substring(line.indexOf(",") + 1, line.indexOf("|")).trim();
                int id = Integer.parseInt(line.substring(0, line.indexOf(",")).trim());
                String rawDates = line.substring(line.indexOf("|"));
                // Date formatting: |{yyyy}[mm](dd)|
                ArrayList<GregorianCalendar> dates = new ArrayList<>();
                while(rawDates.indexOf("|")!=rawDates.lastIndexOf("|")){
                    int y = Integer.parseInt(line.substring(line.indexOf("{")+1,line.indexOf("}")));
                    int m = Integer.parseInt(line.substring(line.indexOf("[")+1,line.indexOf("]")));
                    int d = Integer.parseInt(line.substring(line.indexOf("(")+1,line.indexOf(")")));
                    GregorianCalendar date = new GregorianCalendar();
                    date.set(Calendar.YEAR, y);
                    date.set(Calendar.MONTH, m-1);
                    date.set(Calendar.DAY_OF_MONTH, d-1);
                    date.set(Calendar.HOUR, 12);
                    date.set(Calendar.MINUTE, 0);
                    date.set(Calendar.SECOND, 0);
                    dates.add(date);
                    rawDates = rawDates.substring(1);
                }
                Tuple<String, ArrayList<GregorianCalendar>> employeeInfo = new Tuple<>(name, dates);
                fileInfo.put(id, employeeInfo);
            }
            br.close();
            fr.close();
            return fileInfo;
        }

        catch(FileNotFoundException e){
            System.out.println("employees.txt not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Updates the employees.txt file with new (un)availabilities of employees.
     * @param eList A list of employees.
     */
    @Override
    public void update(ArrayList<Employee> eList) throws IOException {
        FileWriter fw = new FileWriter("src/main/java/data_files/employees.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        for(Employee e: eList){
            //id, name|{yyyy}[mm](dd)|{yyyy}[mm](dd)|...|
            String line = e.getid()  + ", " + e.getName() + "|";
            for(GregorianCalendar d: e.getUnavailableDates()){
                line = line.concat("{"+d.get(Calendar.YEAR)+"}");
                line = line.concat("["+ (d.get(Calendar.MONTH) + 1)+"]");
                line = line.concat("("+d.get(Calendar.DAY_OF_MONTH)+")|");
            }
            line = line.concat("\r\n");
            bw.write(line);
        }
        bw.close();
        fw.close();
    }
}
