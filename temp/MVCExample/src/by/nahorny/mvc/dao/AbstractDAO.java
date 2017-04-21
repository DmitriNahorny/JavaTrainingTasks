package by.nahorny.mvc.dao;

import by.nahorny.mvc.entity.BusinessEntity;
import by.nahorny.mvc.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Dmitri_Nahorny on 4/12/2017.
 */
public abstract class AbstractDAO <K, T extends BusinessEntity> {

    static private final Logger LOGGER = LogManager.getLogger(AbstractDAO.class);

    protected Connection connection;
    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }
    public abstract List<T> findAll()  throws DAOException;
    public abstract T findEntityById(K id)  throws DAOException ;
    public abstract boolean delete(K id)  throws DAOException ;
    public abstract boolean delete(T entity)  throws DAOException ;
    public abstract boolean create(T entity)  throws DAOException ;
    public abstract T update(T entity)  throws DAOException ;
    public void closeStatement(Statement statementToClose) {
        try {
            if (statementToClose != null) {
                statementToClose.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }
    }
}