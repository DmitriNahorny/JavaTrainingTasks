package by.nahorny.mvc.dao;

import by.nahorny.mvc.entity.Album;
import by.nahorny.mvc.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitri_Nahorny on 4/20/2017.
 */
public class AlbumDAO extends AbstractDAO <Integer, Album> {

    private final String SQL_SELECT_ALBUMS_BY_ARTIST = "SELECT al.album_id, al.album_name, al.artist_id, ar.artist_name FROM albums al LEFT OUTER JOIN artists ar on al.artist_id = ar.artist_id WHERE al.artist_id = ?";
    private final String SQL_SELECT_ALBUMS = "SELECT al.album_id, al.album_name, ar.artist_name FROM albums al JOIN artists ar on al.artist_id = ar.artist_id";

    public AlbumDAO(Connection connection) {
        super(connection);
    }

    public List<Album> findAll() throws DAOException {
        Statement findStatement = null;
        List<Album> albumList = new ArrayList<>();

        try {
            findStatement = this.connection.createStatement();
            ResultSet albumsResultSet = findStatement.executeQuery(SQL_SELECT_ALBUMS);
            while (albumsResultSet.next()) {
                int albumId = albumsResultSet.getInt("album_id");
                String albumName = albumsResultSet.getString("album_name");
                int artistId = albumsResultSet.getInt("artist_id");
                String artistName = albumsResultSet.getString("artist_name");
                Album album = new Album(albumId, albumName, artistId, artistName);
                albumList.add(album);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            this.closeStatement(findStatement);
        }

        return albumList;
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
            ResultSet albumsResultSet = findStatement.executeQuery();
            while (albumsResultSet.next()) {
                int albumId = albumsResultSet.getInt("album_id");
                String albumName = albumsResultSet.getString("album_name");
                String artistName = albumsResultSet.getString("artist_name");
                Album album = new Album(albumId, albumName, artistId, artistName);
                albumList.add(album);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            this.closeStatement(findStatement);
        }

        return albumList;
    }
}

