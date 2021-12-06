package read_writers;

import events.Event;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class performs text/database parsing and updating to be used in class EventManager.
 */

public class EventManagerReadWriter implements IEventReadWriter<ArrayList<Event>, FileInputStream, ArrayList<Event>> {

    /**
     * Reads a file of serialized events and returns a list of events.
     */
    @Override
    public ArrayList<Event> read(FileInputStream eventsFile) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(eventsFile);
        return (ArrayList<Event>) in.readObject();
    }

    /**
     * Serializes a list of events to a checkout file.
     */
    @Override
    public void update(ArrayList<Event> events) {
        try {
            FileOutputStream fileOut = new FileOutputStream("src/main/java/data_files/users/_checkout.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(events);
            out.close();
            fileOut.close();
        }
        catch(IOException i){
            i.printStackTrace();
        }
    }
}
