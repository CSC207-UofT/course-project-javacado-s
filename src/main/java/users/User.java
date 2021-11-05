package users;

/**
 This class represents a Users.User with an account in our system.
 */

public class User {
    private final String username;
    private String password;

    /**
     Constructs instance of Users.User, given a username and password
     * @param username the unique username of the Users.User
     * @param password the Users.User's account password
     */

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    /**
     * Users.User getter for Users.User's username.
     * @return String. Return the username of the Users.User.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Users.User getter for Users.User's password.
     * @return String. Return the password for the Users.User's account.
     */
    public String getPassword() { return password; }

    /**
     * Change the Users.User's password.
     */
    public void changePassword(String newPassword) {
        password = newPassword;
    }
}
