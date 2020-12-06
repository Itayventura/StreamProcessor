package producerConsumer;

import handlers.EventsHandler;

import handlers.QueueHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer implements Runnable
{
    private static final Logger log = Logger.getLogger(Producer.class.getName());

    @Override
    public void run() {
        while(true){
            produce();
        }
    }

    private void produce()
    {
            String line;
            try {
                if ((line = EventsHandler.getNextLine()) != null) {
                    log.info("Producer produced - " + line);
                    if (!isValidJson(line)){
                        log.log(Level.SEVERE, line + " is not a valid jason");
                    } else if(!isValidStructure(line)){
                        log.log(Level.SEVERE, line + " has missing structure");
                    } else{
                        QueueHandler.enqueue(line);
                    }
                } else{
                    log.log(Level.SEVERE, "could not read from reader. received null");
                }
            } catch (IOException e) {
                log.log(Level.SEVERE, "IOException when reading from buffered reader", e);
            }
    }


    public boolean isValidJson(String text) {
        try {
            new JSONObject(text);
        } catch (JSONException ex) {
            try {
                new JSONArray(text);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidStructure(String text){
        return text.contains("timestamp") &&
                text.contains("data") &&
                text.contains("event_type");
    }

}
