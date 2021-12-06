package read_writers;

public interface IEmployeeReadWriter<T1, T2> extends IReadWriter<T2>{
    T1 read();
}
