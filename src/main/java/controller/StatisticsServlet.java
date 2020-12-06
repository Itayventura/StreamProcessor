package controller;

import handlers.EventsStatsHandler;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/stat"}, loadOnStartup = 1)
public class StatisticsServlet extends HttpServlet
{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException
    {
      response.getOutputStream().print(EventsStatsHandler.getStats());
    }
}
