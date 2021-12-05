package read_writers;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Interface for ReadWriter classes.
 */

public interface IReadWriter<T1, T2, T3> {
    T1 read(T2 input) throws IOException, ClassNotFoundException;
    void update(T3 input) throws IOException;
}
