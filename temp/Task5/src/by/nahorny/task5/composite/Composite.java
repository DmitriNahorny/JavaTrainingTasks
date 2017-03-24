package by.nahorny.task5.composite;

import java.util.ArrayList;

/**
 * Created by Dmitri_Nahorny on 3/16/2017.
 */
public class Composite implements Component {

    private ArrayList<Component> components = new ArrayList<>();

    @Override
    public void addComponent(Component cmp) {
        this.components.add(cmp);
    }

    @Override
    public Component getChild(int index) {
        return components.get(index);
    }

    @Override
    public void remove(Component cmp) {
        components.remove(cmp);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for(Component c : components)
        {
            sb.append(c.toString());
        }
        return sb.toString();
    }
}
