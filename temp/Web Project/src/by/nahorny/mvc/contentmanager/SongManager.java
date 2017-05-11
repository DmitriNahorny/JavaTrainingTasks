package by.nahorny.mvc.contentmanager;

import by.nahorny.mvc.dao.SongDAO;
import by.nahorny.mvc.entity.Song;
import by.nahorny.mvc.exception.DAOException;
import by.nahorny.mvc.exception.UnavailableConnectionException;
import by.nahorny.mvc.pool.ConnectionPool;

import java.sql.Connection;
import java.util.Map;

/**
 * Created by Dmitri_Nahorny on 5/4/2017.
 */
public class SongManager extends AbstractContentManager {

    /*add throw exception for invalid numbers in request*/

    private ConnectionPool connectionPoolInstance = ConnectionPool.getConnectionPool();

    @Override
    public boolean add(Map<String,String[]> songParams) throws DAOException, UnavailableConnectionException{
        boolean isSongAdded = false;

        String songNameParam = songParams.get("songName")[0];
        String songPriceParam = songParams.get("songPrice")[0];
        String songAlbumIdParam = songParams.get("songAlbum")[0];
        float songPrice = Float.parseFloat(songPriceParam);
        int songAlbumId = Integer.parseInt(songAlbumIdParam);
        Song songToAdd = new Song(songNameParam, songPrice, songAlbumId);

        Connection connection = connectionPoolInstance.getConnection();
        SongDAO songDAO = new SongDAO(connection);
        isSongAdded = songDAO.create(songToAdd);
        connectionPoolInstance.closeConnection(connection);

        return isSongAdded;
    }

    @Override
    public boolean edit(Map<String,String[]> songParams) throws DAOException, UnavailableConnectionException{
        boolean isSongUpdated = false;

        String songIdParam = songParams.get("songId")[0];
        String songNameParam = songParams.get("songName")[0];
        String songPriceParam = songParams.get("songPrice")[0];
        int songId = Integer.parseInt(songIdParam);
        float songPrice = Float.parseFloat(songPriceParam);

        Connection connection = connectionPoolInstance.getConnection();
        SongDAO songDAO = new SongDAO(connection);
        Song songToUpdate = songDAO.findEntityById(songId);
        songToUpdate.setName(songNameParam);
        songToUpdate.setPrice(songPrice);
        if (songDAO.update(songToUpdate) != null) {
            isSongUpdated = true;
        }
        connectionPoolInstance.closeConnection(connection);

        return isSongUpdated;
    }

    @Override
    public boolean delete(Map<String,String[]> songParams) throws DAOException, UnavailableConnectionException{
        boolean isSongDeleted = false;

        String songIdParam = songParams.get("songName")[0];
        int songId = Integer.parseInt(songIdParam);

        Connection connection = connectionPoolInstance.getConnection();
        SongDAO songDAO = new SongDAO(connection);
        isSongDeleted = songDAO.delete(songId);
        connectionPoolInstance.closeConnection(connection);

        return isSongDeleted;
    }
}
