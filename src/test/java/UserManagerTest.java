import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class UserManagerTest {
    UserManager u = new UserManager();

    @After
    public void tearDown(){
        for(File f:u.USER_DIRECTORY.listFiles()){
            if(f.getName().contains("test")){
                f.delete();
            }
        }
    }

    @Test(timeout=50)
    public void testInitializer(){
        File[] expected = {new File("src/data/users/jane_doe.txt"), new File("src/data/users/john_doe.txt")};
        System.out.print(Arrays.toString(expected));
        System.out.print(Arrays.toString(u.USER_DIRECTORY.listFiles()));
        assertEquals(expected,u.USER_DIRECTORY.listFiles());
    }

    @Test(timeout = 50)
    public void createUserNew() throws Exception {
        File[] files = u.USER_DIRECTORY.listFiles();
        File[] expected = new File[files.length+1];
        int count=0;
        for(File f:files){
            expected[count]=f;
            count+=1;
        }
        expected[expected.length-1]=new File("src/data/users/test_a.txt");
        u.createUser("test_a", "12345678");
        assertEquals(expected, u.USER_DIRECTORY.listFiles());
    }

    @Test(timeout = 50)
    public void createUserExisting() throws Exception {
        Exception exception = assertThrows(Exception.class, () -> {
            u.createUser("jane_doe", "qwertyuiop");
        });
        String expectedMessage = "Username already exists. Please choose another username.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}
