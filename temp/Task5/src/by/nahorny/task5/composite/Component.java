package by.nahorny.task5.composite;

/**
 * Created by Dmitri_Nahorny on 3/16/2017.
 */
public interface Component {
    void addComponent (Component cmp);
    Component getChild (int index);
    void remove (Component cmp);
    String toString();
    int componentSize();
    Component getCopy();
}
