package controller;

import handlers.EventsStatsHandler;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/recent"}, loadOnStartup = 1)
public class RecentStatisticsServlet extends HttpServlet
{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException
    {
        response.getOutputStream().print(EventsStatsHandler.getRecentStats());
    }
}
