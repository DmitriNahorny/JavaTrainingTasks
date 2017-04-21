package by.nahorny.mvc.pool;

import by.nahorny.mvc.dao.AbstractDAO;
import by.nahorny.mvc.exception.UnavailableConnectionException;
import by.nahorny.mvc.resource.DatabaseManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Dmitri_Nahorny on 4/3/2017.
 */
public class ConnectionPool {
    private static ConnectionPool instance;
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);
    private static Lock locking = new ReentrantLock();

    private final int POOL_SIZE = 10;
    private BlockingQueue<Connection> connectionQueue;

    private String url;
    private String username;
    private String password;

    static private final Logger LOGGER = LogManager.getLogger(AbstractDAO.class);

    private ConnectionPool(){

        try {
            url = DatabaseManager.getProperty("database.url");
            username = DatabaseManager.getProperty("database.username");
            password = DatabaseManager.getProperty("database.password");
        }
        catch (MissingResourceException e) {
            LOGGER.log(Level.FATAL, e.getMessage());
            throw new RuntimeException(e.getCause());
        }

        connectionQueue = new ArrayBlockingQueue<Connection>(POOL_SIZE);
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                connectionQueue.offer(connection);
            } catch (SQLException e) {
                LOGGER.log(Level.FATAL, e.getMessage());
                throw new RuntimeException(e.getCause());
            }
        }
    }

    public static ConnectionPool getConnectionPool() {
        if (!instanceCreated.get()) {
            locking.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    instanceCreated.getAndSet(true);
                }
            }
            finally {
                locking.unlock();
            }
        }
        return instance;
    }

    public Connection getConnection() throws UnavailableConnectionException {
        Connection connection = null;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new UnavailableConnectionException();
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        connectionQueue.offer(connection);
    }

    public void releaseConnections() {
        Connection queuedConnection = null;
        for(int i = 0; i < POOL_SIZE; i++) {
            try {
                queuedConnection = connectionQueue.take();
                queuedConnection.close();
            } catch (SQLException | InterruptedException e) {
                LOGGER.log(Level.ERROR, e.getMessage());
            }
        }
    }
}
