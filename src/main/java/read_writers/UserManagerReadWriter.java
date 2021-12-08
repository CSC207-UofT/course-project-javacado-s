package read_writers;

import events.Event;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class performs text/database parsing and updating to be used in class UserManager.
 */
public class UserManagerReadWriter implements IUserReadWriter<HashMap<String,String>, String>{
    /*
     * USER_DIRECTORY is the path for the list of user files stored in the system.
     * users is a list of said files as File objects.
     */
    public final File USER_DIRECTORY = new File("src/main/java/data_files/users/");
    final String USER_DIRECTORY_PATH = "src/main/java/data_files/users/";
    private File[] users = USER_DIRECTORY.listFiles();

    /**
     * Reads through the users directory and collects all usernames with their corresponding passwords.
     * @return A hashmap mapping existing usernames in the system to their password.
     */
    @Override
    public HashMap<String,String> read() throws Exception {
        HashMap<String,String> userList = new HashMap<>();

        for (int i = 0; i < users.length - 1 ; i++) {
            String username = users[i].getName();
            FileReader fr = new FileReader(USER_DIRECTORY_PATH + username + "/password.txt");
            BufferedReader br = new BufferedReader(fr);
            String password = br.readLine().trim();
            userList.put(username, password);
        }
        return userList;
    }

    /**
     * Create a new folder for the new user named using their username, with a file containing their password and
     * another file of a serialized empty event list.
     */
    @Override
    public void add(String username, String password) throws IOException {
        File folder = new File(USER_DIRECTORY_PATH + username);
        if(!folder.mkdir()){
            System.out.print("Something went wrong with creating a new folder.");
        }
        FileWriter pw = new FileWriter(USER_DIRECTORY_PATH + username + "/password.txt");
        /*
        A new user file will contain only one line, the password, as they have no Events.
         */
        pw.write(password);
        pw.flush();
        pw.close();

        ArrayList<Event> empty_events = new ArrayList<>();
        FileOutputStream fos = new FileOutputStream(USER_DIRECTORY_PATH + username + "/events.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(empty_events);
        oos.close();
        fos.close();
        this.users = USER_DIRECTORY.listFiles();
        /*
         * the list of users has to be updated again after creating a new account; login is separate from account
         * creation.
         */
    }

    /**
     * Updates/serializes the new list of events of a user.
     * (Writes whatever is in "_checkout.ser" into "(username)/events.txt")
     * @param username the username of the account that needs to be updated.
     */
    @Override
    public void update(String username) {
        try{
            FileInputStream checkout = new FileInputStream("src/main/java/data_files/users/_checkout.ser");
            Path user_events_path = Paths.get(USER_DIRECTORY_PATH + username +"/events.txt");
            Files.copy(checkout, user_events_path, StandardCopyOption.REPLACE_EXISTING);
            checkout.close();

        }
        catch(IOException io){
            io.printStackTrace();
        }
    }
}
