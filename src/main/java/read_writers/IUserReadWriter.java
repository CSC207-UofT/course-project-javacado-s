package read_writers;

import java.io.IOException;

public interface IUserReadWriter<T1, T2> extends IReadWriter<T2>{
    T1 read() throws Exception;
    void add(String username, String password) throws IOException;
}
