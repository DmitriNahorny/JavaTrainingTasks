package by.nahorny.mvc.entity;

/**
 * Created by Dmitri_Nahorny on 4/20/2017.
 */
public class Album extends BusinessEntity implements ContentEntityHandler{

    private int id;
    private String name;

    public Album(int id, String name) {
        this.id = id;
        this.name = name;
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

}
