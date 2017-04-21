package by.nahorny.mvc.dao;

import by.nahorny.mvc.entity.Artist;
import by.nahorny.mvc.entity.User;
import by.nahorny.mvc.exception.DAOException;
import com.sun.glass.ui.EventLoop;
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

    static private final Logger LOGGER = LogManager.getLogger(ArtistDAO.class);

    private final String SQL_SELECT_ALL_ARTISTS = "SELECT artist_id, artist_name, artist_genre FROM artists";

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
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new DAOException(e.getMessage());
        } finally {
            this.closeStatement(findStatement);
        }

        return artistsList;
    }
    public Artist findEntityById(Integer id)  throws DAOException {
        throw new UnsupportedOperationException();
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
