import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class User {
    private final String USERNAME;
    private String password;
    private FileInputStream serialized_events;

    public User(String username, String password, FileInputStream serialized_events){
        this.USERNAME = username;
        this.password = password;
        this.serialized_events = serialized_events;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public String getPassword() {
        return password;
    }
}
