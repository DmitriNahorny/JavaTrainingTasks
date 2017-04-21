package by.nahorny.mvc.model;

import by.nahorny.mvc.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Dmitri_Nahorny on 4/4/2017.
 */
public class SetLocaleCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {

        HttpSession currentSession = request.getSession();
        currentSession.setAttribute("userLocale", request.getParameter("selectedLocale"));

        String page = ConfigurationManager.getProperty("path.page.main");
        return page;
    }
}
