package by.nahorny.mvc.model;

import by.nahorny.mvc.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dmitri_Nahorny on 3/30/2017.
 */
public class EmptyCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        //go to login page in case of wrong command attribute
        String page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
}
