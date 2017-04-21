package by.nahorny.mvc.dao;

import by.nahorny.mvc.entity.Album;
import by.nahorny.mvc.entity.Artist;
import by.nahorny.mvc.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitri_Nahorny on 4/20/2017.
 */
public class AlbumDAO extends AbstractDAO <Integer, Album> {

    static private final Logger LOGGER = LogManager.getLogger(AlbumDAO.class);

    private final String SQL_SELECT_ALBUMS_BY_ARTIST = "SELECT album_id, album_name FROM albums WHERE artist_id = ?";

    public AlbumDAO(Connection connection) {
        super(connection);
    }

    public List<Album> findAll() throws DAOException {
        throw new UnsupportedOperationException();
    }
    public Album findEntityById(Integer id)  throws DAOException {
        throw new UnsupportedOperationException();
    }
    public boolean delete(Integer id)  throws DAOException {
        throw new UnsupportedOperationException();
    }
    public boolean delete(Album entity)  throws DAOException {
        throw new UnsupportedOperationException();
    }
    public boolean create(Album entity)  throws DAOException {
        throw new UnsupportedOperationException();
    }
    public Album update(Album entity)  throws DAOException {
        throw new UnsupportedOperationException();
    }

    public List<Album> findAlbumsByArtist(int artistId) throws DAOException {
        PreparedStatement findStatement = null;
        List<Album> albumList = new ArrayList<>();

        try {
            findStatement = this.connection.prepareStatement(SQL_SELECT_ALBUMS_BY_ARTIST);
            findStatement.setInt(1, artistId);
            ResultSet albumsResultSet = findStatement.getResultSet();
            while (albumsResultSet.next()) {
                int albumId = albumsResultSet.getInt("album_id");
                String albumName = albumsResultSet.getString("album_name");
                Album album = new Album(albumId, albumName);
                albumList.add(album);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new DAOException(e.getMessage());
        } finally {
            this.closeStatement(findStatement);
        }

        return albumList;
    }
}

