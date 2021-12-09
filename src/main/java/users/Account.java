package users;

import java.io.FileInputStream;

public interface Account {

    String getUsername();

    String getPassword();

    FileInputStream getSerialized_events();

}
