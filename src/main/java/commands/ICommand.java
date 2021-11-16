package commands;
/*
Interface for all Command classes
 */

import exceptions.EventNotFoundError;

public interface ICommand<T> {
    T execute() throws EventNotFoundError;
}
