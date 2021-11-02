import java.io.*;
import java.util.ArrayList;

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
    final File USER_DIRECTORY = new File("users");
    private File[] users = USER_DIRECTORY.listFiles();

    /**
     * Constructs an instance of UserManager.
     * No instance variables are needed as UserManager does not directly store User, but rather acts as a tool
     * to retrieve and operate on User.
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
            if(f.getName().equals(username+".txt")){
                /*
                 I may invest time into writing new Exception classes later, for now I'll throw generic Exceptions
                 with descriptions.
                 */
                throw new Exception("Username already exists. Please choose another username.");
            }
        }
        FileWriter new_user = new FileWriter("users/" + username + ".txt");
        /*
        A new user file will contain only one line, the password, as they have no Events.
         */
        new_user.write(password);
        new_user.close();
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
            if(f.getName().equals(username+".txt")){
                /*
                BufferedReader reads from a character-input stream, whereas FileReader reads chars at a time
                so BufferedReader is both more efficient and easier to work with.
                 */
                FileReader fr = new FileReader(username+".txt");
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
     *
     * Included comments on how code works for easier checking.
     * @param u active User
     * @param updated_events List of Serialized Events that will be written into the file storing client information.
     */
    public void updateUser(User u, ArrayList<String> updated_events){
        try{
            FileWriter fw = new FileWriter(u.getUSERNAME()+".txt");
            fw.write(u.getPassword()+"\n");
            for(String s:updated_events){
                fw.write(s+"\n");
            }
            fw.close();
        }
        catch(IOException io){
            System.out.println("Something went wrong with writing to file.");
        }
    }
}
