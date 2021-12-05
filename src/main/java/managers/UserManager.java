package managers;

import exceptions.InvalidPasswordException;
import exceptions.UserNotFoundException;
import users.User;
import events.Event;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.nio.file.Files;

/**
 * This class represents the managers.UserManager branch of our system. The managers.UserManager does not store any instance variables
 * (subject to change), and reads and writes client information to files inside the users folder.
 */
public class UserManager {
    /*
     * USER_DIRECTORY is the path for the list of user files stored in the system. For now, I have them saved under a
     * folder called "users".
     * users is a list of said files as File objects.
     */
    public final File USER_DIRECTORY = new File("src/main/java/data_files/users/");
    final String USER_DIRECTORY_PATH = "src/main/java/data_files/users/";
    private File[] users = USER_DIRECTORY.listFiles();

    /**
     * Constructs an instance of managers.UserManager.
     * No instance variables are needed as managers.UserManager does not directly store User, but rather acts as a tool
     * to retrieve and operate on Users.
     */
    public UserManager(){}

    /**
     * Creates a new account. Account creation is separate from log in, so the client must still log in with
     * their new account afterwards.
     *
     * Included comments on how code works for easier checking.
     * @param username username
     * @param password password
     * No limitations on username and password for now.
     */
    public void createUser(String username, String password) throws Exception {
        for(File f: users){
            if(f.getName().equals(username)){
                /*
                 I may invest time into writing new Exception classes later, for now I'll throw generic Exceptions
                 with descriptions.
                 */
                throw new Exception("Username already exists. Please choose another username.");
            }
        }
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
     * Effectively the "login" method for the client.
     * Throws an exception if (more to be added):
     *      no User with such username exists
     *      username-password mismatch
     *
     * Included comments on how code works for easier checking.
     * @param username username
     * @param password password
     * @return User class containing the username, password, and list of Strings containing serialized Events
     * belonging to the client.
     */
    public User getUser(String username, String password) throws Exception{
        ArrayList<String> user_events = new ArrayList<>();
        for(File f: users){
            if(f.getName().equals(username)){
                FileReader fr = new FileReader(USER_DIRECTORY_PATH + username+"/password.txt");
                BufferedReader br = new BufferedReader(fr);

                if(!br.readLine().trim().equals(password)){
                    throw new InvalidPasswordException("Password does not match.");
                }
                br.close();
                fr.close();
                return new User(username, password, new FileInputStream(USER_DIRECTORY_PATH+username+"/events.txt"));

            }
        }
        throw new UserNotFoundException("Username not found in database.");
    }

    /**
     * Updates client information in file. Will be called on client session end.
     * Writes whatever is in "_checkout.ser" into "(username)/events.txt"
     *
     * Included comments on how code works for easier checking.
     * @param u active User
     */
    public void updateUser(User u){
        try{
            u.getSerialized_events().close();
            FileInputStream checkout = new FileInputStream("src/main/java/data_files/users/_checkout.ser");
            Path user_events_path = Paths.get(USER_DIRECTORY_PATH + u.getUsername()+"/events.txt");
            Files.copy(checkout, user_events_path, StandardCopyOption.REPLACE_EXISTING);
            checkout.close();
        }
        catch(IOException io){
            io.printStackTrace();
        }
    }
}
