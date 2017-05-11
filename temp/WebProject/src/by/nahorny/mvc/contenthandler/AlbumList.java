package by.nahorny.mvc.contenthandler;

import by.nahorny.mvc.dao.AlbumDAO;
import by.nahorny.mvc.entity.ContentEntityHandler;
import by.nahorny.mvc.exception.DAOException;
import by.nahorny.mvc.exception.UnavailableConnectionException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitri_Nahorny on 4/21/2017.
 */
public class AlbumList extends LibraryList {
    @Override
    public List<ContentEntityHandler> getEntityList(String idParameter) throws DAOException, UnavailableConnectionException {
        List<ContentEntityHandler> albumList = new ArrayList<>();
        Connection connection = connectionPoolInstance.getConnection();
        AlbumDAO albumDAO = new AlbumDAO(connection);

        if (idParameter == null) {
            albumList.addAll(albumDAO.findAll());
        } else {
            try {
                int artistId = Integer.parseInt(idParameter);
                albumList.addAll(albumDAO.findAlbumsByArtist(artistId));
            } catch (NumberFormatException e) {
                albumList.addAll(albumDAO.findAll());
            }
        }

        connectionPoolInstance.closeConnection(connection);
        return albumList;
    }
}
