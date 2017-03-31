package by.nahorny.mvc.model;

import by.nahorny.mvc.authorization.LoginLogic;
import by.nahorny.mvc.resource.ConfigurationManager;
import by.nahorny.mvc.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

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
// проверка логина и пароля
        if (LoginLogic.checkLogin(login, pass)) {
            request.setAttribute("user", login);
// определение пути к main.jsp
            page = ConfigurationManager.getProperty("path.page.main");
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}