package by.nahorny.task5.calculation;

import by.nahorny.task5.composite.Component;
import by.nahorny.task5.composite.Composite;
import by.nahorny.task5.exception.LeafComponentOperationException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Created by Dmitri_Nahorny on 4/6/2017.
 */
public class SentenceSorting {

    static final Logger LOGGER = LogManager.getLogger(SentenceSorting.class);

    public void sortSentence(Component textComposite){
    /*method prints result. String return to do*/
        Comparator<Component> sentenceComparator = Comparator.comparing(Component::componentSize);
        TreeSet sentenceSet = new TreeSet(sentenceComparator);

        for (int i = 0; i < textComposite.componentSize(); i++) {
            try {
                Component paragraphComposite = textComposite.getComponent(i);
                if (paragraphComposite instanceof Composite) {
                    for (int j = 0; j < paragraphComposite.componentSize(); j++) {
                        Component sentenceComposite = paragraphComposite.getComponent(j);
                        if (sentenceComposite instanceof Composite) {
                            sentenceSet.add(sentenceComposite);
                        }
                    }
                }
            } catch (LeafComponentOperationException e) {
                LOGGER.log(Level.ERROR, "Attempt to get components of leaf component");
            }
        }

        Object[] sortedSentenceSet = sentenceSet.toArray();
        for (int i = 0; i < sortedSentenceSet.length; i++) {
            System.out.println(i + 1 + ". " + sortedSentenceSet[i].toString());
        }
    }
}
