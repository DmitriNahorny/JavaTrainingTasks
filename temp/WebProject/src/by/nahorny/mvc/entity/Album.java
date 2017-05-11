package by.nahorny.mvc.entity;

/**
 * Created by Dmitri_Nahorny on 4/20/2017.
 */
public class Album extends BusinessEntity implements ContentEntityHandler{

    private int id;
    private String name;
    private int artistId;
    private String artistName;

    public Album(int id, String name, int artistId, String artistName) {
        this.id = id;
        this.name = name;
        this.artistId = artistId;
        this.artistName = artistName;
    }

    public Album(String name, int artistId) {
        this.name = name;
        this.artistId = artistId;
    }

    public Album(String name) {
        this.name = name;
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
