package commands;
/*
Interface for all Command classes
 */

public interface ICommand<T> {
    T execute() throws Exception;
}
