package handlers;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import producerConsumer.Consumer;
import producerConsumer.Producer;

public class EventsHandler {

    private static final Logger log = Logger.getLogger(EventsHandler.class.getName());
    private static BufferedReader reader;

    public static final String EXE_PATH = System.getProperty("user.dir") + "/src/main/java/";
    public static final String EXE_NAME = "generator-windows-amd64.exe";

    public static String getNextLine() throws IOException {
        return reader.readLine();
    }

    public void init(){
        /**
         * init method runs the executable file.
         * sets a buffered reader to read from the input stream generated from it
         * sets a producer and consumer threads and starts them
         */

        String command = EXE_PATH + EXE_NAME;
        Runtime run  = Runtime.getRuntime();
        Process proc;

        try {
            proc = run.exec(command);
            log.info("process executed");

            InputStream inputStream = proc.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            reader = new BufferedReader(inputStreamReader);

            EventsStatsHandler eventsStatsHandler = new EventsStatsHandler();

            Thread producer = new Thread(new Producer(), "Producer");
            Thread consumer = new Thread(new Consumer(), "Consumer");

            producer.start();
            consumer.start();

            producer.join();
            consumer.join();

            reader.close();
            inputStream.close();
            inputStreamReader.close();

        } catch (InterruptedException | IOException exception) {
            exception.printStackTrace();
        }
    }
}


