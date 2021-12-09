package managers;

import exceptions.InvalidPasswordException;
import exceptions.UserNotFoundException;
import read_writers.UserManagerReadWriter;
import users.User;

import java.io.*;
import java.util.HashMap;

/**
 * This class represents the managers.UserManager branch of our system. The managers.UserManager does not store any instance variables
 * (subject to change), and reads and writes client information to files inside the users folder.
 */
public class UserManager {
    final String USER_DIRECTORY_PATH = "src/main/java/data_files/users/";
    private final UserManagerReadWriter RW;

    /**
     * Constructs an instance of managers.UserManager.
     * No instance variables are needed as managers.UserManager does not directly store User, but rather acts as a tool
     * to retrieve and operate on Users.
     */
    public UserManager() {
        this.RW = new UserManagerReadWriter();
    }

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
        HashMap<String, String> userList = RW.read();

        for(String user: userList.keySet()){
            if(user.equals(username)){
                throw new Exception("Username already exists. Please choose another username.");
            }
        }
        RW.add(username, password);
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
        HashMap<String, String> userList = RW.read();

        for (String user: userList.keySet()) {
            if (user.equals(username)){
                if(!userList.get(user).equals(password)){
                    throw new InvalidPasswordException("Password does not match.");
                }
                return new User(username, password, new FileInputStream(USER_DIRECTORY_PATH
                        + username + "/events.txt"));
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
        RW.update(u.getUsername());
    }
}
