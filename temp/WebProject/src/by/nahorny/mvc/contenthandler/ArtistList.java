package by.nahorny.mvc.contenthandler;

import by.nahorny.mvc.dao.ArtistDAO;
import by.nahorny.mvc.entity.ContentEntityHandler;
import by.nahorny.mvc.exception.DAOException;
import by.nahorny.mvc.exception.UnavailableConnectionException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitri_Nahorny on 4/19/2017.
 */
public class ArtistList extends LibraryList {

    @Override
    public List<ContentEntityHandler> getEntityList(String idParameter) throws DAOException, UnavailableConnectionException {

        List<ContentEntityHandler> artistList = new ArrayList<>();
        Connection connection = connectionPoolInstance.getConnection();
        ArtistDAO artistDAO = new ArtistDAO(connection);
        if (idParameter == null) {
            artistList.addAll(artistDAO.findAll());
        } else {
            try {
                int artistId = Integer.parseInt(idParameter);
                artistList.add(artistDAO.findEntityById(artistId));
            } catch (NumberFormatException e) {
                artistList.addAll(artistDAO.findAll());
            }
        }
        connectionPoolInstance.closeConnection(connection);
        return artistList;
    }
}