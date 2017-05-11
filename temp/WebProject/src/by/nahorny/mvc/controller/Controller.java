package by.nahorny.mvc.controller;

import by.nahorny.mvc.model.ActionCommand;
import by.nahorny.mvc.model.ActionFactory;
import by.nahorny.mvc.resource.ConfigurationManager;
import by.nahorny.mvc.resource.MessageManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Created by Dmitri_Nahorny on 3/30/2017.
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {

        String page = null;
        /*command determination from jsp call*/
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        /*defined command execute method call*/
        page = command.execute(request);
        /*the method returns url to necessary page as String*/
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            /*necessary jsp page call*/
            dispatcher.forward(request, response);
        } else {
            /*error page call*/
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}