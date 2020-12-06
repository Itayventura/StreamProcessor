package app;

import controller.StatisticsServlet;
import handlers.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import controller.RecentStatisticsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class App {

    private static Logger log = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        log.info("starting app");

        Server server = new Server(8080);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);

        handler.addServletWithMapping(StatisticsServlet.class, "/stat");
        handler.addServletWithMapping(RecentStatisticsServlet.class, "/recent");

        try {
            server.start();
            log.info("starting server");
        } catch (Exception e) {
            log.log(Level.SEVERE, "failed to start server", e);
        }

        EventsHandler eventsHandler = new EventsHandler();
        eventsHandler.init();

        try {
            server.join();
        } catch (InterruptedException e) {
            log.log(Level.SEVERE, "failed to join server", e);
        }



    }

}
