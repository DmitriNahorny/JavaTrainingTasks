package by.nahorny.mvc.contenthandler;

import by.nahorny.mvc.entity.ContentEntityHandler;
import by.nahorny.mvc.exception.DAOException;
import by.nahorny.mvc.exception.UnavailableConnectionException;
import by.nahorny.mvc.pool.ConnectionPool;

import java.util.List;

/**
 * Created by Dmitri_Nahorny on 4/20/2017.
 */
public abstract class LibraryList {
    protected ConnectionPool connectionPoolInstance = ConnectionPool.getConnectionPool();
    public abstract List<ContentEntityHandler> getEntityList() throws DAOException, UnavailableConnectionException;
}
