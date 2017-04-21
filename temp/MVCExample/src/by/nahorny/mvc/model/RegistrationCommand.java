package by.nahorny.mvc.model;

import by.nahorny.mvc.authorization.RegistrationLogic;
import by.nahorny.mvc.entity.User;
import by.nahorny.mvc.exception.DAOException;
import by.nahorny.mvc.exception.RegisterExistingUserException;
import by.nahorny.mvc.exception.UnavailableConnectionException;
import by.nahorny.mvc.resource.ConfigurationManager;
import by.nahorny.mvc.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.regex.Pattern;

/**
 * Created by Dmitri_Nahorny on 4/14/2017.
 */
public class RegistrationCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_PASS_CONF = "password_conf";
    private static final String PARAM_NAME_EMAIL = "email";

    private static final String LOGIN_REG_EXP = "(\\w{3,15})";
    private static final String PASSWORD_REG_EXP = "(.{4,})";
    private static final String EMAIL_REG_EXP = "(\\w{6,})@(\\w+\\.)([a-z]{2,4})";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        String pass_conf = request.getParameter(PARAM_NAME_PASS_CONF);
        String email = request.getParameter(PARAM_NAME_EMAIL);

        StringBuilder errorMessage = new StringBuilder();
        if(this.checkFormFields(login, pass, pass_conf, email, errorMessage)) {
            try {
                User currentUser = RegistrationLogic.registerUser(login, pass, email);
                HttpSession currentSession = request.getSession();
                currentSession.setAttribute("user", currentUser);
                page = ConfigurationManager.getProperty("path.page.main");
            } catch (RegisterExistingUserException e) {
                request.setAttribute("errorRegMessage", e.getMessage());
                page = ConfigurationManager.getProperty("path.page.registration");
            } catch (DAOException | UnavailableConnectionException e) {
                request.setAttribute("errorRegMessage", MessageManager.getProperty("message.techissue"));
                page = ConfigurationManager.getProperty("path.page.registration");
            }
        } else {
            request.setAttribute("errorRegMessage", errorMessage);
            page = ConfigurationManager.getProperty("path.page.registration");
        }
        return page;
    }

    private boolean checkFormFields(String login, String password, String password_confirmation, String email, StringBuilder errorMessage) {

        boolean checkResult = false;

        if (login != null && password != null && email != null) {

            if (password.equals(password_confirmation)) {

                if (Pattern.matches(LOGIN_REG_EXP, login)) {

                    if (Pattern.matches(PASSWORD_REG_EXP, password)) {

                        if (Pattern.matches(EMAIL_REG_EXP, email)) {

                            checkResult = true;

                        } else {
                            errorMessage.append(MessageManager.getProperty("message.incorrectemail"));
                        }
                    } else {
                        errorMessage.append(MessageManager.getProperty("message.incorrectpassword"));
                    }
                } else {
                    errorMessage.append(MessageManager.getProperty("message.incorrectlogin"));
                }
            } else {
                errorMessage.append(MessageManager.getProperty("message.differentpassword"));
            }
        } else {
            errorMessage.append(MessageManager.getProperty("message.emptyfields"));
        }

        return checkResult;
    }
}
