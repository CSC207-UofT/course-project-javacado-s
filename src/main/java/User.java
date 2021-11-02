import java.util.ArrayList;

public class User {
    private final String USERNAME;
    private String password;
    private ArrayList<String> user_events;

    public User(String username, String password, ArrayList<String> serialized_events){
        this.USERNAME = username;
        this.password = password;
        this.user_events = serialized_events;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public String getPassword() {
        return password;
    }
}
