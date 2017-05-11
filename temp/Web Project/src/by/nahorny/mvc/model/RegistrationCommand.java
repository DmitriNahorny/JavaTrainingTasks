package by.nahorny.mvc.model;

import by.nahorny.mvc.authorization.FieldValidator;
import by.nahorny.mvc.authorization.RegistrationLogic;
import by.nahorny.mvc.entity.User;
import by.nahorny.mvc.exception.DAOException;
import by.nahorny.mvc.exception.PasswordEncryptionException;
import by.nahorny.mvc.exception.RegisterExistingUserException;
import by.nahorny.mvc.exception.UnavailableConnectionException;
import by.nahorny.mvc.resource.ConfigurationManager;
import by.nahorny.mvc.resource.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Dmitri_Nahorny on 4/14/2017.
 */
public class RegistrationCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_PASS_CONF = "password_conf";
    private static final String PARAM_NAME_EMAIL = "email";

    private static final Logger LOGGER = LogManager.getLogger(RegistrationCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        String passConf = request.getParameter(PARAM_NAME_PASS_CONF);
        String email = request.getParameter(PARAM_NAME_EMAIL);

        StringBuilder errorMessage = new StringBuilder();
        if(FieldValidator.checkFormFields(login, pass, passConf, email, errorMessage)) {
            try {
                User currentUser = RegistrationLogic.registerUser(login, pass, email);
                HttpSession currentSession = request.getSession();
                currentSession.setAttribute("user", currentUser);
                page = ConfigurationManager.getProperty("path.page.main");
            } catch (RegisterExistingUserException e) {
                request.setAttribute("errorRegMessage", e.getMessage());
                page = ConfigurationManager.getProperty("path.page.registration");
            } catch (DAOException | UnavailableConnectionException | PasswordEncryptionException e) {
                LOGGER.log(Level.ERROR, e.getMessage());
                request.setAttribute("errorRegMessage", MessageManager.getProperty("message.techissue"));
                page = ConfigurationManager.getProperty("path.page.registration");
            }
        } else {
            request.setAttribute("errorRegMessage", errorMessage);
            page = ConfigurationManager.getProperty("path.page.registration");
        }
        return page;
    }
}
