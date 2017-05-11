package by.nahorny.mvc.listener;

import by.nahorny.mvc.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * Created by Dmitri_Nahorny on 4/3/2017.
 */
@WebListener
public class CustomContextListener implements ServletContextListener {

    private final  static Logger LOGGER = LogManager.getLogger(CustomContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            LOGGER.log(Level.FATAL, e.getMessage());
            throw new RuntimeException(e.getCause());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

        ConnectionPool connectionPoolInstance = ConnectionPool.getConnectionPool();
        connectionPoolInstance.releaseConnections();

        try {
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.FATAL, e.getMessage());
        }
    }
}
