package by.nahorny.mvc.dao;

import by.nahorny.mvc.entity.Artist;
import by.nahorny.mvc.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitri_Nahorny on 4/19/2017.
 */
public class ArtistDAO extends AbstractDAO <Integer, Artist> {
    private final String SQL_SELECT_ALL_ARTISTS = "SELECT artist_id, artist_name, artist_genre FROM artists";
    private final String SQL_SELECT_ARTIST_BY_ID = "SELECT artist_id, artist_name, artist_genre FROM artists WHERE artist_id = ?";

    public ArtistDAO(Connection connection) {
        super(connection);
    }
    public List<Artist> findAll() throws DAOException {
        Statement findStatement = null;
        List<Artist> artistsList = new ArrayList<>();

        try {
            findStatement = this.connection.createStatement();
            ResultSet artistsResultSet = findStatement.executeQuery(SQL_SELECT_ALL_ARTISTS);
            while (artistsResultSet.next()) {
                int artistId = artistsResultSet.getInt("artist_id");
                String artistName = artistsResultSet.getString("artist_name");
                String artistGenre = artistsResultSet.getString("artist_genre");
                Artist artist = new Artist(artistId, artistName, artistGenre);
                artistsList.add(artist);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            this.closeStatement(findStatement);
        }

        return artistsList;
    }
    public Artist findEntityById(Integer id)  throws DAOException {
        PreparedStatement findStatement = null;
        Artist artist = null;

        try {
            findStatement = this.connection.prepareStatement(SQL_SELECT_ARTIST_BY_ID);
            findStatement.setInt(1, id);
            ResultSet artistResultSet = findStatement.executeQuery();
            if (artistResultSet.next()) {
                int artistId = artistResultSet.getInt("artist_id");
                String artistName = artistResultSet.getString("artist_name");
                String artistGenre = artistResultSet.getString("artist_genre");
                artist = new Artist(artistId, artistName, artistGenre);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            this.closeStatement(findStatement);
        }

        return artist;
    }
    public boolean delete(Integer id)  throws DAOException {
        throw new UnsupportedOperationException();
    }
    public boolean delete(Artist entity)  throws DAOException {
        throw new UnsupportedOperationException();
    }
    public boolean create(Artist entity)  throws DAOException {
        throw new UnsupportedOperationException();
    }
    public Artist update(Artist entity)  throws DAOException {
        throw new UnsupportedOperationException();
    }
}
