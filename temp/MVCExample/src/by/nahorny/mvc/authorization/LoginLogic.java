package by.nahorny.mvc.authorization;

import by.nahorny.mvc.resource.DatabaseManager;

import java.sql.*;

/**
 * Created by Dmitri_Nahorny on 3/30/2017.
 */
public class LoginLogic {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    private static final String url = DatabaseManager.getProperty("database.url");
    private static final String username = DatabaseManager.getProperty("database.username");
    private static final String password = DatabaseManager.getProperty("database.password");

    public static boolean checkLogin(String enterLogin, String enterPass) {

        boolean authorizationResult = false;

        try (Connection connection = DriverManager.getConnection(url, username, password)) {

            String databaseQuery = "SELECT login, password FROM authorization WHERE UPPER(login) = \"" + enterLogin.toUpperCase() + "\"";
            Statement databaseStatement = connection.createStatement();
            ResultSet authorizationResultSet = databaseStatement.executeQuery(databaseQuery);

            while (authorizationResultSet.next()) {
                if (authorizationResultSet.getString("password").equals(enterPass)) {
                    authorizationResult = true;
                }
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }

        return authorizationResult;
    }
}