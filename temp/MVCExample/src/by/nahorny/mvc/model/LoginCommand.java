package by.nahorny.mvc.model;

import by.nahorny.mvc.authorization.LoginLogic;
import by.nahorny.mvc.entity.User;
import by.nahorny.mvc.exception.DAOException;
import by.nahorny.mvc.exception.UnavailableConnectionException;
import by.nahorny.mvc.resource.ConfigurationManager;
import by.nahorny.mvc.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Dmitri_Nahorny on 3/30/2017.
 */
public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        // извлечение из запроса логина и пароля
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);

        try {

            // проверка логина и пароля
            User currentUser;
            if ((currentUser = LoginLogic.checkLogin(login, pass)) != null) {
                HttpSession currentSession = request.getSession();
                currentSession.setAttribute("user", currentUser);
                // определение пути к main.jsp
                page = ConfigurationManager.getProperty("path.page.main");
            } else {
                request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
                page = ConfigurationManager.getProperty("path.page.login");
            }
        } catch (DAOException | UnavailableConnectionException e) {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.techissue"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}