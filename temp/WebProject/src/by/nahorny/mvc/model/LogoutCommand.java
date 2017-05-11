package by.nahorny.mvc.model;

import by.nahorny.mvc.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dmitri_Nahorny on 3/30/2017.
 */
public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.index");
        //session destroy leads to user logout
        request.getSession().invalidate();
        return page;
    }
}
