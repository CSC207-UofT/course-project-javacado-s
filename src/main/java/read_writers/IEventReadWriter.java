package read_writers;

import java.io.IOException;

public interface IEventReadWriter<T1, T2, T3> extends IReadWriter<T3>{
    T1 read(T2 input) throws IOException, ClassNotFoundException;
}
