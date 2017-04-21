package by.nahorny.mvc.dao;

import by.nahorny.mvc.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import by.nahorny.mvc.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Dmitri_Nahorny on 4/12/2017.
 */
public class UserDAO extends AbstractDAO <Integer, User>{

    static private final Logger LOGGER = LogManager.getLogger(UserDAO.class);

    private final String SQL_SELECT_USER_BY_LOGIN = "SELECT user_id, login, password, role, email, balance, discount FROM authorization WHERE UPPER(login) = ?";
    private final String SQL_SELECT_USER_BY_EMAIL = "SELECT user_id, login, password, role, email, balance, discount FROM authorization WHERE UPPER(email) = ?";
    private final String SQL_CREATE_USER = "INSERT INTO authorization (login, password, role, email, balance, discount) VALUES (?, ?, ?, ?, ?, ?)";

    public UserDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public User findEntityById(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(User entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(User entity) throws DAOException {
        PreparedStatement createStatement = null;
        User regUser = entity;
        Boolean isUserCreated = false;

        try {
            createStatement = this.connection.prepareStatement(SQL_CREATE_USER);
            createStatement.setString(1, regUser.getLogin());
            createStatement.setString(2, regUser.getPassword());
            createStatement.setString(3, regUser.getRole());
            createStatement.setString(4, regUser.getEmail());
            createStatement.setFloat(5, regUser.getCurrentBalance());
            createStatement.setInt(6, regUser.getDiscount());
            if(createStatement.executeUpdate() > 0) {
                isUserCreated = true;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new DAOException(e.getMessage());
        } finally {
            this.closeStatement(createStatement);
        }

        return isUserCreated;
    }

    @Override
    public User update(User entity) {
        throw new UnsupportedOperationException();
    }

    public User findUserByLogin(String login) throws DAOException {

        PreparedStatement selectStatement = null;
        User loginUser = null;
        try {
            selectStatement = this.connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
            selectStatement.setString(1, login.toUpperCase());
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String userLogin = resultSet.getString("login");
                String userPassword = resultSet.getString("password");
                String userRole = resultSet.getString("role");
                String userEmail = resultSet.getString("email");
                float userBalance = resultSet.getFloat("balance");
                int userDiscount = resultSet.getInt("discount");

                loginUser = new User(userId, userLogin, userPassword, userRole, userEmail, userBalance, userDiscount);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new DAOException(e.getMessage());
        } finally {
            this.closeStatement(selectStatement);
        }

        return loginUser;
    }

    public User findUserByEmail(String email) throws DAOException {

        PreparedStatement selectStatement = null;
        User loginUser = null;
        try {
            selectStatement = this.connection.prepareStatement(SQL_SELECT_USER_BY_EMAIL);
            selectStatement.setString(1, email.toUpperCase());
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String userLogin = resultSet.getString("login");
                String userPassword = resultSet.getString("password");
                String userRole = resultSet.getString("role");
                String userEmail = resultSet.getString("email");
                float userBalance = resultSet.getFloat("balance");
                int userDiscount = resultSet.getInt("discount");

                loginUser = new User(userId, userLogin, userPassword, userRole, userEmail, userBalance, userDiscount);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new DAOException(e.getMessage());
        } finally {
            this.closeStatement(selectStatement);
        }

        return loginUser;
    }
}
