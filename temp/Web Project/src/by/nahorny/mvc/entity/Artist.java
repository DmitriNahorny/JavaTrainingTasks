package by.nahorny.mvc.entity;

/**
 * Created by Dmitri_Nahorny on 4/19/2017.
 */
public class Artist extends BusinessEntity implements ContentEntityHandler {
    private int id;
    private String name;
    private String genre;

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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Artist(int id, String name, String genre) {
        this.id = id;
        this.name = name;
        this.genre = genre;
    }

    public Artist(String name, String genre) {
        this.name = name;
        this.genre = genre;
    }
}
