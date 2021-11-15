import events.Event;
import meals.Breakfast;
import managers.UserManager;
import users.User;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class UserManagerTest {
    UserManager u = new UserManager();

    @Before
    public void setUp(){
    }

    @After
    public void tearDown(){
        for(File f: Objects.requireNonNull(u.USER_DIRECTORY.listFiles())){
            if(f.getName().contains("test")){
                for(File c: Objects.requireNonNull(f.listFiles())){
                    c.delete();
                }
                f.delete();
            }
        }
    }

    @Test(timeout=50)
    public void testInitializer(){
        File expected = new File("src/main/java/data_files/users");
        assertEquals(expected.listFiles(),u.USER_DIRECTORY.listFiles());
    }

    @Test(timeout = 50)
    public void testCreateUserNew() throws Exception {
        u.createUser("testNew", "testpassword");
        File test_file = new File("src/main/java/data_files/users/testNew");
        File[] expected_directory = {new File("src/main/java/data_files/users/jane_doe"),
                new File("src/main/java/data_files/users/john_doe"),
                new File("src/main/java/data_files/users/testNew"),
                new File("src/main/java/data_files/users/_checkout.ser")};
        assertEquals(expected_directory, u.USER_DIRECTORY.listFiles());
        File[] expected_contents = {new File("src/main/java/data_files/users/testNew/events.txt"),
                new File("src/main/java/data_files/users/testNew/password.txt")};
        assertEquals(expected_contents, test_file.listFiles());
        FileReader fr1 = new FileReader("src/main/java/data_files/users/testNew/password.txt");
        BufferedReader br1 = new BufferedReader(fr1);
        assertEquals("testpassword", br1.readLine());
        fr1.close();
        br1.close();
        FileReader fr2 = new FileReader("src/main/java/data_files/users/testNew/events.txt");
        BufferedReader br2 = new BufferedReader(fr2);
        //assertNull(br2.readLine());
        fr2.close();
        br2.close();
    }

    @Test(timeout = 50)
    public void testCreateUserExisting(){
        Exception exception = assertThrows(Exception.class, () -> u.createUser("jane_doe", "qwertyuiop"));
        String expectedMessage = "Username already exists. Please choose another username.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test(timeout = 50)
    public void testGetUserExists(){
        String username = "jane_doe";
        String password = "qwertyuiop";
        User expected = new User(username, password, null);
        try {
            assertEquals(expected, u.getUser(username, password));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(timeout = 50)
    public void testGetUserNull(){
        String username = "userdoesnotexist";
        String password = "userdoesnotexist";
        Exception exception = assertThrows(Exception.class, () -> u.getUser(username, password));
        String expectedMessage = "No user found.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @SuppressWarnings("unchecked")
    @Test(timeout = 200)
    public void testUpdateUser() throws Exception {
        ArrayList<Event> test = new ArrayList<>();
        Event test_event = new Event(0, "test_string",
                new Date(),
                "test_location",
                0,
                new Breakfast("Breakfast"));
        test.add(test_event);
        FileOutputStream a = new FileOutputStream("src/main/java/data_files/users/_checkout.ser");
        ObjectOutputStream o = new ObjectOutputStream(a);
        o.writeObject(test);
        o.flush();
        o.close();
        a.close();
        u.updateUser(u.getUser("john_doe", "12345678"));
        FileInputStream i = new FileInputStream("src/main/java/data_files/users/_checkout.ser");
        ObjectInputStream s = new ObjectInputStream(i);
        ArrayList<Event> expected = (ArrayList<Event>) s.readObject();
        assertArrayEquals(expected.toArray(), test.toArray());
        FileWriter fw = new FileWriter("src/main/java/data_files/users/john_doe/events.txt");
        fw.write("");
        fw.close();
    }
}
