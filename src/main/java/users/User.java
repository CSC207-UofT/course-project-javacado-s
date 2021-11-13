package users;

import events.Event;

import java.io.FileInputStream;
import java.util.ArrayList;

/**
 This class represents a User with an account in our system.
 */

public class User {
    private final String username;
    private String password;
    private FileInputStream serialized_events;
    /**
     Constructs instance of User, given a username and password
     * @param username the unique username of the User
     * @param password the User's account password
     * @param serialized_events FileInputStream of User's existing Events in serialized form
     */

    public User(String username, String password, FileInputStream serialized_events){
        this.username = username;
        this.password = password;
        this.serialized_events = serialized_events;
    }

    /**
     * User getter for User's username.
     * @return String. Return the username of the User.
     */
    public String getUsername() {
        return username;
    }

    /**
     * User getter for User's password.
     * @return String. Return the password for the User's account.
     */
    public String getPassword() { return password; }

    /**
     * Change the User's password.
     */
    public void changePassword(String newPassword) {
        password = newPassword;
    }

    public FileInputStream getSerialized_events() {
        return serialized_events;
    }

    @Override
    public boolean equals(Object o){
        if (this.getClass() != o.getClass()){
            return false;
        }
        User u = (User)o;
        return this.getUsername().equals(u.getUsername())
                && this.getPassword().equals(u.getPassword())
                && this.getSerialized_events().equals(u.getSerialized_events());
    }
}
