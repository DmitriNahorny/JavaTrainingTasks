package by.nahorny.task5.composite;

import by.nahorny.task5.exception.LeafComponentOperationException;

/**
 * Created by Dmitri_Nahorny on 3/16/2017.
 */
public class Letter implements Component {

    private String content;

    @Override
    public void addComponent(Component cmp) {
    }

    @Override
    public Component getComponent(int index) throws LeafComponentOperationException{
        throw new LeafComponentOperationException();
    }

    @Override
    public void removeComponent(Component cmp) {
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public int componentSize() {
        return 0;
    }

    @Override
    public Component getCopy() {
        Component letterCopy = new Letter(this.content);
        return letterCopy;
    }

    public Letter(String content) {
        this.content = content;
    }
}
