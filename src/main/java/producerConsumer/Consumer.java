package producerConsumer;

import handlers.EventsStatsHandler;
import handlers.QueueHandler;

import java.util.logging.Logger;

public class Consumer implements Runnable{

    private static final Logger log = Logger.getLogger(Consumer.class.getName());

    @Override
    public void run() {
        while(true){
            consume();
        }

    }

    public void consume()
    {
            if (!QueueHandler.isEmpty()) {
                String line = QueueHandler.dequeue();
                log.info("Consumer consumed - " + line);
                EventsStatsHandler.addEvent(line);
            }
    }
}
