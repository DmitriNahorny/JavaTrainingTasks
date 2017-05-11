package by.nahorny.mvc.model;

import by.nahorny.mvc.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dmitri_Nahorny on 3/30/2017.
 */
public class ActionFactory {
    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();
        //get command name from request attribute
        String action = request.getParameter("command");
        if (action == null || action.isEmpty()) {
            //check for empty command
            return current;
        }
        //create appropriate command instances
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}
