package read_writers;

import java.io.IOException;

/**
 * Interface for ReadWriter classes.
 */

public interface IReadWriter<T> {
    void update(T input) throws IOException;
}
