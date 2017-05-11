package by.nahorny.mvc.authorization;

import by.nahorny.mvc.dao.UserDAO;
import by.nahorny.mvc.entity.User;
import by.nahorny.mvc.exception.DAOException;
import by.nahorny.mvc.exception.PasswordEncryptionException;
import by.nahorny.mvc.exception.RegisterExistingUserException;
import by.nahorny.mvc.exception.UnavailableConnectionException;
import by.nahorny.mvc.pool.ConnectionPool;
import by.nahorny.mvc.resource.MessageManager;

import java.sql.Connection;

/**
 * Created by Dmitri_Nahorny on 4/14/2017.
 */
public class RegistrationLogic {

    public static User registerUser(String enterLogin, String enterPass, String enterEmail) throws RegisterExistingUserException, UnavailableConnectionException, DAOException, PasswordEncryptionException {
        ConnectionPool connectionPoolInstance = ConnectionPool.getConnectionPool();

            Connection connection = connectionPoolInstance.getConnection();
            UserDAO userDAO = new UserDAO(connection);

            String userExistingCheckResult;
            try {
                if ((userExistingCheckResult = checkExistingUser(userDAO, enterLogin, enterEmail)) == null) {
                    User regUser = new User(enterLogin, LoginLogic.convertToMD5(enterPass, enterLogin.toLowerCase()), "user", enterEmail, 0f, 0);

                    if (userDAO.create(regUser)) {
                        regUser = userDAO.findUserByLogin(regUser.getLogin());
                        regUser.setPassword(null);
                    } else {
                        regUser = null;
                    }
                    return regUser;

                } else {
                    throw new RegisterExistingUserException(userExistingCheckResult);
                }
            } finally {
                connectionPoolInstance.closeConnection(connection);
            }
    }

    private static String checkExistingUser(UserDAO userDAO, String login, String email) throws DAOException{
        if (userDAO.findUserByLogin(login) != null) {
            return MessageManager.getProperty("message.existinglogin");
        } else {
            if (userDAO.findUserByEmail(email) != null) {
                return MessageManager.getProperty("message.existingemail");
            }
        }
        return null;
    }
}
