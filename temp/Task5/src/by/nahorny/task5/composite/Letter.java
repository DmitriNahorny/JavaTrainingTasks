package by.nahorny.task5.composite;

/**
 * Created by Dmitri_Nahorny on 3/16/2017.
 */
public class Letter implements Component {

    private String content;

    @Override
    public void addComponent(Component cmp) {

    }

    @Override
    public Component getChild(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Component cmp) {

    }

    @Override
    public String toString() {
        return content;
    }


    public Letter(String content) {
        this.content = content;
    }
}
