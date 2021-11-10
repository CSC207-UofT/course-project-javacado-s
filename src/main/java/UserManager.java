import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.nio.file.Files;

/**
 * This class represents the UserManager branch of our system. The UserManager does not store any instance variables
 * (subject to change), and reads and writes client information to files inside the users folder.
 */
public class UserManager {
    /*
     * USER_DIRECTORY is the path for the list of user files stored in the system. For now, I have them saved under a
     * folder called "users".
     * users is a list of said files as File objects.
     */
    final File USER_DIRECTORY = new File("src/data/users");
    final String USER_DIRECTORY_PATH = "src/data/users/";
    private File[] users = USER_DIRECTORY.listFiles();

    /**
     * Constructs an instance of UserManager.
     * No instance variables are needed as UserManager does not directly store User, but rather acts as a tool
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
        FileWriter pw = new FileWriter(USER_DIRECTORY_PATH + username + "/password.txt");
        /*
        A new user file will contain only one line, the password, as they have no Events.
         */
        pw.write(password);
        pw.close();
        File file = new File(USER_DIRECTORY_PATH + username + "/events.txt");
        if(!file.createNewFile()){ throw new Exception("Could not create new file. Something went wrong.");}
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
            /*
            May replace with a try-catch. Probably should, in fact.
             */
            if(f.getName().equals(username)){
                /*
                BufferedReader reads from a character-input stream, whereas FileReader reads chars at a time
                so BufferedReader is both more efficient and easier to work with.
                 */
                FileReader fr = new FileReader(USER_DIRECTORY_PATH + username+"/password.txt");
                BufferedReader br = new BufferedReader(fr);
                /*
                BufferedReader moves onto the next line in the file after each call of readLine(), I trim to make
                sure newline characters don't mess up the equality. This may be an issue later on depending on
                whether we allow spaces at the beginning and end of passwords.
                 */
                if(br.readLine().trim().equals(password)){
                    String curr;
                    while((curr=br.readLine())!=null){
                        user_events.add(curr);
                    }
                }
                else{
                    throw new Exception("Password does not match.");
                }
                br.close();
                fr.close();
            }
            else{
                throw new Exception("No User found.");
            }
        }
        return new User(username, password, user_events);
    }

    /**
     * Updates client information in file. Will be called on client session end.
     * Writes whatever is in "checkout.ser" into "(username)/info.txt"
     *
     * Included comments on how code works for easier checking.
     * @param u active User
     */
    public void updateUser(User u){
        try{
            FileInputStream checkout = new FileInputStream("src/data/checkout.ser");
            Path user_events_path = Paths.get("src/data/users" + u.getUSERNAME());
            Files.copy(checkout, user_events_path, StandardCopyOption.REPLACE_EXISTING);
        }
        catch(IOException io){
            System.out.println("Something went wrong while writing to file.");
        }
    }
}
