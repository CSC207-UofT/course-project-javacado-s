package users;

import java.util.ArrayList;

/**
 This class represents a User with an account in our system.
 */

public class User {
    private final String username;
    private String password;

    /**
     Constructs instance of User, given a username and password
     * @param username the unique username of the User
     * @param password the User's account password
     */

    public User(String username, String password){
        this.username = username;
        this.password = password;
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
}
