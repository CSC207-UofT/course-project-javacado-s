package read_writers;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Interface for ReadWriter classes.
 */

public interface IReadWriter<T> {
    void update(T input) throws IOException;
}
