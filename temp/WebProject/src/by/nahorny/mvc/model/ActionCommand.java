package by.nahorny.mvc.model;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dmitri_Nahorny on 3/30/2017.
 */
public interface ActionCommand {
    String execute(HttpServletRequest request);
}
