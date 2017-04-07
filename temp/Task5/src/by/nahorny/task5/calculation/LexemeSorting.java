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
 * Created by Dmitri_Nahorny on 4/5/2017.
 */
public class LexemeSorting {

    static final Logger LOGGER = LogManager.getLogger(LexemeSorting.class);

    /*method prints result. String return to do*/
    public void sortText(Composite textComposite) {
        Comparator<String> stringComparator = ((String s1, String s2) -> (s1.toUpperCase().compareTo(s2.toUpperCase())));
        TreeSet lexemeSet = new TreeSet(stringComparator);

        for (int i = 0; i < textComposite.componentSize(); i++) {
            try {
                Component paragraphComposite = textComposite.getComponent(i);
                if (paragraphComposite instanceof Composite) {
                    for (int j = 0; j < paragraphComposite.componentSize(); j++) {
                        Component sentenceComposite = paragraphComposite.getComponent(j);
                        if (sentenceComposite instanceof Composite) {
                            for (int k = 0; k < sentenceComposite.componentSize(); k++) {
                                Component lexemeComposite = sentenceComposite.getComponent(k);
                                if (lexemeComposite instanceof Composite) {
                                    lexemeSet.add(lexemeComposite.toString());
                                }
                            }
                        }
                    }
                }
            } catch (LeafComponentOperationException e) {
                LOGGER.log(Level.ERROR, "Attempt to get components of leaf component");
            }
        }

        Object[] sortedLexemeSet = lexemeSet.toArray();

        for (int i = 1; i < sortedLexemeSet.length; i++) {
            System.out.print(sortedLexemeSet[i-1].toString());
            if (sortedLexemeSet[i].toString().toUpperCase().charAt(0) != sortedLexemeSet[i-1].toString().toUpperCase().charAt(0)) {
                System.out.println();
            }
            else {
                System.out.print(",");
            }
        }
        System.out.println(sortedLexemeSet[sortedLexemeSet.length - 1]);
    }
}
