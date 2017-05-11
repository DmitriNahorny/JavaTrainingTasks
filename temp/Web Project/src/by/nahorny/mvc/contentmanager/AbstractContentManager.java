package by.nahorny.mvc.contentmanager;

import by.nahorny.mvc.entity.BusinessEntity;
import by.nahorny.mvc.exception.DAOException;
import by.nahorny.mvc.exception.UnavailableConnectionException;

import java.util.Map;

/**
 * Created by Dmitri_Nahorny on 5/4/2017.
 */
public abstract class AbstractContentManager {
    public abstract boolean add(Map<String,String[]> paramsToAdd) throws DAOException, UnavailableConnectionException;
    public abstract boolean edit(Map<String,String[]> paramsToEdit) throws DAOException, UnavailableConnectionException;
    public abstract boolean delete(Map<String,String[]> paramsTodelete) throws DAOException, UnavailableConnectionException;
}