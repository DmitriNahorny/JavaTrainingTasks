package by.nahorny.mvc.entity;

/**
 * Created by Dmitri_Nahorny on 4/25/2017.
 */
public class Song extends BusinessEntity implements ContentEntityHandler {

    private int id;
    private String name;
    private float price;
    private int albumId;
    private String albumName;
    private int artistId;
    private String artistName;

    public Song(int id, String name, float price, int albumId, String albumName, int artistId, String artistName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.albumId = albumId;
        this.albumName = albumName;
        this.artistId = artistId;
        this.artistName = artistName;
    }

    public Song(String name, float price, int albumId, int artistId) {
        this.name = name;
        this.price = price;
        this.albumId = albumId;
        this.artistId = artistId;
    }

    public Song(String name, float price, int albumId) {
        this.name = name;
        this.price = price;
        this.albumId = albumId;
    }

    public Song(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}
