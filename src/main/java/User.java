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

    public FileInputStream getSerialized_events() {
        return serialized_events;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof User u)){
            return false;
        }
        return this.getUSERNAME().equals(u.getUSERNAME())
                && this.getPassword().equals(u.getPassword())
                && this.getSerialized_events().equals(u.getSerialized_events());
    }
}
