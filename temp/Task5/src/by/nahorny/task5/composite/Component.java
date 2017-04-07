package by.nahorny.task5.composite;

import by.nahorny.task5.exception.LeafComponentOperationException;

/**
 * Created by Dmitri_Nahorny on 3/16/2017.
 */
public interface Component {
    void addComponent (Component cmp);
    Component getComponent(int index) throws LeafComponentOperationException;
    void removeComponent(Component cmp);
    String toString();
    int componentSize();
    Component getCopy();
}
