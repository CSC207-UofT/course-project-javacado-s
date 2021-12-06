package commands;
/*
Interface for all Command classes
 */

import exceptions.EventNotFoundException;

public interface ICommand<T> {
    T execute() throws EventNotFoundException;
}
