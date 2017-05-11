package by.nahorny.mvc.authorization;

import by.nahorny.mvc.dao.UserDAO;
import by.nahorny.mvc.entity.User;
import by.nahorny.mvc.exception.DAOException;
import by.nahorny.mvc.exception.PasswordEncryptionException;
import by.nahorny.mvc.exception.UnavailableConnectionException;
import by.nahorny.mvc.pool.ConnectionPool;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;

/**
 * Created by Dmitri_Nahorny on 3/30/2017.
 */
public class LoginLogic {

    public static User checkLogin(String enterLogin, String enterPass) throws UnavailableConnectionException, DAOException, PasswordEncryptionException {

        ConnectionPool connectionPoolInstance = ConnectionPool.getConnectionPool();

        Connection connection = connectionPoolInstance.getConnection();
        UserDAO userDAO = new UserDAO(connection);
        User loginUser = userDAO.findUserByLogin(enterLogin);
        if (loginUser != null) {
            String md5EnterPass = convertToMD5(enterPass, enterLogin.toLowerCase());
            if (!loginUser.getPassword().equals(md5EnterPass)) {
                loginUser = null;
            } else {
                loginUser.setPassword(null);
            }
        }
        connectionPoolInstance.closeConnection(connection);

        return loginUser;
    }

    static String convertToMD5(String input, String salt) throws PasswordEncryptionException {
        MessageDigest digest = null;
        String md5Hash = null;
        try {

            digest = MessageDigest.getInstance("MD5");
            digest.update((input + salt).getBytes());
            md5Hash = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {
            throw new PasswordEncryptionException(e.getMessage());
        }
        return md5Hash;
    }
}