import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserManager {
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
                throw new Exception("Username already exists. Please choose another username.");
            }
        }
        FileWriter new_user = new FileWriter("users/" + username + ".txt");
        new_user.write(password);
        new_user.close();
        this.users = USER_DIRECTORY.listFiles();  /*the list of users has to be updated again after creating a user;
                                                   login is separate from account creation*/
    }

    /**
     * Effectively the "login" method for the client.
     * Throws an exception if:
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
            if(f.getName().equals(username+".txt")){
                BufferedReader br = new BufferedReader(new FileReader(username+".txt"));
                if(br.readLine().trim().equals(password)){
                    String curr;
                    while((curr=br.readLine())!=null){
                        user_events.add(curr);
                    }
                }
                else{
                    throw new Exception("Password does not match.");
                }
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
