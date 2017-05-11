package by.nahorny.mvc.contenthandler;

import by.nahorny.mvc.dao.SongDAO;
import by.nahorny.mvc.entity.ContentEntityHandler;
import by.nahorny.mvc.exception.DAOException;
import by.nahorny.mvc.exception.UnavailableConnectionException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitri_Nahorny on 4/25/2017.
 */
public class SongList extends LibraryList {
    @Override
    public List<ContentEntityHandler> getEntityList(String idParameter) throws DAOException, UnavailableConnectionException {
        List<ContentEntityHandler> songList = new ArrayList<>();
        Connection connection = connectionPoolInstance.getConnection();
        SongDAO songDAO = new SongDAO(connection);

        if (idParameter == null) {
            songList.addAll(songDAO.findAll());
        } else {
            try {
                int albumId = Integer.parseInt(idParameter);
                songList.addAll(songDAO.findSongsByAlbum(albumId));
            } catch (NumberFormatException e) {
                songList.addAll(songDAO.findAll());
            }
        }

        connectionPoolInstance.closeConnection(connection);
        return songList;
    }
}
