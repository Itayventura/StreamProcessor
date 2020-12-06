package handlers;

import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueHandler {

    private static QueueHandler instance;
    private static ConcurrentLinkedQueue<String> queue;

    private QueueHandler(){
        queue = new ConcurrentLinkedQueue<>();
    }

    public static void enqueue(String item){
        if (instance == null){
            instance = new QueueHandler();
        }
        queue.add(item);
    }

    public static String dequeue(){
        if (instance == null){
            instance = new QueueHandler();
        }
        return queue.poll();
    }

    public static boolean isEmpty(){
        if (instance == null){
            instance = new QueueHandler();
        }
        return queue.isEmpty();
    }
}
