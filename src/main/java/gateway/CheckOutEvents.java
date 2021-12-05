package gateway;

import events.Event;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CheckOutEvents {

    /**
     * Serializes events_list to "_checkout.ser" file.
     */
    public void checkout(ArrayList<Event> eventList){
        try {
            FileOutputStream fileOut = new FileOutputStream("src/main/java/data_files/users/_checkout.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(eventList);
            out.close();
            fileOut.close();
        }
        catch(IOException i){
            i.printStackTrace();
        }
    }

}
