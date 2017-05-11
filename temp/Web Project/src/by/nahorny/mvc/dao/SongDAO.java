package by.nahorny.mvc.dao;

import by.nahorny.mvc.entity.Song;
import by.nahorny.mvc.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitri_Nahorny on 4/25/2017.
 */
public class SongDAO extends AbstractDAO <Integer, Song> {

    private final String SQL_SELECT_SONG_BY_ID = "SELECT s.song_id, s.song_name, s.song_price, s.album_id, al.album_name, al.artist_id, ar.artist_name FROM songs s LEFT OUTER JOIN albums al on s.album_id = al.album_id LEFT OUTER JOIN artists ar on al.artist_id = ar.artist_id WHERE s.song_id = ?";
    private final String SQL_SELECT_SONGS = "SELECT s.song_id, s.song_name, s.song_price, s.album_id, al.album_name, al.artist_id, ar.artist_name FROM songs s LEFT OUTER JOIN albums al on s.album_id = al.album_id LEFT OUTER JOIN artists ar on al.artist_id = ar.artist_id";
    private final String SQL_SELECT_SONGS_BY_ALBUM = "SELECT s.song_id, s.song_name, s.song_price, s.album_id, al.album_name, al.artist_id, ar.artist_name FROM songs s LEFT OUTER JOIN albums al on s.album_id = al.album_id LEFT OUTER JOIN artists ar on al.artist_id = ar.artist_id WHERE s.album_id = ?";
    private final String SQL_UPDATE_SONG = "UPDATE songs SET song_name = ?, song_price = ?, album_id = ? WHERE song_id = ?";

    public SongDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Song> findAll() throws DAOException {
        Statement findStatement = null;
        List<Song> songList = new ArrayList<>();

        try {
            findStatement = this.connection.createStatement();
            ResultSet songsResultSet = findStatement.executeQuery(SQL_SELECT_SONGS);
            while (songsResultSet.next()) {
                int songId  = songsResultSet.getInt("song_id");
                String songName  = songsResultSet.getString("song_name");
                float songPrice = songsResultSet.getFloat("song_price");
                int albumId  = songsResultSet.getInt("album_id");
                String albumName  = songsResultSet.getString("album_name");
                int artistId  = songsResultSet.getInt("artist_id");
                String artistName  = songsResultSet.getString("artist_name");
                Song song = new Song(songId, songName, songPrice, albumId, albumName, artistId, artistName);
                songList.add(song);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            this.closeStatement(findStatement);
        }

        return songList;
    }

    @Override
    public Song findEntityById(Integer id) throws DAOException {
        PreparedStatement findStatement = null;
        Song findSong = null;
        try {
            findStatement = this.connection.prepareStatement(SQL_SELECT_SONG_BY_ID);
            findStatement.setInt(1, id);
            ResultSet songsResultSet = findStatement.executeQuery();
            if (songsResultSet.next()) {
                String songName  = songsResultSet.getString("song_name");
                float songPrice = songsResultSet.getFloat("song_price");
                int albumId  = songsResultSet.getInt("album_id");
                String albumName  = songsResultSet.getString("album_name");
                int artistId  = songsResultSet.getInt("artist_id");
                String artistName  = songsResultSet.getString("artist_name");
                findSong = new Song(id, songName, songPrice, albumId, albumName, artistId, artistName);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            this.closeStatement(findStatement);
        }
        return findSong;
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(Song entity) throws DAOException {
        return false;
    }

    @Override
    public boolean create(Song entity) throws DAOException {
        return false;
    }

    @Override
    public Song update(Song entity) throws DAOException {
        PreparedStatement updateStatement = null;
        Song updatedSong = null;
        try {
            updateStatement = this.connection.prepareStatement(SQL_UPDATE_SONG);
            updateStatement.setString(1, entity.getName());
            updateStatement.setFloat(2, entity.getPrice());
            updateStatement.setInt(3, entity.getAlbumId());
            updateStatement.setInt(4, entity.getId());
            if (updateStatement.executeUpdate() > 0) {
                updatedSong = entity;
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            this.closeStatement(updateStatement);
        }
        return updatedSong;
    }

    public List<Song> findSongsByAlbum(int albumId) throws DAOException {
        PreparedStatement findStatement = null;
        List<Song> songList = new ArrayList<>();

        try {
            findStatement = this.connection.prepareStatement(SQL_SELECT_SONGS_BY_ALBUM);
            findStatement.setInt(1, albumId);
            ResultSet songsResultSet = findStatement.executeQuery();
            while (songsResultSet.next()) {
                int songId  = songsResultSet.getInt("song_id");
                String songName  = songsResultSet.getString("song_name");
                float songPrice = songsResultSet.getFloat("song_price");
                String albumName  = songsResultSet.getString("album_name");
                int artistId  = songsResultSet.getInt("artist_id");
                String artistName  = songsResultSet.getString("artist_name");
                Song song = new Song(songId, songName, songPrice, albumId, albumName, artistId, artistName);
                songList.add(song);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            this.closeStatement(findStatement);
        }

        return songList;
    }
}
